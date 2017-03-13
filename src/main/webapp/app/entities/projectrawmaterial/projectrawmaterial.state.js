(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectrawmaterial', {
            parent: 'entity',
            url: '/projectrawmaterial',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectrawmaterial.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectrawmaterial/projectrawmaterials.html',
                    controller: 'ProjectrawmaterialController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectrawmaterial');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectrawmaterial-detail', {
            parent: 'projectrawmaterial',
            url: '/projectrawmaterial/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectrawmaterial.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectrawmaterial/projectrawmaterial-detail.html',
                    controller: 'ProjectrawmaterialDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectrawmaterial');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectrawmaterial', function($stateParams, Projectrawmaterial) {
                    return Projectrawmaterial.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectrawmaterial',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectrawmaterial-detail.edit', {
            parent: 'projectrawmaterial-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectrawmaterial/projectrawmaterial-dialog.html',
                    controller: 'ProjectrawmaterialDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectrawmaterial', function(Projectrawmaterial) {
                            return Projectrawmaterial.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectrawmaterial.new', {
            parent: 'projectrawmaterial',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectrawmaterial/projectrawmaterial-dialog.html',
                    controller: 'ProjectrawmaterialDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                raw_material: null,
                                raw_quantity: null,
                                raw_unit: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectrawmaterial', null, { reload: 'projectrawmaterial' });
                }, function() {
                    $state.go('projectrawmaterial');
                });
            }]
        })
        .state('projectrawmaterial.edit', {
            parent: 'projectrawmaterial',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectrawmaterial/projectrawmaterial-dialog.html',
                    controller: 'ProjectrawmaterialDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectrawmaterial', function(Projectrawmaterial) {
                            return Projectrawmaterial.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectrawmaterial', null, { reload: 'projectrawmaterial' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectrawmaterial.delete', {
            parent: 'projectrawmaterial',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectrawmaterial/projectrawmaterial-delete-dialog.html',
                    controller: 'ProjectrawmaterialDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectrawmaterial', function(Projectrawmaterial) {
                            return Projectrawmaterial.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectrawmaterial', null, { reload: 'projectrawmaterial' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

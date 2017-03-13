(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projecttype', {
            parent: 'entity',
            url: '/projecttype',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projecttype.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projecttype/projecttypes.html',
                    controller: 'ProjecttypeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projecttype');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projecttype-detail', {
            parent: 'projecttype',
            url: '/projecttype/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projecttype.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projecttype/projecttype-detail.html',
                    controller: 'ProjecttypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projecttype');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projecttype', function($stateParams, Projecttype) {
                    return Projecttype.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projecttype',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projecttype-detail.edit', {
            parent: 'projecttype-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projecttype/projecttype-dialog.html',
                    controller: 'ProjecttypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projecttype', function(Projecttype) {
                            return Projecttype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projecttype.new', {
            parent: 'projecttype',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projecttype/projecttype-dialog.html',
                    controller: 'ProjecttypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projecttype_name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projecttype', null, { reload: 'projecttype' });
                }, function() {
                    $state.go('projecttype');
                });
            }]
        })
        .state('projecttype.edit', {
            parent: 'projecttype',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projecttype/projecttype-dialog.html',
                    controller: 'ProjecttypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projecttype', function(Projecttype) {
                            return Projecttype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projecttype', null, { reload: 'projecttype' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projecttype.delete', {
            parent: 'projecttype',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projecttype/projecttype-delete-dialog.html',
                    controller: 'ProjecttypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projecttype', function(Projecttype) {
                            return Projecttype.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projecttype', null, { reload: 'projecttype' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

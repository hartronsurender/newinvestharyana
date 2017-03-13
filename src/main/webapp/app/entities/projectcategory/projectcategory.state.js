(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectcategory', {
            parent: 'entity',
            url: '/projectcategory',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectcategory.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectcategory/projectcategories.html',
                    controller: 'ProjectcategoryController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectcategory');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectcategory-detail', {
            parent: 'projectcategory',
            url: '/projectcategory/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectcategory.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectcategory/projectcategory-detail.html',
                    controller: 'ProjectcategoryDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectcategory');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectcategory', function($stateParams, Projectcategory) {
                    return Projectcategory.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectcategory',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectcategory-detail.edit', {
            parent: 'projectcategory-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectcategory/projectcategory-dialog.html',
                    controller: 'ProjectcategoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectcategory', function(Projectcategory) {
                            return Projectcategory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectcategory.new', {
            parent: 'projectcategory',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectcategory/projectcategory-dialog.html',
                    controller: 'ProjectcategoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectcategoryname: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectcategory', null, { reload: 'projectcategory' });
                }, function() {
                    $state.go('projectcategory');
                });
            }]
        })
        .state('projectcategory.edit', {
            parent: 'projectcategory',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectcategory/projectcategory-dialog.html',
                    controller: 'ProjectcategoryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectcategory', function(Projectcategory) {
                            return Projectcategory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectcategory', null, { reload: 'projectcategory' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectcategory.delete', {
            parent: 'projectcategory',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectcategory/projectcategory-delete-dialog.html',
                    controller: 'ProjectcategoryDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectcategory', function(Projectcategory) {
                            return Projectcategory.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectcategory', null, { reload: 'projectcategory' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('electric-load-type', {
            parent: 'entity',
            url: '/electric-load-type',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.electric_load_type.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/electric-load-type/electric-load-types.html',
                    controller: 'Electric_load_typeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('electric_load_type');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('electric-load-type-detail', {
            parent: 'electric-load-type',
            url: '/electric-load-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.electric_load_type.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/electric-load-type/electric-load-type-detail.html',
                    controller: 'Electric_load_typeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('electric_load_type');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Electric_load_type', function($stateParams, Electric_load_type) {
                    return Electric_load_type.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'electric-load-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('electric-load-type-detail.edit', {
            parent: 'electric-load-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/electric-load-type/electric-load-type-dialog.html',
                    controller: 'Electric_load_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Electric_load_type', function(Electric_load_type) {
                            return Electric_load_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('electric-load-type.new', {
            parent: 'electric-load-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/electric-load-type/electric-load-type-dialog.html',
                    controller: 'Electric_load_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                regular_uhbvn_dhbvn_customer_type_name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('electric-load-type', null, { reload: 'electric-load-type' });
                }, function() {
                    $state.go('electric-load-type');
                });
            }]
        })
        .state('electric-load-type.edit', {
            parent: 'electric-load-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/electric-load-type/electric-load-type-dialog.html',
                    controller: 'Electric_load_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Electric_load_type', function(Electric_load_type) {
                            return Electric_load_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('electric-load-type', null, { reload: 'electric-load-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('electric-load-type.delete', {
            parent: 'electric-load-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/electric-load-type/electric-load-type-delete-dialog.html',
                    controller: 'Electric_load_typeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Electric_load_type', function(Electric_load_type) {
                            return Electric_load_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('electric-load-type', null, { reload: 'electric-load-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

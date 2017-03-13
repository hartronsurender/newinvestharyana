(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('units', {
            parent: 'entity',
            url: '/units',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.units.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/units/units.html',
                    controller: 'UnitsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('units');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('units-detail', {
            parent: 'units',
            url: '/units/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.units.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/units/units-detail.html',
                    controller: 'UnitsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('units');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Units', function($stateParams, Units) {
                    return Units.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'units',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('units-detail.edit', {
            parent: 'units-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/units/units-dialog.html',
                    controller: 'UnitsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Units', function(Units) {
                            return Units.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('units.new', {
            parent: 'units',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/units/units-dialog.html',
                    controller: 'UnitsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                unittype: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('units', null, { reload: 'units' });
                }, function() {
                    $state.go('units');
                });
            }]
        })
        .state('units.edit', {
            parent: 'units',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/units/units-dialog.html',
                    controller: 'UnitsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Units', function(Units) {
                            return Units.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('units', null, { reload: 'units' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('units.delete', {
            parent: 'units',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/units/units-delete-dialog.html',
                    controller: 'UnitsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Units', function(Units) {
                            return Units.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('units', null, { reload: 'units' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

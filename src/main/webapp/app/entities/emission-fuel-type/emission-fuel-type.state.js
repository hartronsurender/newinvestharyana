(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('emission-fuel-type', {
            parent: 'entity',
            url: '/emission-fuel-type',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emission_fuel_type.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emission-fuel-type/emission-fuel-types.html',
                    controller: 'Emission_fuel_typeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emission_fuel_type');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('emission-fuel-type-detail', {
            parent: 'emission-fuel-type',
            url: '/emission-fuel-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emission_fuel_type.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emission-fuel-type/emission-fuel-type-detail.html',
                    controller: 'Emission_fuel_typeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emission_fuel_type');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Emission_fuel_type', function($stateParams, Emission_fuel_type) {
                    return Emission_fuel_type.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'emission-fuel-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('emission-fuel-type-detail.edit', {
            parent: 'emission-fuel-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-fuel-type/emission-fuel-type-dialog.html',
                    controller: 'Emission_fuel_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emission_fuel_type', function(Emission_fuel_type) {
                            return Emission_fuel_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emission-fuel-type.new', {
            parent: 'emission-fuel-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-fuel-type/emission-fuel-type-dialog.html',
                    controller: 'Emission_fuel_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                fuel_type: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('emission-fuel-type', null, { reload: 'emission-fuel-type' });
                }, function() {
                    $state.go('emission-fuel-type');
                });
            }]
        })
        .state('emission-fuel-type.edit', {
            parent: 'emission-fuel-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-fuel-type/emission-fuel-type-dialog.html',
                    controller: 'Emission_fuel_typeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emission_fuel_type', function(Emission_fuel_type) {
                            return Emission_fuel_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emission-fuel-type', null, { reload: 'emission-fuel-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emission-fuel-type.delete', {
            parent: 'emission-fuel-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-fuel-type/emission-fuel-type-delete-dialog.html',
                    controller: 'Emission_fuel_typeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Emission_fuel_type', function(Emission_fuel_type) {
                            return Emission_fuel_type.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emission-fuel-type', null, { reload: 'emission-fuel-type' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

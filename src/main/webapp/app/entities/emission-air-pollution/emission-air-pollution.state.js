(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('emission-air-pollution', {
            parent: 'entity',
            url: '/emission-air-pollution',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emission_air_pollution.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emission-air-pollution/emission-air-pollutions.html',
                    controller: 'Emission_air_pollutionController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emission_air_pollution');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('emission-air-pollution-detail', {
            parent: 'emission-air-pollution',
            url: '/emission-air-pollution/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emission_air_pollution.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emission-air-pollution/emission-air-pollution-detail.html',
                    controller: 'Emission_air_pollutionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emission_air_pollution');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Emission_air_pollution', function($stateParams, Emission_air_pollution) {
                    return Emission_air_pollution.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'emission-air-pollution',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('emission-air-pollution-detail.edit', {
            parent: 'emission-air-pollution-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-air-pollution/emission-air-pollution-dialog.html',
                    controller: 'Emission_air_pollutionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emission_air_pollution', function(Emission_air_pollution) {
                            return Emission_air_pollution.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emission-air-pollution.new', {
            parent: 'emission-air-pollution',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-air-pollution/emission-air-pollution-dialog.html',
                    controller: 'Emission_air_pollutionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                pollution_controll_device: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('emission-air-pollution', null, { reload: 'emission-air-pollution' });
                }, function() {
                    $state.go('emission-air-pollution');
                });
            }]
        })
        .state('emission-air-pollution.edit', {
            parent: 'emission-air-pollution',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-air-pollution/emission-air-pollution-dialog.html',
                    controller: 'Emission_air_pollutionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emission_air_pollution', function(Emission_air_pollution) {
                            return Emission_air_pollution.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emission-air-pollution', null, { reload: 'emission-air-pollution' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emission-air-pollution.delete', {
            parent: 'emission-air-pollution',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-air-pollution/emission-air-pollution-delete-dialog.html',
                    controller: 'Emission_air_pollutionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Emission_air_pollution', function(Emission_air_pollution) {
                            return Emission_air_pollution.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emission-air-pollution', null, { reload: 'emission-air-pollution' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

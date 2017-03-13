(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('waste-water-disposal-mode', {
            parent: 'entity',
            url: '/waste-water-disposal-mode',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.waste_water_disposal_mode.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/waste-water-disposal-mode/waste-water-disposal-modes.html',
                    controller: 'Waste_water_disposal_modeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('waste_water_disposal_mode');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('waste-water-disposal-mode-detail', {
            parent: 'waste-water-disposal-mode',
            url: '/waste-water-disposal-mode/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.waste_water_disposal_mode.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/waste-water-disposal-mode/waste-water-disposal-mode-detail.html',
                    controller: 'Waste_water_disposal_modeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('waste_water_disposal_mode');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Waste_water_disposal_mode', function($stateParams, Waste_water_disposal_mode) {
                    return Waste_water_disposal_mode.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'waste-water-disposal-mode',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('waste-water-disposal-mode-detail.edit', {
            parent: 'waste-water-disposal-mode-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-disposal-mode/waste-water-disposal-mode-dialog.html',
                    controller: 'Waste_water_disposal_modeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Waste_water_disposal_mode', function(Waste_water_disposal_mode) {
                            return Waste_water_disposal_mode.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('waste-water-disposal-mode.new', {
            parent: 'waste-water-disposal-mode',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-disposal-mode/waste-water-disposal-mode-dialog.html',
                    controller: 'Waste_water_disposal_modeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                disposal_mode: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('waste-water-disposal-mode', null, { reload: 'waste-water-disposal-mode' });
                }, function() {
                    $state.go('waste-water-disposal-mode');
                });
            }]
        })
        .state('waste-water-disposal-mode.edit', {
            parent: 'waste-water-disposal-mode',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-disposal-mode/waste-water-disposal-mode-dialog.html',
                    controller: 'Waste_water_disposal_modeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Waste_water_disposal_mode', function(Waste_water_disposal_mode) {
                            return Waste_water_disposal_mode.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('waste-water-disposal-mode', null, { reload: 'waste-water-disposal-mode' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('waste-water-disposal-mode.delete', {
            parent: 'waste-water-disposal-mode',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-disposal-mode/waste-water-disposal-mode-delete-dialog.html',
                    controller: 'Waste_water_disposal_modeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Waste_water_disposal_mode', function(Waste_water_disposal_mode) {
                            return Waste_water_disposal_mode.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('waste-water-disposal-mode', null, { reload: 'waste-water-disposal-mode' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

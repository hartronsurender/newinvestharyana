(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('water-supply-source', {
            parent: 'entity',
            url: '/water-supply-source',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.water_supply_source.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/water-supply-source/water-supply-sources.html',
                    controller: 'Water_supply_sourceController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('water_supply_source');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('water-supply-source-detail', {
            parent: 'water-supply-source',
            url: '/water-supply-source/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.water_supply_source.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/water-supply-source/water-supply-source-detail.html',
                    controller: 'Water_supply_sourceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('water_supply_source');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Water_supply_source', function($stateParams, Water_supply_source) {
                    return Water_supply_source.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'water-supply-source',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('water-supply-source-detail.edit', {
            parent: 'water-supply-source-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/water-supply-source/water-supply-source-dialog.html',
                    controller: 'Water_supply_sourceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Water_supply_source', function(Water_supply_source) {
                            return Water_supply_source.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('water-supply-source.new', {
            parent: 'water-supply-source',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/water-supply-source/water-supply-source-dialog.html',
                    controller: 'Water_supply_sourceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                source_type: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('water-supply-source', null, { reload: 'water-supply-source' });
                }, function() {
                    $state.go('water-supply-source');
                });
            }]
        })
        .state('water-supply-source.edit', {
            parent: 'water-supply-source',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/water-supply-source/water-supply-source-dialog.html',
                    controller: 'Water_supply_sourceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Water_supply_source', function(Water_supply_source) {
                            return Water_supply_source.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('water-supply-source', null, { reload: 'water-supply-source' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('water-supply-source.delete', {
            parent: 'water-supply-source',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/water-supply-source/water-supply-source-delete-dialog.html',
                    controller: 'Water_supply_sourceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Water_supply_source', function(Water_supply_source) {
                            return Water_supply_source.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('water-supply-source', null, { reload: 'water-supply-source' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

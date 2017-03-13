(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('waste-water-nature', {
            parent: 'entity',
            url: '/waste-water-nature',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.waste_water_nature.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/waste-water-nature/waste-water-natures.html',
                    controller: 'Waste_water_natureController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('waste_water_nature');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('waste-water-nature-detail', {
            parent: 'waste-water-nature',
            url: '/waste-water-nature/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.waste_water_nature.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/waste-water-nature/waste-water-nature-detail.html',
                    controller: 'Waste_water_natureDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('waste_water_nature');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Waste_water_nature', function($stateParams, Waste_water_nature) {
                    return Waste_water_nature.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'waste-water-nature',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('waste-water-nature-detail.edit', {
            parent: 'waste-water-nature-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-nature/waste-water-nature-dialog.html',
                    controller: 'Waste_water_natureDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Waste_water_nature', function(Waste_water_nature) {
                            return Waste_water_nature.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('waste-water-nature.new', {
            parent: 'waste-water-nature',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-nature/waste-water-nature-dialog.html',
                    controller: 'Waste_water_natureDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                waste_water_nature_type: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('waste-water-nature', null, { reload: 'waste-water-nature' });
                }, function() {
                    $state.go('waste-water-nature');
                });
            }]
        })
        .state('waste-water-nature.edit', {
            parent: 'waste-water-nature',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-nature/waste-water-nature-dialog.html',
                    controller: 'Waste_water_natureDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Waste_water_nature', function(Waste_water_nature) {
                            return Waste_water_nature.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('waste-water-nature', null, { reload: 'waste-water-nature' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('waste-water-nature.delete', {
            parent: 'waste-water-nature',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-nature/waste-water-nature-delete-dialog.html',
                    controller: 'Waste_water_natureDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Waste_water_nature', function(Waste_water_nature) {
                            return Waste_water_nature.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('waste-water-nature', null, { reload: 'waste-water-nature' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

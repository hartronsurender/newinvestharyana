(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('waste-water-detail', {
            parent: 'entity',
            url: '/waste-water-detail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.waste_water_detail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/waste-water-detail/waste-water-details.html',
                    controller: 'Waste_water_detailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('waste_water_detail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('waste-water-detail-detail', {
            parent: 'waste-water-detail',
            url: '/waste-water-detail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.waste_water_detail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/waste-water-detail/waste-water-detail-detail.html',
                    controller: 'Waste_water_detailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('waste_water_detail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Waste_water_detail', function($stateParams, Waste_water_detail) {
                    return Waste_water_detail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'waste-water-detail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('waste-water-detail-detail.edit', {
            parent: 'waste-water-detail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-detail/waste-water-detail-dialog.html',
                    controller: 'Waste_water_detailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Waste_water_detail', function(Waste_water_detail) {
                            return Waste_water_detail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('waste-water-detail.new', {
            parent: 'waste-water-detail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-detail/waste-water-detail-dialog.html',
                    controller: 'Waste_water_detailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                source_of_generation: null,
                                nature_type: null,
                                quantity: null,
                                mode_of_disposal: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('waste-water-detail', null, { reload: 'waste-water-detail' });
                }, function() {
                    $state.go('waste-water-detail');
                });
            }]
        })
        .state('waste-water-detail.edit', {
            parent: 'waste-water-detail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-detail/waste-water-detail-dialog.html',
                    controller: 'Waste_water_detailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Waste_water_detail', function(Waste_water_detail) {
                            return Waste_water_detail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('waste-water-detail', null, { reload: 'waste-water-detail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('waste-water-detail.delete', {
            parent: 'waste-water-detail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/waste-water-detail/waste-water-detail-delete-dialog.html',
                    controller: 'Waste_water_detailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Waste_water_detail', function(Waste_water_detail) {
                            return Waste_water_detail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('waste-water-detail', null, { reload: 'waste-water-detail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

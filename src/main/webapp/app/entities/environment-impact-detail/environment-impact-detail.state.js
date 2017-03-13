(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('environment-impact-detail', {
            parent: 'entity',
            url: '/environment-impact-detail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.environment_impact_detail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/environment-impact-detail/environment-impact-details.html',
                    controller: 'Environment_impact_detailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('environment_impact_detail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('environment-impact-detail-detail', {
            parent: 'environment-impact-detail',
            url: '/environment-impact-detail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.environment_impact_detail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/environment-impact-detail/environment-impact-detail-detail.html',
                    controller: 'Environment_impact_detailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('environment_impact_detail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Environment_impact_detail', function($stateParams, Environment_impact_detail) {
                    return Environment_impact_detail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'environment-impact-detail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('environment-impact-detail-detail.edit', {
            parent: 'environment-impact-detail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/environment-impact-detail/environment-impact-detail-dialog.html',
                    controller: 'Environment_impact_detailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Environment_impact_detail', function(Environment_impact_detail) {
                            return Environment_impact_detail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('environment-impact-detail.new', {
            parent: 'environment-impact-detail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/environment-impact-detail/environment-impact-detail-dialog.html',
                    controller: 'Environment_impact_detailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                water_supply_source: null,
                                water_usage_process: null,
                                water_usage_cooling: null,
                                water_usage_domestic: null,
                                water_usage_other: null,
                                water_waste_process: null,
                                water_waste_cooling: null,
                                water_waste_domesting: null,
                                water_waste_other: null,
                                waste_water_treatment: null,
                                waste_water_treatment_document: null,
                                waste_water_treatment_documentContentType: null,
                                mode_of_disposal: null,
                                emissionid: null,
                                water_waste_id: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('environment-impact-detail', null, { reload: 'environment-impact-detail' });
                }, function() {
                    $state.go('environment-impact-detail');
                });
            }]
        })
        .state('environment-impact-detail.edit', {
            parent: 'environment-impact-detail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/environment-impact-detail/environment-impact-detail-dialog.html',
                    controller: 'Environment_impact_detailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Environment_impact_detail', function(Environment_impact_detail) {
                            return Environment_impact_detail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('environment-impact-detail', null, { reload: 'environment-impact-detail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('environment-impact-detail.delete', {
            parent: 'environment-impact-detail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/environment-impact-detail/environment-impact-detail-delete-dialog.html',
                    controller: 'Environment_impact_detailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Environment_impact_detail', function(Environment_impact_detail) {
                            return Environment_impact_detail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('environment-impact-detail', null, { reload: 'environment-impact-detail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('financeinvestment', {
            parent: 'entity',
            url: '/financeinvestment',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.financeinvestment.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/financeinvestment/financeinvestments.html',
                    controller: 'FinanceinvestmentController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('financeinvestment');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('financeinvestment-detail', {
            parent: 'financeinvestment',
            url: '/financeinvestment/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.financeinvestment.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/financeinvestment/financeinvestment-detail.html',
                    controller: 'FinanceinvestmentDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('financeinvestment');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Financeinvestment', function($stateParams, Financeinvestment) {
                    return Financeinvestment.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'financeinvestment',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('financeinvestment-detail.edit', {
            parent: 'financeinvestment-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/financeinvestment/financeinvestment-dialog.html',
                    controller: 'FinanceinvestmentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Financeinvestment', function(Financeinvestment) {
                            return Financeinvestment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('financeinvestment.new', {
            parent: 'financeinvestment',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/financeinvestment/financeinvestment-dialog.html',
                    controller: 'FinanceinvestmentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                land_cost: null,
                                building_cost: null,
                                plan_machinery_cost: null,
                                misc_assets: null,
                                totalproject_cost: null,
                                fdi_applicable: null,
                                fdi_country: null,
                                fdi_value: null,
                                fdi_resource: null,
                                project_construction_start_date: null,
                                project_commercial_activity_date: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('financeinvestment', null, { reload: 'financeinvestment' });
                }, function() {
                    $state.go('financeinvestment');
                });
            }]
        })
        .state('financeinvestment.edit', {
            parent: 'financeinvestment',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/financeinvestment/financeinvestment-dialog.html',
                    controller: 'FinanceinvestmentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Financeinvestment', function(Financeinvestment) {
                            return Financeinvestment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('financeinvestment', null, { reload: 'financeinvestment' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('financeinvestment.delete', {
            parent: 'financeinvestment',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/financeinvestment/financeinvestment-delete-dialog.html',
                    controller: 'FinanceinvestmentDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Financeinvestment', function(Financeinvestment) {
                            return Financeinvestment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('financeinvestment', null, { reload: 'financeinvestment' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

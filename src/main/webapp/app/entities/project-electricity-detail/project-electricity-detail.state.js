(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('project-electricity-detail', {
            parent: 'entity',
            url: '/project-electricity-detail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.project_electricity_detail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-electricity-detail/project-electricity-details.html',
                    controller: 'Project_electricity_detailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('project_electricity_detail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('project-electricity-detail-detail', {
            parent: 'project-electricity-detail',
            url: '/project-electricity-detail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.project_electricity_detail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-electricity-detail/project-electricity-detail-detail.html',
                    controller: 'Project_electricity_detailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('project_electricity_detail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Project_electricity_detail', function($stateParams, Project_electricity_detail) {
                    return Project_electricity_detail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'project-electricity-detail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('project-electricity-detail-detail.edit', {
            parent: 'project-electricity-detail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-electricity-detail/project-electricity-detail-dialog.html',
                    controller: 'Project_electricity_detailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Project_electricity_detail', function(Project_electricity_detail) {
                            return Project_electricity_detail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-electricity-detail.new', {
            parent: 'project-electricity-detail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-electricity-detail/project-electricity-detail-dialog.html',
                    controller: 'Project_electricity_detailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                temporaryconnection: null,
                                temporaryconnectionContentType: null,
                                load_required: null,
                                existing_connecton: null,
                                account_number: null,
                                load_demand: null,
                                load_demand_kva: null,
                                new_load_demand_kw: null,
                                new_load_demand_kva: null,
                                load_demand_date: null,
                                regular_connection: null,
                                regular_connectionContentType: null,
                                regular_load_required: null,
                                regular_existing_connection: null,
                                regular_uhbvn_dhbvn_customer_type: null,
                                regular_account_number: null,
                                regular_load_if_any_kw: null,
                                regular_existing_load_kva: null,
                                regular_new_load_demand_kw: null,
                                regular_new_load_demand_kva: null,
                                regular_demand_date: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('project-electricity-detail', null, { reload: 'project-electricity-detail' });
                }, function() {
                    $state.go('project-electricity-detail');
                });
            }]
        })
        .state('project-electricity-detail.edit', {
            parent: 'project-electricity-detail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-electricity-detail/project-electricity-detail-dialog.html',
                    controller: 'Project_electricity_detailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Project_electricity_detail', function(Project_electricity_detail) {
                            return Project_electricity_detail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-electricity-detail', null, { reload: 'project-electricity-detail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-electricity-detail.delete', {
            parent: 'project-electricity-detail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-electricity-detail/project-electricity-detail-delete-dialog.html',
                    controller: 'Project_electricity_detailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Project_electricity_detail', function(Project_electricity_detail) {
                            return Project_electricity_detail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-electricity-detail', null, { reload: 'project-electricity-detail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

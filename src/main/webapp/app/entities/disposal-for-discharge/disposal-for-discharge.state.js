(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('disposal-for-discharge', {
            parent: 'entity',
            url: '/disposal-for-discharge',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.disposal_for_discharge.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/disposal-for-discharge/disposal-for-discharges.html',
                    controller: 'Disposal_for_dischargeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('disposal_for_discharge');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('disposal-for-discharge-detail', {
            parent: 'disposal-for-discharge',
            url: '/disposal-for-discharge/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.disposal_for_discharge.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/disposal-for-discharge/disposal-for-discharge-detail.html',
                    controller: 'Disposal_for_dischargeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('disposal_for_discharge');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Disposal_for_discharge', function($stateParams, Disposal_for_discharge) {
                    return Disposal_for_discharge.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'disposal-for-discharge',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('disposal-for-discharge-detail.edit', {
            parent: 'disposal-for-discharge-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/disposal-for-discharge/disposal-for-discharge-dialog.html',
                    controller: 'Disposal_for_dischargeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Disposal_for_discharge', function(Disposal_for_discharge) {
                            return Disposal_for_discharge.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('disposal-for-discharge.new', {
            parent: 'disposal-for-discharge',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/disposal-for-discharge/disposal-for-discharge-dialog.html',
                    controller: 'Disposal_for_dischargeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                disposal_for_discharge_type: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('disposal-for-discharge', null, { reload: 'disposal-for-discharge' });
                }, function() {
                    $state.go('disposal-for-discharge');
                });
            }]
        })
        .state('disposal-for-discharge.edit', {
            parent: 'disposal-for-discharge',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/disposal-for-discharge/disposal-for-discharge-dialog.html',
                    controller: 'Disposal_for_dischargeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Disposal_for_discharge', function(Disposal_for_discharge) {
                            return Disposal_for_discharge.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('disposal-for-discharge', null, { reload: 'disposal-for-discharge' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('disposal-for-discharge.delete', {
            parent: 'disposal-for-discharge',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/disposal-for-discharge/disposal-for-discharge-delete-dialog.html',
                    controller: 'Disposal_for_dischargeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Disposal_for_discharge', function(Disposal_for_discharge) {
                            return Disposal_for_discharge.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('disposal-for-discharge', null, { reload: 'disposal-for-discharge' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

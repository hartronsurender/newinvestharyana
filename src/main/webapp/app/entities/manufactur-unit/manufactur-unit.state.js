(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('manufactur-unit', {
            parent: 'entity',
            url: '/manufactur-unit',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.manufactur_unit.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/manufactur-unit/manufactur-units.html',
                    controller: 'Manufactur_unitController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('manufactur_unit');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('manufactur-unit-detail', {
            parent: 'manufactur-unit',
            url: '/manufactur-unit/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.manufactur_unit.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/manufactur-unit/manufactur-unit-detail.html',
                    controller: 'Manufactur_unitDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('manufactur_unit');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Manufactur_unit', function($stateParams, Manufactur_unit) {
                    return Manufactur_unit.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'manufactur-unit',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('manufactur-unit-detail.edit', {
            parent: 'manufactur-unit-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufactur-unit/manufactur-unit-dialog.html',
                    controller: 'Manufactur_unitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Manufactur_unit', function(Manufactur_unit) {
                            return Manufactur_unit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('manufactur-unit.new', {
            parent: 'manufactur-unit',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufactur-unit/manufactur-unit-dialog.html',
                    controller: 'Manufactur_unitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                unit_type: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('manufactur-unit', null, { reload: 'manufactur-unit' });
                }, function() {
                    $state.go('manufactur-unit');
                });
            }]
        })
        .state('manufactur-unit.edit', {
            parent: 'manufactur-unit',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufactur-unit/manufactur-unit-dialog.html',
                    controller: 'Manufactur_unitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Manufactur_unit', function(Manufactur_unit) {
                            return Manufactur_unit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('manufactur-unit', null, { reload: 'manufactur-unit' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('manufactur-unit.delete', {
            parent: 'manufactur-unit',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufactur-unit/manufactur-unit-delete-dialog.html',
                    controller: 'Manufactur_unitDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Manufactur_unit', function(Manufactur_unit) {
                            return Manufactur_unit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('manufactur-unit', null, { reload: 'manufactur-unit' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

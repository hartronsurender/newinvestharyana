(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('emission-particulars', {
            parent: 'entity',
            url: '/emission-particulars',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emission_particulars.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emission-particulars/emission-particulars.html',
                    controller: 'Emission_particularsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emission_particulars');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('emission-particulars-detail', {
            parent: 'emission-particulars',
            url: '/emission-particulars/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emission_particulars.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emission-particulars/emission-particulars-detail.html',
                    controller: 'Emission_particularsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emission_particulars');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Emission_particulars', function($stateParams, Emission_particulars) {
                    return Emission_particulars.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'emission-particulars',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('emission-particulars-detail.edit', {
            parent: 'emission-particulars-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-particulars/emission-particulars-dialog.html',
                    controller: 'Emission_particularsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emission_particulars', function(Emission_particulars) {
                            return Emission_particulars.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emission-particulars.new', {
            parent: 'emission-particulars',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-particulars/emission-particulars-dialog.html',
                    controller: 'Emission_particularsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                particular_type: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('emission-particulars', null, { reload: 'emission-particulars' });
                }, function() {
                    $state.go('emission-particulars');
                });
            }]
        })
        .state('emission-particulars.edit', {
            parent: 'emission-particulars',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-particulars/emission-particulars-dialog.html',
                    controller: 'Emission_particularsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emission_particulars', function(Emission_particulars) {
                            return Emission_particulars.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emission-particulars', null, { reload: 'emission-particulars' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emission-particulars.delete', {
            parent: 'emission-particulars',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emission-particulars/emission-particulars-delete-dialog.html',
                    controller: 'Emission_particularsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Emission_particulars', function(Emission_particulars) {
                            return Emission_particulars.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emission-particulars', null, { reload: 'emission-particulars' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

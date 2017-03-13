(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('emissiondetail', {
            parent: 'entity',
            url: '/emissiondetail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emissiondetail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emissiondetail/emissiondetails.html',
                    controller: 'EmissiondetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emissiondetail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('emissiondetail-detail', {
            parent: 'emissiondetail',
            url: '/emissiondetail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.emissiondetail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/emissiondetail/emissiondetail-detail.html',
                    controller: 'EmissiondetailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('emissiondetail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Emissiondetail', function($stateParams, Emissiondetail) {
                    return Emissiondetail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'emissiondetail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('emissiondetail-detail.edit', {
            parent: 'emissiondetail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emissiondetail/emissiondetail-dialog.html',
                    controller: 'EmissiondetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emissiondetail', function(Emissiondetail) {
                            return Emissiondetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emissiondetail.new', {
            parent: 'emissiondetail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emissiondetail/emissiondetail-dialog.html',
                    controller: 'EmissiondetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                particulars: null,
                                capacity: null,
                                type_of_fuel: null,
                                air_pollution_control_device: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('emissiondetail', null, { reload: 'emissiondetail' });
                }, function() {
                    $state.go('emissiondetail');
                });
            }]
        })
        .state('emissiondetail.edit', {
            parent: 'emissiondetail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emissiondetail/emissiondetail-dialog.html',
                    controller: 'EmissiondetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Emissiondetail', function(Emissiondetail) {
                            return Emissiondetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emissiondetail', null, { reload: 'emissiondetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('emissiondetail.delete', {
            parent: 'emissiondetail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/emissiondetail/emissiondetail-delete-dialog.html',
                    controller: 'EmissiondetailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Emissiondetail', function(Emissiondetail) {
                            return Emissiondetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('emissiondetail', null, { reload: 'emissiondetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

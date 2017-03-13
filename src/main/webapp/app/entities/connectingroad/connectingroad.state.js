(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('connectingroad', {
            parent: 'entity',
            url: '/connectingroad',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.connectingroad.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/connectingroad/connectingroads.html',
                    controller: 'ConnectingroadController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('connectingroad');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('connectingroad-detail', {
            parent: 'connectingroad',
            url: '/connectingroad/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.connectingroad.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/connectingroad/connectingroad-detail.html',
                    controller: 'ConnectingroadDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('connectingroad');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Connectingroad', function($stateParams, Connectingroad) {
                    return Connectingroad.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'connectingroad',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('connectingroad-detail.edit', {
            parent: 'connectingroad-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/connectingroad/connectingroad-dialog.html',
                    controller: 'ConnectingroadDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Connectingroad', function(Connectingroad) {
                            return Connectingroad.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('connectingroad.new', {
            parent: 'connectingroad',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/connectingroad/connectingroad-dialog.html',
                    controller: 'ConnectingroadDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                connectingroadtype: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('connectingroad', null, { reload: 'connectingroad' });
                }, function() {
                    $state.go('connectingroad');
                });
            }]
        })
        .state('connectingroad.edit', {
            parent: 'connectingroad',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/connectingroad/connectingroad-dialog.html',
                    controller: 'ConnectingroadDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Connectingroad', function(Connectingroad) {
                            return Connectingroad.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('connectingroad', null, { reload: 'connectingroad' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('connectingroad.delete', {
            parent: 'connectingroad',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/connectingroad/connectingroad-delete-dialog.html',
                    controller: 'ConnectingroadDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Connectingroad', function(Connectingroad) {
                            return Connectingroad.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('connectingroad', null, { reload: 'connectingroad' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

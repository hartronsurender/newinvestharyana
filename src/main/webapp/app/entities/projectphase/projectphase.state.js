(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectphase', {
            parent: 'entity',
            url: '/projectphase',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectphase.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectphase/projectphases.html',
                    controller: 'ProjectphaseController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectphase');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectphase-detail', {
            parent: 'projectphase',
            url: '/projectphase/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectphase.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectphase/projectphase-detail.html',
                    controller: 'ProjectphaseDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectphase');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectphase', function($stateParams, Projectphase) {
                    return Projectphase.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectphase',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectphase-detail.edit', {
            parent: 'projectphase-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectphase/projectphase-dialog.html',
                    controller: 'ProjectphaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectphase', function(Projectphase) {
                            return Projectphase.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectphase.new', {
            parent: 'projectphase',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectphase/projectphase-dialog.html',
                    controller: 'ProjectphaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                fciphase: null,
                                fci_product_category: null,
                                fci: null,
                                fic_implementation_date: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectphase', null, { reload: 'projectphase' });
                }, function() {
                    $state.go('projectphase');
                });
            }]
        })
        .state('projectphase.edit', {
            parent: 'projectphase',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectphase/projectphase-dialog.html',
                    controller: 'ProjectphaseDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectphase', function(Projectphase) {
                            return Projectphase.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectphase', null, { reload: 'projectphase' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectphase.delete', {
            parent: 'projectphase',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectphase/projectphase-delete-dialog.html',
                    controller: 'ProjectphaseDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectphase', function(Projectphase) {
                            return Projectphase.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectphase', null, { reload: 'projectphase' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

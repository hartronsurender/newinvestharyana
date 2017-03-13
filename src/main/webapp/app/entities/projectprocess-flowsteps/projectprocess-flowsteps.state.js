(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectprocess-flowsteps', {
            parent: 'entity',
            url: '/projectprocess-flowsteps',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectprocess_flowsteps.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectprocess-flowsteps/projectprocess-flowsteps.html',
                    controller: 'Projectprocess_flowstepsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectprocess_flowsteps');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectprocess-flowsteps-detail', {
            parent: 'projectprocess-flowsteps',
            url: '/projectprocess-flowsteps/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectprocess_flowsteps.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectprocess-flowsteps/projectprocess-flowsteps-detail.html',
                    controller: 'Projectprocess_flowstepsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectprocess_flowsteps');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectprocess_flowsteps', function($stateParams, Projectprocess_flowsteps) {
                    return Projectprocess_flowsteps.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectprocess-flowsteps',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectprocess-flowsteps-detail.edit', {
            parent: 'projectprocess-flowsteps-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectprocess-flowsteps/projectprocess-flowsteps-dialog.html',
                    controller: 'Projectprocess_flowstepsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectprocess_flowsteps', function(Projectprocess_flowsteps) {
                            return Projectprocess_flowsteps.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectprocess-flowsteps.new', {
            parent: 'projectprocess-flowsteps',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectprocess-flowsteps/projectprocess-flowsteps-dialog.html',
                    controller: 'Projectprocess_flowstepsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                process_flow_document: null,
                                process_flow_documentContentType: null,
                                steps: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectprocess-flowsteps', null, { reload: 'projectprocess-flowsteps' });
                }, function() {
                    $state.go('projectprocess-flowsteps');
                });
            }]
        })
        .state('projectprocess-flowsteps.edit', {
            parent: 'projectprocess-flowsteps',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectprocess-flowsteps/projectprocess-flowsteps-dialog.html',
                    controller: 'Projectprocess_flowstepsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectprocess_flowsteps', function(Projectprocess_flowsteps) {
                            return Projectprocess_flowsteps.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectprocess-flowsteps', null, { reload: 'projectprocess-flowsteps' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectprocess-flowsteps.delete', {
            parent: 'projectprocess-flowsteps',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectprocess-flowsteps/projectprocess-flowsteps-delete-dialog.html',
                    controller: 'Projectprocess_flowstepsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectprocess_flowsteps', function(Projectprocess_flowsteps) {
                            return Projectprocess_flowsteps.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectprocess-flowsteps', null, { reload: 'projectprocess-flowsteps' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

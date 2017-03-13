(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('foreignfundingresource', {
            parent: 'entity',
            url: '/foreignfundingresource',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.foreignfundingresource.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/foreignfundingresource/foreignfundingresources.html',
                    controller: 'ForeignfundingresourceController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('foreignfundingresource');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('foreignfundingresource-detail', {
            parent: 'foreignfundingresource',
            url: '/foreignfundingresource/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.foreignfundingresource.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/foreignfundingresource/foreignfundingresource-detail.html',
                    controller: 'ForeignfundingresourceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('foreignfundingresource');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Foreignfundingresource', function($stateParams, Foreignfundingresource) {
                    return Foreignfundingresource.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'foreignfundingresource',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('foreignfundingresource-detail.edit', {
            parent: 'foreignfundingresource-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/foreignfundingresource/foreignfundingresource-dialog.html',
                    controller: 'ForeignfundingresourceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Foreignfundingresource', function(Foreignfundingresource) {
                            return Foreignfundingresource.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('foreignfundingresource.new', {
            parent: 'foreignfundingresource',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/foreignfundingresource/foreignfundingresource-dialog.html',
                    controller: 'ForeignfundingresourceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                foreign_funding_resource_type: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('foreignfundingresource', null, { reload: 'foreignfundingresource' });
                }, function() {
                    $state.go('foreignfundingresource');
                });
            }]
        })
        .state('foreignfundingresource.edit', {
            parent: 'foreignfundingresource',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/foreignfundingresource/foreignfundingresource-dialog.html',
                    controller: 'ForeignfundingresourceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Foreignfundingresource', function(Foreignfundingresource) {
                            return Foreignfundingresource.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('foreignfundingresource', null, { reload: 'foreignfundingresource' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('foreignfundingresource.delete', {
            parent: 'foreignfundingresource',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/foreignfundingresource/foreignfundingresource-delete-dialog.html',
                    controller: 'ForeignfundingresourceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Foreignfundingresource', function(Foreignfundingresource) {
                            return Foreignfundingresource.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('foreignfundingresource', null, { reload: 'foreignfundingresource' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('landzoneclassification', {
            parent: 'entity',
            url: '/landzoneclassification',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.landzoneclassification.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/landzoneclassification/landzoneclassifications.html',
                    controller: 'LandzoneclassificationController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('landzoneclassification');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('landzoneclassification-detail', {
            parent: 'landzoneclassification',
            url: '/landzoneclassification/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.landzoneclassification.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/landzoneclassification/landzoneclassification-detail.html',
                    controller: 'LandzoneclassificationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('landzoneclassification');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Landzoneclassification', function($stateParams, Landzoneclassification) {
                    return Landzoneclassification.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'landzoneclassification',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('landzoneclassification-detail.edit', {
            parent: 'landzoneclassification-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/landzoneclassification/landzoneclassification-dialog.html',
                    controller: 'LandzoneclassificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Landzoneclassification', function(Landzoneclassification) {
                            return Landzoneclassification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('landzoneclassification.new', {
            parent: 'landzoneclassification',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/landzoneclassification/landzoneclassification-dialog.html',
                    controller: 'LandzoneclassificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                land_zone_classification: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('landzoneclassification', null, { reload: 'landzoneclassification' });
                }, function() {
                    $state.go('landzoneclassification');
                });
            }]
        })
        .state('landzoneclassification.edit', {
            parent: 'landzoneclassification',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/landzoneclassification/landzoneclassification-dialog.html',
                    controller: 'LandzoneclassificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Landzoneclassification', function(Landzoneclassification) {
                            return Landzoneclassification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('landzoneclassification', null, { reload: 'landzoneclassification' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('landzoneclassification.delete', {
            parent: 'landzoneclassification',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/landzoneclassification/landzoneclassification-delete-dialog.html',
                    controller: 'LandzoneclassificationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Landzoneclassification', function(Landzoneclassification) {
                            return Landzoneclassification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('landzoneclassification', null, { reload: 'landzoneclassification' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

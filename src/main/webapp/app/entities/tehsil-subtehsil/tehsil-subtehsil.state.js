(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tehsil-subtehsil', {
            parent: 'entity',
            url: '/tehsil-subtehsil',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.tehsil_subtehsil.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tehsil-subtehsil/tehsil-subtehsils.html',
                    controller: 'Tehsil_subtehsilController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tehsil_subtehsil');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tehsil-subtehsil-detail', {
            parent: 'tehsil-subtehsil',
            url: '/tehsil-subtehsil/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.tehsil_subtehsil.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tehsil-subtehsil/tehsil-subtehsil-detail.html',
                    controller: 'Tehsil_subtehsilDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tehsil_subtehsil');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tehsil_subtehsil', function($stateParams, Tehsil_subtehsil) {
                    return Tehsil_subtehsil.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tehsil-subtehsil',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tehsil-subtehsil-detail.edit', {
            parent: 'tehsil-subtehsil-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tehsil-subtehsil/tehsil-subtehsil-dialog.html',
                    controller: 'Tehsil_subtehsilDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tehsil_subtehsil', function(Tehsil_subtehsil) {
                            return Tehsil_subtehsil.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tehsil-subtehsil.new', {
            parent: 'tehsil-subtehsil',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tehsil-subtehsil/tehsil-subtehsil-dialog.html',
                    controller: 'Tehsil_subtehsilDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                districtid: null,
                                blockid: null,
                                city_town_villageid: null,
                                tehsilname: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tehsil-subtehsil', null, { reload: 'tehsil-subtehsil' });
                }, function() {
                    $state.go('tehsil-subtehsil');
                });
            }]
        })
        .state('tehsil-subtehsil.edit', {
            parent: 'tehsil-subtehsil',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tehsil-subtehsil/tehsil-subtehsil-dialog.html',
                    controller: 'Tehsil_subtehsilDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tehsil_subtehsil', function(Tehsil_subtehsil) {
                            return Tehsil_subtehsil.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tehsil-subtehsil', null, { reload: 'tehsil-subtehsil' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tehsil-subtehsil.delete', {
            parent: 'tehsil-subtehsil',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tehsil-subtehsil/tehsil-subtehsil-delete-dialog.html',
                    controller: 'Tehsil_subtehsilDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tehsil_subtehsil', function(Tehsil_subtehsil) {
                            return Tehsil_subtehsil.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tehsil-subtehsil', null, { reload: 'tehsil-subtehsil' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

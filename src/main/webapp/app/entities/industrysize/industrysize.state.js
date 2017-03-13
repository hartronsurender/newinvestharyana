(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('industrysize', {
            parent: 'entity',
            url: '/industrysize',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.industrysize.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/industrysize/industrysizes.html',
                    controller: 'IndustrysizeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('industrysize');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('industrysize-detail', {
            parent: 'industrysize',
            url: '/industrysize/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.industrysize.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/industrysize/industrysize-detail.html',
                    controller: 'IndustrysizeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('industrysize');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Industrysize', function($stateParams, Industrysize) {
                    return Industrysize.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'industrysize',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('industrysize-detail.edit', {
            parent: 'industrysize-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/industrysize/industrysize-dialog.html',
                    controller: 'IndustrysizeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Industrysize', function(Industrysize) {
                            return Industrysize.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('industrysize.new', {
            parent: 'industrysize',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/industrysize/industrysize-dialog.html',
                    controller: 'IndustrysizeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                size_of_industry: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('industrysize', null, { reload: 'industrysize' });
                }, function() {
                    $state.go('industrysize');
                });
            }]
        })
        .state('industrysize.edit', {
            parent: 'industrysize',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/industrysize/industrysize-dialog.html',
                    controller: 'IndustrysizeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Industrysize', function(Industrysize) {
                            return Industrysize.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('industrysize', null, { reload: 'industrysize' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('industrysize.delete', {
            parent: 'industrysize',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/industrysize/industrysize-delete-dialog.html',
                    controller: 'IndustrysizeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Industrysize', function(Industrysize) {
                            return Industrysize.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('industrysize', null, { reload: 'industrysize' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

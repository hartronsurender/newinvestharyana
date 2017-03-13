(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('accept-verification', {
            parent: 'entity',
            url: '/accept-verification',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.accept_verification.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/accept-verification/accept-verifications.html',
                    controller: 'Accept_verificationController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('accept_verification');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('accept-verification-detail', {
            parent: 'accept-verification',
            url: '/accept-verification/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.accept_verification.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/accept-verification/accept-verification-detail.html',
                    controller: 'Accept_verificationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('accept_verification');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Accept_verification', function($stateParams, Accept_verification) {
                    return Accept_verification.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'accept-verification',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('accept-verification-detail.edit', {
            parent: 'accept-verification-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/accept-verification/accept-verification-dialog.html',
                    controller: 'Accept_verificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Accept_verification', function(Accept_verification) {
                            return Accept_verification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('accept-verification.new', {
            parent: 'accept-verification',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/accept-verification/accept-verification-dialog.html',
                    controller: 'Accept_verificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                investorid: null,
                                acceptcondition: null,
                                applicationdate: null,
                                signature_document: null,
                                signature_documentContentType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('accept-verification', null, { reload: 'accept-verification' });
                }, function() {
                    $state.go('accept-verification');
                });
            }]
        })
        .state('accept-verification.edit', {
            parent: 'accept-verification',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/accept-verification/accept-verification-dialog.html',
                    controller: 'Accept_verificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Accept_verification', function(Accept_verification) {
                            return Accept_verification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('accept-verification', null, { reload: 'accept-verification' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('accept-verification.delete', {
            parent: 'accept-verification',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/accept-verification/accept-verification-delete-dialog.html',
                    controller: 'Accept_verificationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Accept_verification', function(Accept_verification) {
                            return Accept_verification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('accept-verification', null, { reload: 'accept-verification' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

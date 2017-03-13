(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('companydetail', {
            parent: 'entity',
            url: '/companydetail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.companydetail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/companydetail/companydetails.html',
                    controller: 'CompanydetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('companydetail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('companydetail-detail', {
            parent: 'companydetail',
            url: '/companydetail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.companydetail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/companydetail/companydetail-detail.html',
                    controller: 'CompanydetailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('companydetail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Companydetail', function($stateParams, Companydetail) {
                    return Companydetail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'companydetail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('companydetail-detail.edit', {
            parent: 'companydetail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/companydetail/companydetail-dialog.html',
                    controller: 'CompanydetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Companydetail', function(Companydetail) {
                            return Companydetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('companydetail.new', {
            parent: 'companydetail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/companydetail/companydetail-dialog.html',
                    controller: 'CompanydetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                investorid: null,
                                promoter_md_director: null,
                                designation: null,
                                businessentity: null,
                                businesstype: null,
                                number_of_director_mds_ceo: null,
                                list_director_md_ceo: null,
                                list_director_md_ceoContentType: null,
                                pannumber: null,
                                pancard: null,
                                pancardContentType: null,
                                aadharnumber: null,
                                aadharcard: null,
                                aadharcardContentType: null,
                                nri: null,
                                tin_vat_no: null,
                                tin_vat_document: null,
                                tin_vat_documentContentType: null,
                                cstno: null,
                                cstdocument: null,
                                cstdocumentContentType: null,
                                moa_partnershipdeed_document: null,
                                moa_partnershipdeed_documentContentType: null,
                                registration_document: null,
                                registration_documentContentType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('companydetail', null, { reload: 'companydetail' });
                }, function() {
                    $state.go('companydetail');
                });
            }]
        })
        .state('companydetail.edit', {
            parent: 'companydetail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/companydetail/companydetail-dialog.html',
                    controller: 'CompanydetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Companydetail', function(Companydetail) {
                            return Companydetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('companydetail', null, { reload: 'companydetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('companydetail.delete', {
            parent: 'companydetail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/companydetail/companydetail-delete-dialog.html',
                    controller: 'CompanydetailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Companydetail', function(Companydetail) {
                            return Companydetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('companydetail', null, { reload: 'companydetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

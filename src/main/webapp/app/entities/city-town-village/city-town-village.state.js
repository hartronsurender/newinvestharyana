(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('city-town-village', {
            parent: 'entity',
            url: '/city-town-village',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.city_town_village.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/city-town-village/city-town-villages.html',
                    controller: 'City_town_villageController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('city_town_village');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('city-town-village-detail', {
            parent: 'city-town-village',
            url: '/city-town-village/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.city_town_village.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/city-town-village/city-town-village-detail.html',
                    controller: 'City_town_villageDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('city_town_village');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'City_town_village', function($stateParams, City_town_village) {
                    return City_town_village.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'city-town-village',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('city-town-village-detail.edit', {
            parent: 'city-town-village-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-town-village/city-town-village-dialog.html',
                    controller: 'City_town_villageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['City_town_village', function(City_town_village) {
                            return City_town_village.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('city-town-village.new', {
            parent: 'city-town-village',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-town-village/city-town-village-dialog.html',
                    controller: 'City_town_villageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                stateid: null,
                                districtid: null,
                                blockid: null,
                                city_town_village_name: null,
                                description: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('city-town-village', null, { reload: 'city-town-village' });
                }, function() {
                    $state.go('city-town-village');
                });
            }]
        })
        .state('city-town-village.edit', {
            parent: 'city-town-village',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-town-village/city-town-village-dialog.html',
                    controller: 'City_town_villageDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['City_town_village', function(City_town_village) {
                            return City_town_village.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('city-town-village', null, { reload: 'city-town-village' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('city-town-village.delete', {
            parent: 'city-town-village',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/city-town-village/city-town-village-delete-dialog.html',
                    controller: 'City_town_villageDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['City_town_village', function(City_town_village) {
                            return City_town_village.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('city-town-village', null, { reload: 'city-town-village' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

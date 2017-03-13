(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('City_town_villageDetailController', City_town_villageDetailController);

    City_town_villageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'City_town_village'];

    function City_town_villageDetailController($scope, $rootScope, $stateParams, previousState, entity, City_town_village) {
        var vm = this;

        vm.city_town_village = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:city_town_villageUpdate', function(event, result) {
            vm.city_town_village = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

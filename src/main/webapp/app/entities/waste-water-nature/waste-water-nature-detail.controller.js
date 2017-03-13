(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_natureDetailController', Waste_water_natureDetailController);

    Waste_water_natureDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Waste_water_nature'];

    function Waste_water_natureDetailController($scope, $rootScope, $stateParams, previousState, entity, Waste_water_nature) {
        var vm = this;

        vm.waste_water_nature = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:waste_water_natureUpdate', function(event, result) {
            vm.waste_water_nature = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

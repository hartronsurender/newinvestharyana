(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_disposal_modeDetailController', Waste_water_disposal_modeDetailController);

    Waste_water_disposal_modeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Waste_water_disposal_mode'];

    function Waste_water_disposal_modeDetailController($scope, $rootScope, $stateParams, previousState, entity, Waste_water_disposal_mode) {
        var vm = this;

        vm.waste_water_disposal_mode = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:waste_water_disposal_modeUpdate', function(event, result) {
            vm.waste_water_disposal_mode = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

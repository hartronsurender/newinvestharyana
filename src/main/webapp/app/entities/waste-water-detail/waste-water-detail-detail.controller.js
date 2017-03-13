(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_detailDetailController', Waste_water_detailDetailController);

    Waste_water_detailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Waste_water_detail'];

    function Waste_water_detailDetailController($scope, $rootScope, $stateParams, previousState, entity, Waste_water_detail) {
        var vm = this;

        vm.waste_water_detail = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:waste_water_detailUpdate', function(event, result) {
            vm.waste_water_detail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

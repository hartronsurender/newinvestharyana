(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_fuel_typeDetailController', Emission_fuel_typeDetailController);

    Emission_fuel_typeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Emission_fuel_type'];

    function Emission_fuel_typeDetailController($scope, $rootScope, $stateParams, previousState, entity, Emission_fuel_type) {
        var vm = this;

        vm.emission_fuel_type = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:emission_fuel_typeUpdate', function(event, result) {
            vm.emission_fuel_type = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

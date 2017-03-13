(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Water_supply_sourceDetailController', Water_supply_sourceDetailController);

    Water_supply_sourceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Water_supply_source'];

    function Water_supply_sourceDetailController($scope, $rootScope, $stateParams, previousState, entity, Water_supply_source) {
        var vm = this;

        vm.water_supply_source = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:water_supply_sourceUpdate', function(event, result) {
            vm.water_supply_source = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

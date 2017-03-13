(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Disposal_for_dischargeDetailController', Disposal_for_dischargeDetailController);

    Disposal_for_dischargeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Disposal_for_discharge'];

    function Disposal_for_dischargeDetailController($scope, $rootScope, $stateParams, previousState, entity, Disposal_for_discharge) {
        var vm = this;

        vm.disposal_for_discharge = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:disposal_for_dischargeUpdate', function(event, result) {
            vm.disposal_for_discharge = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

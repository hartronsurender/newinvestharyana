(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Disposal_for_dischargeController', Disposal_for_dischargeController);

    Disposal_for_dischargeController.$inject = ['Disposal_for_discharge'];

    function Disposal_for_dischargeController(Disposal_for_discharge) {

        var vm = this;

        vm.disposal_for_discharges = [];

        loadAll();

        function loadAll() {
            Disposal_for_discharge.query(function(result) {
                vm.disposal_for_discharges = result;
                vm.searchQuery = null;
            });
        }
    }
})();

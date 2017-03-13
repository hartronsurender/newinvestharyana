(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_disposal_modeController', Waste_water_disposal_modeController);

    Waste_water_disposal_modeController.$inject = ['Waste_water_disposal_mode'];

    function Waste_water_disposal_modeController(Waste_water_disposal_mode) {

        var vm = this;

        vm.waste_water_disposal_modes = [];

        loadAll();

        function loadAll() {
            Waste_water_disposal_mode.query(function(result) {
                vm.waste_water_disposal_modes = result;
                vm.searchQuery = null;
            });
        }
    }
})();

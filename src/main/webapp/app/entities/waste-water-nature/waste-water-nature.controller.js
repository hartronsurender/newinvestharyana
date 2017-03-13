(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_natureController', Waste_water_natureController);

    Waste_water_natureController.$inject = ['Waste_water_nature'];

    function Waste_water_natureController(Waste_water_nature) {

        var vm = this;

        vm.waste_water_natures = [];

        loadAll();

        function loadAll() {
            Waste_water_nature.query(function(result) {
                vm.waste_water_natures = result;
                vm.searchQuery = null;
            });
        }
    }
})();

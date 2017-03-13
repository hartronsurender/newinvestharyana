(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Water_supply_sourceController', Water_supply_sourceController);

    Water_supply_sourceController.$inject = ['Water_supply_source'];

    function Water_supply_sourceController(Water_supply_source) {

        var vm = this;

        vm.water_supply_sources = [];

        loadAll();

        function loadAll() {
            Water_supply_source.query(function(result) {
                vm.water_supply_sources = result;
                vm.searchQuery = null;
            });
        }
    }
})();

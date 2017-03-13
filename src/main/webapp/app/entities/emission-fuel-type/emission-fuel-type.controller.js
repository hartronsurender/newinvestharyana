(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_fuel_typeController', Emission_fuel_typeController);

    Emission_fuel_typeController.$inject = ['Emission_fuel_type'];

    function Emission_fuel_typeController(Emission_fuel_type) {

        var vm = this;

        vm.emission_fuel_types = [];

        loadAll();

        function loadAll() {
            Emission_fuel_type.query(function(result) {
                vm.emission_fuel_types = result;
                vm.searchQuery = null;
            });
        }
    }
})();

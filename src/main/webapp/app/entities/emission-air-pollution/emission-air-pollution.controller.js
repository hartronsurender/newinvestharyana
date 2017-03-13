(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_air_pollutionController', Emission_air_pollutionController);

    Emission_air_pollutionController.$inject = ['Emission_air_pollution'];

    function Emission_air_pollutionController(Emission_air_pollution) {

        var vm = this;

        vm.emission_air_pollutions = [];

        loadAll();

        function loadAll() {
            Emission_air_pollution.query(function(result) {
                vm.emission_air_pollutions = result;
                vm.searchQuery = null;
            });
        }
    }
})();

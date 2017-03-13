(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_detailController', Waste_water_detailController);

    Waste_water_detailController.$inject = ['Waste_water_detail'];

    function Waste_water_detailController(Waste_water_detail) {

        var vm = this;

        vm.waste_water_details = [];

        loadAll();

        function loadAll() {
            Waste_water_detail.query(function(result) {
                vm.waste_water_details = result;
                vm.searchQuery = null;
            });
        }
    }
})();

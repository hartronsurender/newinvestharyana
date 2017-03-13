(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('SectorController', SectorController);

    SectorController.$inject = ['Sector'];

    function SectorController(Sector) {

        var vm = this;

        vm.sectors = [];

        loadAll();

        function loadAll() {
            Sector.query(function(result) {
                vm.sectors = result;
                vm.searchQuery = null;
            });
        }
    }
})();

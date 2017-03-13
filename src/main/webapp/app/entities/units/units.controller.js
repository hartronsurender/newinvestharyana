(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('UnitsController', UnitsController);

    UnitsController.$inject = ['Units'];

    function UnitsController(Units) {

        var vm = this;

        vm.units = [];

        loadAll();

        function loadAll() {
            Units.query(function(result) {
                vm.units = result;
                vm.searchQuery = null;
            });
        }
    }
})();

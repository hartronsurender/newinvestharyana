(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Manufactur_unitController', Manufactur_unitController);

    Manufactur_unitController.$inject = ['Manufactur_unit'];

    function Manufactur_unitController(Manufactur_unit) {

        var vm = this;

        vm.manufactur_units = [];

        loadAll();

        function loadAll() {
            Manufactur_unit.query(function(result) {
                vm.manufactur_units = result;
                vm.searchQuery = null;
            });
        }
    }
})();

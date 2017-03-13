(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('EmissiondetailController', EmissiondetailController);

    EmissiondetailController.$inject = ['Emissiondetail'];

    function EmissiondetailController(Emissiondetail) {

        var vm = this;

        vm.emissiondetails = [];

        loadAll();

        function loadAll() {
            Emissiondetail.query(function(result) {
                vm.emissiondetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();

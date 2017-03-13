(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_particularsController', Emission_particularsController);

    Emission_particularsController.$inject = ['Emission_particulars'];

    function Emission_particularsController(Emission_particulars) {

        var vm = this;

        vm.emission_particulars = [];

        loadAll();

        function loadAll() {
            Emission_particulars.query(function(result) {
                vm.emission_particulars = result;
                vm.searchQuery = null;
            });
        }
    }
})();

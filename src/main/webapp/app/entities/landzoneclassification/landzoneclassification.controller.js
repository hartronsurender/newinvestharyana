(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('LandzoneclassificationController', LandzoneclassificationController);

    LandzoneclassificationController.$inject = ['Landzoneclassification'];

    function LandzoneclassificationController(Landzoneclassification) {

        var vm = this;

        vm.landzoneclassifications = [];

        loadAll();

        function loadAll() {
            Landzoneclassification.query(function(result) {
                vm.landzoneclassifications = result;
                vm.searchQuery = null;
            });
        }
    }
})();

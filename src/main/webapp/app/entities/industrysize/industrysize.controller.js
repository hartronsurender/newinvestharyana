(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('IndustrysizeController', IndustrysizeController);

    IndustrysizeController.$inject = ['Industrysize'];

    function IndustrysizeController(Industrysize) {

        var vm = this;

        vm.industrysizes = [];

        loadAll();

        function loadAll() {
            Industrysize.query(function(result) {
                vm.industrysizes = result;
                vm.searchQuery = null;
            });
        }
    }
})();

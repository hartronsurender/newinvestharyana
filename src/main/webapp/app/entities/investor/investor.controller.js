(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('InvestorController', InvestorController);

    InvestorController.$inject = ['DataUtils', 'Investor'];

    function InvestorController(DataUtils, Investor) {

        var vm = this;

        vm.investors = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Investor.query(function(result) {
                vm.investors = result;
                vm.searchQuery = null;
            });
        }
    }
})();

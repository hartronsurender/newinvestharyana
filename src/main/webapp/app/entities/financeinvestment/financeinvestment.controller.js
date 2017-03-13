(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('FinanceinvestmentController', FinanceinvestmentController);

    FinanceinvestmentController.$inject = ['Financeinvestment'];

    function FinanceinvestmentController(Financeinvestment) {

        var vm = this;

        vm.financeinvestments = [];

        loadAll();

        function loadAll() {
            Financeinvestment.query(function(result) {
                vm.financeinvestments = result;
                vm.searchQuery = null;
            });
        }
    }
})();

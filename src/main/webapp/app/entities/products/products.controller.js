(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProductsController', ProductsController);

    ProductsController.$inject = ['Products'];

    function ProductsController(Products) {

        var vm = this;

        vm.products = [];

        loadAll();

        function loadAll() {
            Products.query(function(result) {
                vm.products = result;
                vm.searchQuery = null;
            });
        }
    }
})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ForeignfundingresourceController', ForeignfundingresourceController);

    ForeignfundingresourceController.$inject = ['Foreignfundingresource'];

    function ForeignfundingresourceController(Foreignfundingresource) {

        var vm = this;

        vm.foreignfundingresources = [];

        loadAll();

        function loadAll() {
            Foreignfundingresource.query(function(result) {
                vm.foreignfundingresources = result;
                vm.searchQuery = null;
            });
        }
    }
})();

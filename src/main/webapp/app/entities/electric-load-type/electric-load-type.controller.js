(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Electric_load_typeController', Electric_load_typeController);

    Electric_load_typeController.$inject = ['Electric_load_type'];

    function Electric_load_typeController(Electric_load_type) {

        var vm = this;

        vm.electric_load_types = [];

        loadAll();

        function loadAll() {
            Electric_load_type.query(function(result) {
                vm.electric_load_types = result;
                vm.searchQuery = null;
            });
        }
    }
})();

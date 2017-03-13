(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Tehsil_subtehsilController', Tehsil_subtehsilController);

    Tehsil_subtehsilController.$inject = ['Tehsil_subtehsil'];

    function Tehsil_subtehsilController(Tehsil_subtehsil) {

        var vm = this;

        vm.tehsil_subtehsils = [];

        loadAll();

        function loadAll() {
            Tehsil_subtehsil.query(function(result) {
                vm.tehsil_subtehsils = result;
                vm.searchQuery = null;
            });
        }
    }
})();

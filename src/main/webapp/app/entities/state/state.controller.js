(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('StateController', StateController);

    StateController.$inject = ['State'];

    function StateController(State) {

        var vm = this;

        vm.states = [];

        loadAll();

        function loadAll() {
            State.query(function(result) {
                vm.states = result;
                vm.searchQuery = null;
            });
        }
    }
})();

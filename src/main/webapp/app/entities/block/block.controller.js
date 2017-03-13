(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('BlockController', BlockController);

    BlockController.$inject = ['Block'];

    function BlockController(Block) {

        var vm = this;

        vm.blocks = [];

        loadAll();

        function loadAll() {
            Block.query(function(result) {
                vm.blocks = result;
                vm.searchQuery = null;
            });
        }
    }
})();

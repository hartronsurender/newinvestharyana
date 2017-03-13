(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Projectprocess_flowstepsController', Projectprocess_flowstepsController);

    Projectprocess_flowstepsController.$inject = ['DataUtils', 'Projectprocess_flowsteps'];

    function Projectprocess_flowstepsController(DataUtils, Projectprocess_flowsteps) {

        var vm = this;

        vm.projectprocess_flowsteps = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Projectprocess_flowsteps.query(function(result) {
                vm.projectprocess_flowsteps = result;
                vm.searchQuery = null;
            });
        }
    }
})();

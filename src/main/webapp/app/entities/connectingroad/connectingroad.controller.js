(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ConnectingroadController', ConnectingroadController);

    ConnectingroadController.$inject = ['Connectingroad'];

    function ConnectingroadController(Connectingroad) {

        var vm = this;

        vm.connectingroads = [];

        loadAll();

        function loadAll() {
            Connectingroad.query(function(result) {
                vm.connectingroads = result;
                vm.searchQuery = null;
            });
        }
    }
})();

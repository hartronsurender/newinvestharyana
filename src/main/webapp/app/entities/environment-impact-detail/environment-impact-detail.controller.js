(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Environment_impact_detailController', Environment_impact_detailController);

    Environment_impact_detailController.$inject = ['DataUtils', 'Environment_impact_detail'];

    function Environment_impact_detailController(DataUtils, Environment_impact_detail) {

        var vm = this;

        vm.environment_impact_details = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Environment_impact_detail.query(function(result) {
                vm.environment_impact_details = result;
                vm.searchQuery = null;
            });
        }
    }
})();

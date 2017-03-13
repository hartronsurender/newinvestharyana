(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_electricity_detailController', Project_electricity_detailController);

    Project_electricity_detailController.$inject = ['DataUtils', 'Project_electricity_detail'];

    function Project_electricity_detailController(DataUtils, Project_electricity_detail) {

        var vm = this;

        vm.project_electricity_details = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Project_electricity_detail.query(function(result) {
                vm.project_electricity_details = result;
                vm.searchQuery = null;
            });
        }
    }
})();

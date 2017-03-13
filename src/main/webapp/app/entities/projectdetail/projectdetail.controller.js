(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectdetailController', ProjectdetailController);

    ProjectdetailController.$inject = ['DataUtils', 'Projectdetail'];

    function ProjectdetailController(DataUtils, Projectdetail) {

        var vm = this;

        vm.projectdetails = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Projectdetail.query(function(result) {
                vm.projectdetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();

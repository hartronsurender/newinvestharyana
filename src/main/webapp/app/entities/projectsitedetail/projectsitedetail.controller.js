(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectsitedetailController', ProjectsitedetailController);

    ProjectsitedetailController.$inject = ['DataUtils', 'Projectsitedetail'];

    function ProjectsitedetailController(DataUtils, Projectsitedetail) {

        var vm = this;

        vm.projectsitedetails = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Projectsitedetail.query(function(result) {
                vm.projectsitedetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();

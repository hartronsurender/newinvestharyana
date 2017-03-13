(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjecttypeController', ProjecttypeController);

    ProjecttypeController.$inject = ['Projecttype'];

    function ProjecttypeController(Projecttype) {

        var vm = this;

        vm.projecttypes = [];

        loadAll();

        function loadAll() {
            Projecttype.query(function(result) {
                vm.projecttypes = result;
                vm.searchQuery = null;
            });
        }
    }
})();

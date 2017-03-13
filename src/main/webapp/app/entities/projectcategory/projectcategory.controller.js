(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectcategoryController', ProjectcategoryController);

    ProjectcategoryController.$inject = ['Projectcategory'];

    function ProjectcategoryController(Projectcategory) {

        var vm = this;

        vm.projectcategories = [];

        loadAll();

        function loadAll() {
            Projectcategory.query(function(result) {
                vm.projectcategories = result;
                vm.searchQuery = null;
            });
        }
    }
})();

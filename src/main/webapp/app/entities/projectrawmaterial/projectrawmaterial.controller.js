(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectrawmaterialController', ProjectrawmaterialController);

    ProjectrawmaterialController.$inject = ['Projectrawmaterial'];

    function ProjectrawmaterialController(Projectrawmaterial) {

        var vm = this;

        vm.projectrawmaterials = [];

        loadAll();

        function loadAll() {
            Projectrawmaterial.query(function(result) {
                vm.projectrawmaterials = result;
                vm.searchQuery = null;
            });
        }
    }
})();

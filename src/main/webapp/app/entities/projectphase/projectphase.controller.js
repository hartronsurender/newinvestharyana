(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectphaseController', ProjectphaseController);

    ProjectphaseController.$inject = ['Projectphase'];

    function ProjectphaseController(Projectphase) {

        var vm = this;

        vm.projectphases = [];

        loadAll();

        function loadAll() {
            Projectphase.query(function(result) {
                vm.projectphases = result;
                vm.searchQuery = null;
            });
        }
    }
})();

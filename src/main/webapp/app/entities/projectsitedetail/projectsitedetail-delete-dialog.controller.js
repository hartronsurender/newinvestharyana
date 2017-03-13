(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectsitedetailDeleteController',ProjectsitedetailDeleteController);

    ProjectsitedetailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectsitedetail'];

    function ProjectsitedetailDeleteController($uibModalInstance, entity, Projectsitedetail) {
        var vm = this;

        vm.projectsitedetail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectsitedetail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

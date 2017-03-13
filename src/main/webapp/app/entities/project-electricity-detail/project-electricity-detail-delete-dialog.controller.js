(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_electricity_detailDeleteController',Project_electricity_detailDeleteController);

    Project_electricity_detailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Project_electricity_detail'];

    function Project_electricity_detailDeleteController($uibModalInstance, entity, Project_electricity_detail) {
        var vm = this;

        vm.project_electricity_detail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Project_electricity_detail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

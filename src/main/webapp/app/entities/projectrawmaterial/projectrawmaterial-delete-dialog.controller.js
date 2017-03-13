(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectrawmaterialDeleteController',ProjectrawmaterialDeleteController);

    ProjectrawmaterialDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectrawmaterial'];

    function ProjectrawmaterialDeleteController($uibModalInstance, entity, Projectrawmaterial) {
        var vm = this;

        vm.projectrawmaterial = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectrawmaterial.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

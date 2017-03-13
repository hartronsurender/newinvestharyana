(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectphaseDeleteController',ProjectphaseDeleteController);

    ProjectphaseDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectphase'];

    function ProjectphaseDeleteController($uibModalInstance, entity, Projectphase) {
        var vm = this;

        vm.projectphase = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectphase.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

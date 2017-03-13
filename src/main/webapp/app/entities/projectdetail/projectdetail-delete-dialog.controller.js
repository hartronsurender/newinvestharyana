(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectdetailDeleteController',ProjectdetailDeleteController);

    ProjectdetailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectdetail'];

    function ProjectdetailDeleteController($uibModalInstance, entity, Projectdetail) {
        var vm = this;

        vm.projectdetail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectdetail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

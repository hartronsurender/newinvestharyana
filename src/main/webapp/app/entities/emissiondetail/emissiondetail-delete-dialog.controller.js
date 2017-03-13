(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('EmissiondetailDeleteController',EmissiondetailDeleteController);

    EmissiondetailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Emissiondetail'];

    function EmissiondetailDeleteController($uibModalInstance, entity, Emissiondetail) {
        var vm = this;

        vm.emissiondetail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Emissiondetail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

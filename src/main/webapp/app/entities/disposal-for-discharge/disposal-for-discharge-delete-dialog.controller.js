(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Disposal_for_dischargeDeleteController',Disposal_for_dischargeDeleteController);

    Disposal_for_dischargeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Disposal_for_discharge'];

    function Disposal_for_dischargeDeleteController($uibModalInstance, entity, Disposal_for_discharge) {
        var vm = this;

        vm.disposal_for_discharge = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Disposal_for_discharge.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

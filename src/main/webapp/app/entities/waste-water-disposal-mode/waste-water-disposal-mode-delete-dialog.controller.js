(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_disposal_modeDeleteController',Waste_water_disposal_modeDeleteController);

    Waste_water_disposal_modeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Waste_water_disposal_mode'];

    function Waste_water_disposal_modeDeleteController($uibModalInstance, entity, Waste_water_disposal_mode) {
        var vm = this;

        vm.waste_water_disposal_mode = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Waste_water_disposal_mode.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

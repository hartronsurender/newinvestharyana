(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_detailDeleteController',Waste_water_detailDeleteController);

    Waste_water_detailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Waste_water_detail'];

    function Waste_water_detailDeleteController($uibModalInstance, entity, Waste_water_detail) {
        var vm = this;

        vm.waste_water_detail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Waste_water_detail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

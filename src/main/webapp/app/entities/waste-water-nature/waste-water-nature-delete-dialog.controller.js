(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_natureDeleteController',Waste_water_natureDeleteController);

    Waste_water_natureDeleteController.$inject = ['$uibModalInstance', 'entity', 'Waste_water_nature'];

    function Waste_water_natureDeleteController($uibModalInstance, entity, Waste_water_nature) {
        var vm = this;

        vm.waste_water_nature = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Waste_water_nature.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

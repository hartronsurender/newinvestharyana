(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_fuel_typeDeleteController',Emission_fuel_typeDeleteController);

    Emission_fuel_typeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Emission_fuel_type'];

    function Emission_fuel_typeDeleteController($uibModalInstance, entity, Emission_fuel_type) {
        var vm = this;

        vm.emission_fuel_type = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Emission_fuel_type.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

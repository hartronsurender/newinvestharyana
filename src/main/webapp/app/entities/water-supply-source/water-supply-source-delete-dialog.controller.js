(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Water_supply_sourceDeleteController',Water_supply_sourceDeleteController);

    Water_supply_sourceDeleteController.$inject = ['$uibModalInstance', 'entity', 'Water_supply_source'];

    function Water_supply_sourceDeleteController($uibModalInstance, entity, Water_supply_source) {
        var vm = this;

        vm.water_supply_source = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Water_supply_source.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

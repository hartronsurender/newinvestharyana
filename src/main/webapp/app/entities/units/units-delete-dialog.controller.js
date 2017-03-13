(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('UnitsDeleteController',UnitsDeleteController);

    UnitsDeleteController.$inject = ['$uibModalInstance', 'entity', 'Units'];

    function UnitsDeleteController($uibModalInstance, entity, Units) {
        var vm = this;

        vm.units = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Units.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

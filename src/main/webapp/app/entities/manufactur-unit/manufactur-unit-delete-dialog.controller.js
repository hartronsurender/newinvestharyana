(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Manufactur_unitDeleteController',Manufactur_unitDeleteController);

    Manufactur_unitDeleteController.$inject = ['$uibModalInstance', 'entity', 'Manufactur_unit'];

    function Manufactur_unitDeleteController($uibModalInstance, entity, Manufactur_unit) {
        var vm = this;

        vm.manufactur_unit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Manufactur_unit.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

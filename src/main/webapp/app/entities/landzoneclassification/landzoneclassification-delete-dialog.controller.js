(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('LandzoneclassificationDeleteController',LandzoneclassificationDeleteController);

    LandzoneclassificationDeleteController.$inject = ['$uibModalInstance', 'entity', 'Landzoneclassification'];

    function LandzoneclassificationDeleteController($uibModalInstance, entity, Landzoneclassification) {
        var vm = this;

        vm.landzoneclassification = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Landzoneclassification.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

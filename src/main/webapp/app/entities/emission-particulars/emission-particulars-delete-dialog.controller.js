(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_particularsDeleteController',Emission_particularsDeleteController);

    Emission_particularsDeleteController.$inject = ['$uibModalInstance', 'entity', 'Emission_particulars'];

    function Emission_particularsDeleteController($uibModalInstance, entity, Emission_particulars) {
        var vm = this;

        vm.emission_particulars = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Emission_particulars.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

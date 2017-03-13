(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Projectprocess_flowstepsDeleteController',Projectprocess_flowstepsDeleteController);

    Projectprocess_flowstepsDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectprocess_flowsteps'];

    function Projectprocess_flowstepsDeleteController($uibModalInstance, entity, Projectprocess_flowsteps) {
        var vm = this;

        vm.projectprocess_flowsteps = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectprocess_flowsteps.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

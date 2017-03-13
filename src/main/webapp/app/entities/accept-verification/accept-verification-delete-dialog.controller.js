(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Accept_verificationDeleteController',Accept_verificationDeleteController);

    Accept_verificationDeleteController.$inject = ['$uibModalInstance', 'entity', 'Accept_verification'];

    function Accept_verificationDeleteController($uibModalInstance, entity, Accept_verification) {
        var vm = this;

        vm.accept_verification = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Accept_verification.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

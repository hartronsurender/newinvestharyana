(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ConnectingroadDeleteController',ConnectingroadDeleteController);

    ConnectingroadDeleteController.$inject = ['$uibModalInstance', 'entity', 'Connectingroad'];

    function ConnectingroadDeleteController($uibModalInstance, entity, Connectingroad) {
        var vm = this;

        vm.connectingroad = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Connectingroad.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

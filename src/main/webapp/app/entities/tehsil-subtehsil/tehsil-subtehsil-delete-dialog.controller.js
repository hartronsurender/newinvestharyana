(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Tehsil_subtehsilDeleteController',Tehsil_subtehsilDeleteController);

    Tehsil_subtehsilDeleteController.$inject = ['$uibModalInstance', 'entity', 'Tehsil_subtehsil'];

    function Tehsil_subtehsilDeleteController($uibModalInstance, entity, Tehsil_subtehsil) {
        var vm = this;

        vm.tehsil_subtehsil = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Tehsil_subtehsil.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

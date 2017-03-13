(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Electric_load_typeDeleteController',Electric_load_typeDeleteController);

    Electric_load_typeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Electric_load_type'];

    function Electric_load_typeDeleteController($uibModalInstance, entity, Electric_load_type) {
        var vm = this;

        vm.electric_load_type = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Electric_load_type.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

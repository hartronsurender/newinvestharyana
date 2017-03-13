(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Environment_impact_detailDeleteController',Environment_impact_detailDeleteController);

    Environment_impact_detailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Environment_impact_detail'];

    function Environment_impact_detailDeleteController($uibModalInstance, entity, Environment_impact_detail) {
        var vm = this;

        vm.environment_impact_detail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Environment_impact_detail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

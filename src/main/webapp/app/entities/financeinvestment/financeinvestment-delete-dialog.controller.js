(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('FinanceinvestmentDeleteController',FinanceinvestmentDeleteController);

    FinanceinvestmentDeleteController.$inject = ['$uibModalInstance', 'entity', 'Financeinvestment'];

    function FinanceinvestmentDeleteController($uibModalInstance, entity, Financeinvestment) {
        var vm = this;

        vm.financeinvestment = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Financeinvestment.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

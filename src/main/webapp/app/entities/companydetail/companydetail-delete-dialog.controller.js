(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('CompanydetailDeleteController',CompanydetailDeleteController);

    CompanydetailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Companydetail'];

    function CompanydetailDeleteController($uibModalInstance, entity, Companydetail) {
        var vm = this;

        vm.companydetail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Companydetail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('IndustrysizeDeleteController',IndustrysizeDeleteController);

    IndustrysizeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Industrysize'];

    function IndustrysizeDeleteController($uibModalInstance, entity, Industrysize) {
        var vm = this;

        vm.industrysize = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Industrysize.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

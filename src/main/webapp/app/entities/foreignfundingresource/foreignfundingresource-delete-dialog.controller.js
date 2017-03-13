(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ForeignfundingresourceDeleteController',ForeignfundingresourceDeleteController);

    ForeignfundingresourceDeleteController.$inject = ['$uibModalInstance', 'entity', 'Foreignfundingresource'];

    function ForeignfundingresourceDeleteController($uibModalInstance, entity, Foreignfundingresource) {
        var vm = this;

        vm.foreignfundingresource = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Foreignfundingresource.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

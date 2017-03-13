(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectcategoryDeleteController',ProjectcategoryDeleteController);

    ProjectcategoryDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectcategory'];

    function ProjectcategoryDeleteController($uibModalInstance, entity, Projectcategory) {
        var vm = this;

        vm.projectcategory = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectcategory.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

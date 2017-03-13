(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjecttypeDeleteController',ProjecttypeDeleteController);

    ProjecttypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projecttype'];

    function ProjecttypeDeleteController($uibModalInstance, entity, Projecttype) {
        var vm = this;

        vm.projecttype = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projecttype.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

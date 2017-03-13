(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectcategoryDialogController', ProjectcategoryDialogController);

    ProjectcategoryDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectcategory'];

    function ProjectcategoryDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectcategory) {
        var vm = this;

        vm.projectcategory = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.projectcategory.id !== null) {
                Projectcategory.update(vm.projectcategory, onSaveSuccess, onSaveError);
            } else {
                Projectcategory.save(vm.projectcategory, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectcategoryUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

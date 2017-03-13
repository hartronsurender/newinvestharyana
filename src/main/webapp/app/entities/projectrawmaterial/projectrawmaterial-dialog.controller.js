(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectrawmaterialDialogController', ProjectrawmaterialDialogController);

    ProjectrawmaterialDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectrawmaterial'];

    function ProjectrawmaterialDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectrawmaterial) {
        var vm = this;

        vm.projectrawmaterial = entity;
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
            if (vm.projectrawmaterial.id !== null) {
                Projectrawmaterial.update(vm.projectrawmaterial, onSaveSuccess, onSaveError);
            } else {
                Projectrawmaterial.save(vm.projectrawmaterial, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectrawmaterialUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

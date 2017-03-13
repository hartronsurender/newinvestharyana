(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjecttypeDialogController', ProjecttypeDialogController);

    ProjecttypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projecttype'];

    function ProjecttypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projecttype) {
        var vm = this;

        vm.projecttype = entity;
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
            if (vm.projecttype.id !== null) {
                Projecttype.update(vm.projecttype, onSaveSuccess, onSaveError);
            } else {
                Projecttype.save(vm.projecttype, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projecttypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

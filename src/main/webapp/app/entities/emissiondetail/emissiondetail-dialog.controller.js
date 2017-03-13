(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('EmissiondetailDialogController', EmissiondetailDialogController);

    EmissiondetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Emissiondetail'];

    function EmissiondetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Emissiondetail) {
        var vm = this;

        vm.emissiondetail = entity;
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
            if (vm.emissiondetail.id !== null) {
                Emissiondetail.update(vm.emissiondetail, onSaveSuccess, onSaveError);
            } else {
                Emissiondetail.save(vm.emissiondetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:emissiondetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

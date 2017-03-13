(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Disposal_for_dischargeDialogController', Disposal_for_dischargeDialogController);

    Disposal_for_dischargeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Disposal_for_discharge'];

    function Disposal_for_dischargeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Disposal_for_discharge) {
        var vm = this;

        vm.disposal_for_discharge = entity;
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
            if (vm.disposal_for_discharge.id !== null) {
                Disposal_for_discharge.update(vm.disposal_for_discharge, onSaveSuccess, onSaveError);
            } else {
                Disposal_for_discharge.save(vm.disposal_for_discharge, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:disposal_for_dischargeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

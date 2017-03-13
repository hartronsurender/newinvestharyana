(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_disposal_modeDialogController', Waste_water_disposal_modeDialogController);

    Waste_water_disposal_modeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Waste_water_disposal_mode'];

    function Waste_water_disposal_modeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Waste_water_disposal_mode) {
        var vm = this;

        vm.waste_water_disposal_mode = entity;
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
            if (vm.waste_water_disposal_mode.id !== null) {
                Waste_water_disposal_mode.update(vm.waste_water_disposal_mode, onSaveSuccess, onSaveError);
            } else {
                Waste_water_disposal_mode.save(vm.waste_water_disposal_mode, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:waste_water_disposal_modeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

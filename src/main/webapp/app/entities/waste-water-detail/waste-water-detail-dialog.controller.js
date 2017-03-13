(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_detailDialogController', Waste_water_detailDialogController);

    Waste_water_detailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Waste_water_detail'];

    function Waste_water_detailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Waste_water_detail) {
        var vm = this;

        vm.waste_water_detail = entity;
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
            if (vm.waste_water_detail.id !== null) {
                Waste_water_detail.update(vm.waste_water_detail, onSaveSuccess, onSaveError);
            } else {
                Waste_water_detail.save(vm.waste_water_detail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:waste_water_detailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

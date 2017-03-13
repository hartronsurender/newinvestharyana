(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_natureDialogController', Waste_water_natureDialogController);

    Waste_water_natureDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Waste_water_nature'];

    function Waste_water_natureDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Waste_water_nature) {
        var vm = this;

        vm.waste_water_nature = entity;
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
            if (vm.waste_water_nature.id !== null) {
                Waste_water_nature.update(vm.waste_water_nature, onSaveSuccess, onSaveError);
            } else {
                Waste_water_nature.save(vm.waste_water_nature, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:waste_water_natureUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

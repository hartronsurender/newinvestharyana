(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_fuel_typeDialogController', Emission_fuel_typeDialogController);

    Emission_fuel_typeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Emission_fuel_type'];

    function Emission_fuel_typeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Emission_fuel_type) {
        var vm = this;

        vm.emission_fuel_type = entity;
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
            if (vm.emission_fuel_type.id !== null) {
                Emission_fuel_type.update(vm.emission_fuel_type, onSaveSuccess, onSaveError);
            } else {
                Emission_fuel_type.save(vm.emission_fuel_type, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:emission_fuel_typeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

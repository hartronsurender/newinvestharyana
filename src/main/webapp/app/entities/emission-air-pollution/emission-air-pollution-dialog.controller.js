(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_air_pollutionDialogController', Emission_air_pollutionDialogController);

    Emission_air_pollutionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Emission_air_pollution'];

    function Emission_air_pollutionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Emission_air_pollution) {
        var vm = this;

        vm.emission_air_pollution = entity;
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
            if (vm.emission_air_pollution.id !== null) {
                Emission_air_pollution.update(vm.emission_air_pollution, onSaveSuccess, onSaveError);
            } else {
                Emission_air_pollution.save(vm.emission_air_pollution, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:emission_air_pollutionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Water_supply_sourceDialogController', Water_supply_sourceDialogController);

    Water_supply_sourceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Water_supply_source'];

    function Water_supply_sourceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Water_supply_source) {
        var vm = this;

        vm.water_supply_source = entity;
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
            if (vm.water_supply_source.id !== null) {
                Water_supply_source.update(vm.water_supply_source, onSaveSuccess, onSaveError);
            } else {
                Water_supply_source.save(vm.water_supply_source, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:water_supply_sourceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

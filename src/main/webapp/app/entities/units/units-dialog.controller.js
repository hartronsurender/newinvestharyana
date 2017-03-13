(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('UnitsDialogController', UnitsDialogController);

    UnitsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Units'];

    function UnitsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Units) {
        var vm = this;

        vm.units = entity;
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
            if (vm.units.id !== null) {
                Units.update(vm.units, onSaveSuccess, onSaveError);
            } else {
                Units.save(vm.units, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:unitsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Manufactur_unitDialogController', Manufactur_unitDialogController);

    Manufactur_unitDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Manufactur_unit'];

    function Manufactur_unitDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Manufactur_unit) {
        var vm = this;

        vm.manufactur_unit = entity;
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
            if (vm.manufactur_unit.id !== null) {
                Manufactur_unit.update(vm.manufactur_unit, onSaveSuccess, onSaveError);
            } else {
                Manufactur_unit.save(vm.manufactur_unit, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:manufactur_unitUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('LandzoneclassificationDialogController', LandzoneclassificationDialogController);

    LandzoneclassificationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Landzoneclassification'];

    function LandzoneclassificationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Landzoneclassification) {
        var vm = this;

        vm.landzoneclassification = entity;
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
            if (vm.landzoneclassification.id !== null) {
                Landzoneclassification.update(vm.landzoneclassification, onSaveSuccess, onSaveError);
            } else {
                Landzoneclassification.save(vm.landzoneclassification, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:landzoneclassificationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

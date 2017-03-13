(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_particularsDialogController', Emission_particularsDialogController);

    Emission_particularsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Emission_particulars'];

    function Emission_particularsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Emission_particulars) {
        var vm = this;

        vm.emission_particulars = entity;
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
            if (vm.emission_particulars.id !== null) {
                Emission_particulars.update(vm.emission_particulars, onSaveSuccess, onSaveError);
            } else {
                Emission_particulars.save(vm.emission_particulars, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:emission_particularsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

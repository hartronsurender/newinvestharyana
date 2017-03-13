(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Tehsil_subtehsilDialogController', Tehsil_subtehsilDialogController);

    Tehsil_subtehsilDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tehsil_subtehsil'];

    function Tehsil_subtehsilDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tehsil_subtehsil) {
        var vm = this;

        vm.tehsil_subtehsil = entity;
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
            if (vm.tehsil_subtehsil.id !== null) {
                Tehsil_subtehsil.update(vm.tehsil_subtehsil, onSaveSuccess, onSaveError);
            } else {
                Tehsil_subtehsil.save(vm.tehsil_subtehsil, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:tehsil_subtehsilUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

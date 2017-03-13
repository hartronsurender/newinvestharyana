(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Electric_load_typeDialogController', Electric_load_typeDialogController);

    Electric_load_typeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Electric_load_type'];

    function Electric_load_typeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Electric_load_type) {
        var vm = this;

        vm.electric_load_type = entity;
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
            if (vm.electric_load_type.id !== null) {
                Electric_load_type.update(vm.electric_load_type, onSaveSuccess, onSaveError);
            } else {
                Electric_load_type.save(vm.electric_load_type, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:electric_load_typeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

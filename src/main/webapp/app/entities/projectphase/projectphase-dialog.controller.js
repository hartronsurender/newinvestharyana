(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectphaseDialogController', ProjectphaseDialogController);

    ProjectphaseDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectphase'];

    function ProjectphaseDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectphase) {
        var vm = this;

        vm.projectphase = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.projectphase.id !== null) {
                Projectphase.update(vm.projectphase, onSaveSuccess, onSaveError);
            } else {
                Projectphase.save(vm.projectphase, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectphaseUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.fic_implementation_date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();

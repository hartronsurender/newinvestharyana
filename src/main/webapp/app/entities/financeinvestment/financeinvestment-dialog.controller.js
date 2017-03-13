(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('FinanceinvestmentDialogController', FinanceinvestmentDialogController);

    FinanceinvestmentDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Financeinvestment'];

    function FinanceinvestmentDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Financeinvestment) {
        var vm = this;

        vm.financeinvestment = entity;
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
            if (vm.financeinvestment.id !== null) {
                Financeinvestment.update(vm.financeinvestment, onSaveSuccess, onSaveError);
            } else {
                Financeinvestment.save(vm.financeinvestment, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:financeinvestmentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.project_construction_start_date = false;
        vm.datePickerOpenStatus.project_commercial_activity_date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();

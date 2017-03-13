(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_electricity_detailDialogController', Project_electricity_detailDialogController);

    Project_electricity_detailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Project_electricity_detail'];

    function Project_electricity_detailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Project_electricity_detail) {
        var vm = this;

        vm.project_electricity_detail = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.project_electricity_detail.id !== null) {
                Project_electricity_detail.update(vm.project_electricity_detail, onSaveSuccess, onSaveError);
            } else {
                Project_electricity_detail.save(vm.project_electricity_detail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:project_electricity_detailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setTemporaryconnection = function ($file, project_electricity_detail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        project_electricity_detail.temporaryconnection = base64Data;
                        project_electricity_detail.temporaryconnectionContentType = $file.type;
                    });
                });
            }
        };
        vm.datePickerOpenStatus.load_demand_date = false;

        vm.setRegular_connection = function ($file, project_electricity_detail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        project_electricity_detail.regular_connection = base64Data;
                        project_electricity_detail.regular_connectionContentType = $file.type;
                    });
                });
            }
        };
        vm.datePickerOpenStatus.regular_demand_date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();

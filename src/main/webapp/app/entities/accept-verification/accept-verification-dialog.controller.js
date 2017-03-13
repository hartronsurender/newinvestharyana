(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Accept_verificationDialogController', Accept_verificationDialogController);

    Accept_verificationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Accept_verification'];

    function Accept_verificationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Accept_verification) {
        var vm = this;

        vm.accept_verification = entity;
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
            if (vm.accept_verification.id !== null) {
                Accept_verification.update(vm.accept_verification, onSaveSuccess, onSaveError);
            } else {
                Accept_verification.save(vm.accept_verification, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:accept_verificationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.applicationdate = false;

        vm.setSignature_document = function ($file, accept_verification) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        accept_verification.signature_document = base64Data;
                        accept_verification.signature_documentContentType = $file.type;
                    });
                });
            }
        };

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();

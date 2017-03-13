(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('CompanydetailDialogController', CompanydetailDialogController);

    CompanydetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Companydetail'];

    function CompanydetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Companydetail) {
        var vm = this;

        vm.companydetail = entity;
        vm.clear = clear;
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
            if (vm.companydetail.id !== null) {
                Companydetail.update(vm.companydetail, onSaveSuccess, onSaveError);
            } else {
                Companydetail.save(vm.companydetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:companydetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setList_director_md_ceo = function ($file, companydetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        companydetail.list_director_md_ceo = base64Data;
                        companydetail.list_director_md_ceoContentType = $file.type;
                    });
                });
            }
        };

        vm.setPancard = function ($file, companydetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        companydetail.pancard = base64Data;
                        companydetail.pancardContentType = $file.type;
                    });
                });
            }
        };

        vm.setAadharcard = function ($file, companydetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        companydetail.aadharcard = base64Data;
                        companydetail.aadharcardContentType = $file.type;
                    });
                });
            }
        };

        vm.setTin_vat_document = function ($file, companydetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        companydetail.tin_vat_document = base64Data;
                        companydetail.tin_vat_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setCstdocument = function ($file, companydetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        companydetail.cstdocument = base64Data;
                        companydetail.cstdocumentContentType = $file.type;
                    });
                });
            }
        };

        vm.setMoa_partnershipdeed_document = function ($file, companydetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        companydetail.moa_partnershipdeed_document = base64Data;
                        companydetail.moa_partnershipdeed_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setRegistration_document = function ($file, companydetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        companydetail.registration_document = base64Data;
                        companydetail.registration_documentContentType = $file.type;
                    });
                });
            }
        };

    }
})();

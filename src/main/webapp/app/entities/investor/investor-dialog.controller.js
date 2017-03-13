(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('InvestorDialogController', InvestorDialogController);

    InvestorDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Investor'];

    function InvestorDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Investor) {
        var vm = this;

        vm.investor = entity;
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
            if (vm.investor.id !== null) {
                Investor.update(vm.investor, onSaveSuccess, onSaveError);
            } else {
                Investor.save(vm.investor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:investorUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setMoudocument = function ($file, investor) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        investor.moudocument = base64Data;
                        investor.moudocumentContentType = $file.type;
                    });
                });
            }
        };

        vm.setInvestorphoto = function ($file, investor) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        investor.investorphoto = base64Data;
                        investor.investorphotoContentType = $file.type;
                    });
                });
            }
        };

    }
})();

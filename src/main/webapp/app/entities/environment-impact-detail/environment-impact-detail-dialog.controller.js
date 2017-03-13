(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Environment_impact_detailDialogController', Environment_impact_detailDialogController);

    Environment_impact_detailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Environment_impact_detail'];

    function Environment_impact_detailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Environment_impact_detail) {
        var vm = this;

        vm.environment_impact_detail = entity;
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
            if (vm.environment_impact_detail.id !== null) {
                Environment_impact_detail.update(vm.environment_impact_detail, onSaveSuccess, onSaveError);
            } else {
                Environment_impact_detail.save(vm.environment_impact_detail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:environment_impact_detailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setWaste_water_treatment_document = function ($file, environment_impact_detail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        environment_impact_detail.waste_water_treatment_document = base64Data;
                        environment_impact_detail.waste_water_treatment_documentContentType = $file.type;
                    });
                });
            }
        };

    }
})();

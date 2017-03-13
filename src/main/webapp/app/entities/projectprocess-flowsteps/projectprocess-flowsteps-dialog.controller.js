(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Projectprocess_flowstepsDialogController', Projectprocess_flowstepsDialogController);

    Projectprocess_flowstepsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Projectprocess_flowsteps'];

    function Projectprocess_flowstepsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Projectprocess_flowsteps) {
        var vm = this;

        vm.projectprocess_flowsteps = entity;
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
            if (vm.projectprocess_flowsteps.id !== null) {
                Projectprocess_flowsteps.update(vm.projectprocess_flowsteps, onSaveSuccess, onSaveError);
            } else {
                Projectprocess_flowsteps.save(vm.projectprocess_flowsteps, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectprocess_flowstepsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setProcess_flow_document = function ($file, projectprocess_flowsteps) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectprocess_flowsteps.process_flow_document = base64Data;
                        projectprocess_flowsteps.process_flow_documentContentType = $file.type;
                    });
                });
            }
        };

    }
})();

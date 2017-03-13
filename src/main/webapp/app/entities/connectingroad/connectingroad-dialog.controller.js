(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ConnectingroadDialogController', ConnectingroadDialogController);

    ConnectingroadDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Connectingroad'];

    function ConnectingroadDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Connectingroad) {
        var vm = this;

        vm.connectingroad = entity;
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
            if (vm.connectingroad.id !== null) {
                Connectingroad.update(vm.connectingroad, onSaveSuccess, onSaveError);
            } else {
                Connectingroad.save(vm.connectingroad, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:connectingroadUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

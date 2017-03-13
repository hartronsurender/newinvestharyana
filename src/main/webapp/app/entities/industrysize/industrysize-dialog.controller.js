(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('IndustrysizeDialogController', IndustrysizeDialogController);

    IndustrysizeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Industrysize'];

    function IndustrysizeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Industrysize) {
        var vm = this;

        vm.industrysize = entity;
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
            if (vm.industrysize.id !== null) {
                Industrysize.update(vm.industrysize, onSaveSuccess, onSaveError);
            } else {
                Industrysize.save(vm.industrysize, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:industrysizeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

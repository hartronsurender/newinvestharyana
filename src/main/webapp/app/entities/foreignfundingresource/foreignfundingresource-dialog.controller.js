(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ForeignfundingresourceDialogController', ForeignfundingresourceDialogController);

    ForeignfundingresourceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Foreignfundingresource'];

    function ForeignfundingresourceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Foreignfundingresource) {
        var vm = this;

        vm.foreignfundingresource = entity;
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
            if (vm.foreignfundingresource.id !== null) {
                Foreignfundingresource.update(vm.foreignfundingresource, onSaveSuccess, onSaveError);
            } else {
                Foreignfundingresource.save(vm.foreignfundingresource, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:foreignfundingresourceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

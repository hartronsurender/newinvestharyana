(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('City_town_villageDialogController', City_town_villageDialogController);

    City_town_villageDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'City_town_village'];

    function City_town_villageDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, City_town_village) {
        var vm = this;

        vm.city_town_village = entity;
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
            if (vm.city_town_village.id !== null) {
                City_town_village.update(vm.city_town_village, onSaveSuccess, onSaveError);
            } else {
                City_town_village.save(vm.city_town_village, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:city_town_villageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

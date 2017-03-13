(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('City_town_villageDeleteController',City_town_villageDeleteController);

    City_town_villageDeleteController.$inject = ['$uibModalInstance', 'entity', 'City_town_village'];

    function City_town_villageDeleteController($uibModalInstance, entity, City_town_village) {
        var vm = this;

        vm.city_town_village = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            City_town_village.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

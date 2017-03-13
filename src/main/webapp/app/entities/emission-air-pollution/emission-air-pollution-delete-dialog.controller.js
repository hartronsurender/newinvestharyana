(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_air_pollutionDeleteController',Emission_air_pollutionDeleteController);

    Emission_air_pollutionDeleteController.$inject = ['$uibModalInstance', 'entity', 'Emission_air_pollution'];

    function Emission_air_pollutionDeleteController($uibModalInstance, entity, Emission_air_pollution) {
        var vm = this;

        vm.emission_air_pollution = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Emission_air_pollution.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

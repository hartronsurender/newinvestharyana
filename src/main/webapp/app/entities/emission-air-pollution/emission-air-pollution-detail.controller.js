(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_air_pollutionDetailController', Emission_air_pollutionDetailController);

    Emission_air_pollutionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Emission_air_pollution'];

    function Emission_air_pollutionDetailController($scope, $rootScope, $stateParams, previousState, entity, Emission_air_pollution) {
        var vm = this;

        vm.emission_air_pollution = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:emission_air_pollutionUpdate', function(event, result) {
            vm.emission_air_pollution = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('EmissiondetailDetailController', EmissiondetailDetailController);

    EmissiondetailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Emissiondetail'];

    function EmissiondetailDetailController($scope, $rootScope, $stateParams, previousState, entity, Emissiondetail) {
        var vm = this;

        vm.emissiondetail = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:emissiondetailUpdate', function(event, result) {
            vm.emissiondetail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

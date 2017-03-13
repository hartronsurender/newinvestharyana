(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('UnitsDetailController', UnitsDetailController);

    UnitsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Units'];

    function UnitsDetailController($scope, $rootScope, $stateParams, previousState, entity, Units) {
        var vm = this;

        vm.units = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:unitsUpdate', function(event, result) {
            vm.units = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

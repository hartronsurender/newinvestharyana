(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Manufactur_unitDetailController', Manufactur_unitDetailController);

    Manufactur_unitDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Manufactur_unit'];

    function Manufactur_unitDetailController($scope, $rootScope, $stateParams, previousState, entity, Manufactur_unit) {
        var vm = this;

        vm.manufactur_unit = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:manufactur_unitUpdate', function(event, result) {
            vm.manufactur_unit = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

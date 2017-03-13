(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Electric_load_typeDetailController', Electric_load_typeDetailController);

    Electric_load_typeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Electric_load_type'];

    function Electric_load_typeDetailController($scope, $rootScope, $stateParams, previousState, entity, Electric_load_type) {
        var vm = this;

        vm.electric_load_type = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:electric_load_typeUpdate', function(event, result) {
            vm.electric_load_type = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

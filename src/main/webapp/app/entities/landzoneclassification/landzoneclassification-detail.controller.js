(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('LandzoneclassificationDetailController', LandzoneclassificationDetailController);

    LandzoneclassificationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Landzoneclassification'];

    function LandzoneclassificationDetailController($scope, $rootScope, $stateParams, previousState, entity, Landzoneclassification) {
        var vm = this;

        vm.landzoneclassification = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:landzoneclassificationUpdate', function(event, result) {
            vm.landzoneclassification = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

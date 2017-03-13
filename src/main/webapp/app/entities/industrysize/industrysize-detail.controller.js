(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('IndustrysizeDetailController', IndustrysizeDetailController);

    IndustrysizeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Industrysize'];

    function IndustrysizeDetailController($scope, $rootScope, $stateParams, previousState, entity, Industrysize) {
        var vm = this;

        vm.industrysize = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:industrysizeUpdate', function(event, result) {
            vm.industrysize = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

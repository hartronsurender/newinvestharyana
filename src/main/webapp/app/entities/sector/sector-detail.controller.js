(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('SectorDetailController', SectorDetailController);

    SectorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Sector'];

    function SectorDetailController($scope, $rootScope, $stateParams, previousState, entity, Sector) {
        var vm = this;

        vm.sector = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:sectorUpdate', function(event, result) {
            vm.sector = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

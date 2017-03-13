(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Environment_impact_detailDetailController', Environment_impact_detailDetailController);

    Environment_impact_detailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Environment_impact_detail'];

    function Environment_impact_detailDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Environment_impact_detail) {
        var vm = this;

        vm.environment_impact_detail = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('investhryApp:environment_impact_detailUpdate', function(event, result) {
            vm.environment_impact_detail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

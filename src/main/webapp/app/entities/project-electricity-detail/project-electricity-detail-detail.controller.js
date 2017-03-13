(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_electricity_detailDetailController', Project_electricity_detailDetailController);

    Project_electricity_detailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Project_electricity_detail'];

    function Project_electricity_detailDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Project_electricity_detail) {
        var vm = this;

        vm.project_electricity_detail = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('investhryApp:project_electricity_detailUpdate', function(event, result) {
            vm.project_electricity_detail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

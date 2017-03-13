(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectsitedetailDetailController', ProjectsitedetailDetailController);

    ProjectsitedetailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Projectsitedetail'];

    function ProjectsitedetailDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Projectsitedetail) {
        var vm = this;

        vm.projectsitedetail = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('investhryApp:projectsitedetailUpdate', function(event, result) {
            vm.projectsitedetail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

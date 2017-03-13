(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectrawmaterialDetailController', ProjectrawmaterialDetailController);

    ProjectrawmaterialDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectrawmaterial'];

    function ProjectrawmaterialDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectrawmaterial) {
        var vm = this;

        vm.projectrawmaterial = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectrawmaterialUpdate', function(event, result) {
            vm.projectrawmaterial = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

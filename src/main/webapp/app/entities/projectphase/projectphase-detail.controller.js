(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectphaseDetailController', ProjectphaseDetailController);

    ProjectphaseDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectphase'];

    function ProjectphaseDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectphase) {
        var vm = this;

        vm.projectphase = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectphaseUpdate', function(event, result) {
            vm.projectphase = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

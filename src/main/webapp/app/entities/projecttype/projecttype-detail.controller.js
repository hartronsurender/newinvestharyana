(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjecttypeDetailController', ProjecttypeDetailController);

    ProjecttypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projecttype'];

    function ProjecttypeDetailController($scope, $rootScope, $stateParams, previousState, entity, Projecttype) {
        var vm = this;

        vm.projecttype = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projecttypeUpdate', function(event, result) {
            vm.projecttype = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

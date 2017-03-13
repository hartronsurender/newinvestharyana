(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectcategoryDetailController', ProjectcategoryDetailController);

    ProjectcategoryDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectcategory'];

    function ProjectcategoryDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectcategory) {
        var vm = this;

        vm.projectcategory = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectcategoryUpdate', function(event, result) {
            vm.projectcategory = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

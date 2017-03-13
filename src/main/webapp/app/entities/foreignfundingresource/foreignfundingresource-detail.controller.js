(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ForeignfundingresourceDetailController', ForeignfundingresourceDetailController);

    ForeignfundingresourceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Foreignfundingresource'];

    function ForeignfundingresourceDetailController($scope, $rootScope, $stateParams, previousState, entity, Foreignfundingresource) {
        var vm = this;

        vm.foreignfundingresource = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:foreignfundingresourceUpdate', function(event, result) {
            vm.foreignfundingresource = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

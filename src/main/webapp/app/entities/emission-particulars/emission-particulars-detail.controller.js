(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emission_particularsDetailController', Emission_particularsDetailController);

    Emission_particularsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Emission_particulars'];

    function Emission_particularsDetailController($scope, $rootScope, $stateParams, previousState, entity, Emission_particulars) {
        var vm = this;

        vm.emission_particulars = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:emission_particularsUpdate', function(event, result) {
            vm.emission_particulars = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

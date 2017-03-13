(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ConnectingroadDetailController', ConnectingroadDetailController);

    ConnectingroadDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Connectingroad'];

    function ConnectingroadDetailController($scope, $rootScope, $stateParams, previousState, entity, Connectingroad) {
        var vm = this;

        vm.connectingroad = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:connectingroadUpdate', function(event, result) {
            vm.connectingroad = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

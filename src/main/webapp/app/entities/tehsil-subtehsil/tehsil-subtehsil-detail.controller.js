(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Tehsil_subtehsilDetailController', Tehsil_subtehsilDetailController);

    Tehsil_subtehsilDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tehsil_subtehsil'];

    function Tehsil_subtehsilDetailController($scope, $rootScope, $stateParams, previousState, entity, Tehsil_subtehsil) {
        var vm = this;

        vm.tehsil_subtehsil = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:tehsil_subtehsilUpdate', function(event, result) {
            vm.tehsil_subtehsil = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

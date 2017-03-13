(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Accept_verificationDetailController', Accept_verificationDetailController);

    Accept_verificationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Accept_verification'];

    function Accept_verificationDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Accept_verification) {
        var vm = this;

        vm.accept_verification = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('investhryApp:accept_verificationUpdate', function(event, result) {
            vm.accept_verification = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

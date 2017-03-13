(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('InvestorDetailController', InvestorDetailController);

    InvestorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Investor'];

    function InvestorDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Investor) {
        var vm = this;

        vm.investor = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('investhryApp:investorUpdate', function(event, result) {
            vm.investor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

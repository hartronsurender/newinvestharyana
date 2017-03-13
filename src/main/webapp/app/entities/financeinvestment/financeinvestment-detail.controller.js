(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('FinanceinvestmentDetailController', FinanceinvestmentDetailController);

    FinanceinvestmentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Financeinvestment'];

    function FinanceinvestmentDetailController($scope, $rootScope, $stateParams, previousState, entity, Financeinvestment) {
        var vm = this;

        vm.financeinvestment = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:financeinvestmentUpdate', function(event, result) {
            vm.financeinvestment = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

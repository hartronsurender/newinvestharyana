(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProductsDetailController', ProductsDetailController);

    ProductsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Products'];

    function ProductsDetailController($scope, $rootScope, $stateParams, previousState, entity, Products) {
        var vm = this;

        vm.products = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:productsUpdate', function(event, result) {
            vm.products = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

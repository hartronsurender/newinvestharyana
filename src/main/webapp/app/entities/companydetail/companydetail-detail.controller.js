(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('CompanydetailDetailController', CompanydetailDetailController);

    CompanydetailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Companydetail'];

    function CompanydetailDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Companydetail) {
        var vm = this;

        vm.companydetail = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('investhryApp:companydetailUpdate', function(event, result) {
            vm.companydetail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

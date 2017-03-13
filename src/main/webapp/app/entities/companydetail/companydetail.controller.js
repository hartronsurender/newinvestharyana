(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('CompanydetailController', CompanydetailController);

    CompanydetailController.$inject = ['DataUtils', 'Companydetail'];

    function CompanydetailController(DataUtils, Companydetail) {

        var vm = this;

        vm.companydetails = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Companydetail.query(function(result) {
                vm.companydetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Accept_verificationController', Accept_verificationController);

    Accept_verificationController.$inject = ['DataUtils', 'Accept_verification'];

    function Accept_verificationController(DataUtils, Accept_verification) {

        var vm = this;

        vm.accept_verifications = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Accept_verification.query(function(result) {
                vm.accept_verifications = result;
                vm.searchQuery = null;
            });
        }
    }
})();

(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Projectprocess_flowstepsDetailController', Projectprocess_flowstepsDetailController);

    Projectprocess_flowstepsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Projectprocess_flowsteps'];

    function Projectprocess_flowstepsDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Projectprocess_flowsteps) {
        var vm = this;

        vm.projectprocess_flowsteps = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('investhryApp:projectprocess_flowstepsUpdate', function(event, result) {
            vm.projectprocess_flowsteps = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

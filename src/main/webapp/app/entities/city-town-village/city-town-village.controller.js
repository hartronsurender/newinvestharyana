(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('City_town_villageController', City_town_villageController);

    City_town_villageController.$inject = ['City_town_village'];

    function City_town_villageController(City_town_village) {

        var vm = this;

        vm.city_town_villages = [];

        loadAll();

        function loadAll() {
            City_town_village.query(function(result) {
                vm.city_town_villages = result;
                vm.searchQuery = null;
            });
        }
    }
})();

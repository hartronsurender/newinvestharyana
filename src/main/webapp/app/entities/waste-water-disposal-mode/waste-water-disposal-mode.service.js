(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Waste_water_disposal_mode', Waste_water_disposal_mode);

    Waste_water_disposal_mode.$inject = ['$resource'];

    function Waste_water_disposal_mode ($resource) {
        var resourceUrl =  'api/waste-water-disposal-modes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

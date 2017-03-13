(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Waste_water_detail', Waste_water_detail);

    Waste_water_detail.$inject = ['$resource'];

    function Waste_water_detail ($resource) {
        var resourceUrl =  'api/waste-water-details/:id';

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

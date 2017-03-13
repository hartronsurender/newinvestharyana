(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Waste_water_nature', Waste_water_nature);

    Waste_water_nature.$inject = ['$resource'];

    function Waste_water_nature ($resource) {
        var resourceUrl =  'api/waste-water-natures/:id';

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

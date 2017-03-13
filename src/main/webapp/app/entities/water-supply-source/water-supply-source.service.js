(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Water_supply_source', Water_supply_source);

    Water_supply_source.$inject = ['$resource'];

    function Water_supply_source ($resource) {
        var resourceUrl =  'api/water-supply-sources/:id';

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

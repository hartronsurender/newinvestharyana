(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('City_town_village', City_town_village);

    City_town_village.$inject = ['$resource'];

    function City_town_village ($resource) {
        var resourceUrl =  'api/city-town-villages/:id';

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

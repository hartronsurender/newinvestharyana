(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Emission_air_pollution', Emission_air_pollution);

    Emission_air_pollution.$inject = ['$resource'];

    function Emission_air_pollution ($resource) {
        var resourceUrl =  'api/emission-air-pollutions/:id';

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

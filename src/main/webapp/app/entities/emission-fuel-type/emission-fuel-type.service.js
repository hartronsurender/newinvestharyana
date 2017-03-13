(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Emission_fuel_type', Emission_fuel_type);

    Emission_fuel_type.$inject = ['$resource'];

    function Emission_fuel_type ($resource) {
        var resourceUrl =  'api/emission-fuel-types/:id';

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

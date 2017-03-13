(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Disposal_for_discharge', Disposal_for_discharge);

    Disposal_for_discharge.$inject = ['$resource'];

    function Disposal_for_discharge ($resource) {
        var resourceUrl =  'api/disposal-for-discharges/:id';

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

(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Emission_particulars', Emission_particulars);

    Emission_particulars.$inject = ['$resource'];

    function Emission_particulars ($resource) {
        var resourceUrl =  'api/emission-particulars/:id';

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

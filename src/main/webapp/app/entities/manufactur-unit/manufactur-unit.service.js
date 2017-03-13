(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Manufactur_unit', Manufactur_unit);

    Manufactur_unit.$inject = ['$resource'];

    function Manufactur_unit ($resource) {
        var resourceUrl =  'api/manufactur-units/:id';

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

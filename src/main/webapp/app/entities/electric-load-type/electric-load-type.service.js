(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Electric_load_type', Electric_load_type);

    Electric_load_type.$inject = ['$resource'];

    function Electric_load_type ($resource) {
        var resourceUrl =  'api/electric-load-types/:id';

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

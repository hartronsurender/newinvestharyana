(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Industrysize', Industrysize);

    Industrysize.$inject = ['$resource'];

    function Industrysize ($resource) {
        var resourceUrl =  'api/industrysizes/:id';

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

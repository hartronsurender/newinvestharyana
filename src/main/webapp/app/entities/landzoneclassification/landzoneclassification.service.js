(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Landzoneclassification', Landzoneclassification);

    Landzoneclassification.$inject = ['$resource'];

    function Landzoneclassification ($resource) {
        var resourceUrl =  'api/landzoneclassifications/:id';

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

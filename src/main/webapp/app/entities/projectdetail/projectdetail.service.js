(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectdetail', Projectdetail);

    Projectdetail.$inject = ['$resource'];

    function Projectdetail ($resource) {
        var resourceUrl =  'api/projectdetails/:id';

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

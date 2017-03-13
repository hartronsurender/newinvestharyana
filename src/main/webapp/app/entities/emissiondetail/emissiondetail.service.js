(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Emissiondetail', Emissiondetail);

    Emissiondetail.$inject = ['$resource'];

    function Emissiondetail ($resource) {
        var resourceUrl =  'api/emissiondetails/:id';

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

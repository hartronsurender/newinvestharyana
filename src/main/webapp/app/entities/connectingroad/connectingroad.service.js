(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Connectingroad', Connectingroad);

    Connectingroad.$inject = ['$resource'];

    function Connectingroad ($resource) {
        var resourceUrl =  'api/connectingroads/:id';

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

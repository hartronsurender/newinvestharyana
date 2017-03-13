(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectrawmaterial', Projectrawmaterial);

    Projectrawmaterial.$inject = ['$resource'];

    function Projectrawmaterial ($resource) {
        var resourceUrl =  'api/projectrawmaterials/:id';

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

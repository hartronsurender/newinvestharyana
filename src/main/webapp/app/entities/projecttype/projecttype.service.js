(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projecttype', Projecttype);

    Projecttype.$inject = ['$resource'];

    function Projecttype ($resource) {
        var resourceUrl =  'api/projecttypes/:id';

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

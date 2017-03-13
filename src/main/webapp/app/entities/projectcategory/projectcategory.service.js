(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectcategory', Projectcategory);

    Projectcategory.$inject = ['$resource'];

    function Projectcategory ($resource) {
        var resourceUrl =  'api/projectcategories/:id';

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

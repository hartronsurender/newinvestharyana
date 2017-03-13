(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectsitedetail', Projectsitedetail);

    Projectsitedetail.$inject = ['$resource'];

    function Projectsitedetail ($resource) {
        var resourceUrl =  'api/projectsitedetails/:id';

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

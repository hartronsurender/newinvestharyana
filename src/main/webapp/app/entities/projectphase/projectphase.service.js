(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectphase', Projectphase);

    Projectphase.$inject = ['$resource', 'DateUtils'];

    function Projectphase ($resource, DateUtils) {
        var resourceUrl =  'api/projectphases/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.fic_implementation_date = DateUtils.convertDateTimeFromServer(data.fic_implementation_date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

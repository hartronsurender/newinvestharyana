(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Accept_verification', Accept_verification);

    Accept_verification.$inject = ['$resource', 'DateUtils'];

    function Accept_verification ($resource, DateUtils) {
        var resourceUrl =  'api/accept-verifications/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.applicationdate = DateUtils.convertDateTimeFromServer(data.applicationdate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

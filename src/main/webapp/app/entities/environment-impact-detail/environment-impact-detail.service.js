(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Environment_impact_detail', Environment_impact_detail);

    Environment_impact_detail.$inject = ['$resource'];

    function Environment_impact_detail ($resource) {
        var resourceUrl =  'api/environment-impact-details/:id';

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

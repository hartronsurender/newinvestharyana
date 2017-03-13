(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Financeinvestment', Financeinvestment);

    Financeinvestment.$inject = ['$resource', 'DateUtils'];

    function Financeinvestment ($resource, DateUtils) {
        var resourceUrl =  'api/financeinvestments/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.project_construction_start_date = DateUtils.convertDateTimeFromServer(data.project_construction_start_date);
                        data.project_commercial_activity_date = DateUtils.convertDateTimeFromServer(data.project_commercial_activity_date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

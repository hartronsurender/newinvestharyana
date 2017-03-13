(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Project_electricity_detail', Project_electricity_detail);

    Project_electricity_detail.$inject = ['$resource', 'DateUtils'];

    function Project_electricity_detail ($resource, DateUtils) {
        var resourceUrl =  'api/project-electricity-details/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.load_demand_date = DateUtils.convertDateTimeFromServer(data.load_demand_date);
                        data.regular_demand_date = DateUtils.convertDateTimeFromServer(data.regular_demand_date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

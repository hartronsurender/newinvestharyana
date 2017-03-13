(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Companydetail', Companydetail);

    Companydetail.$inject = ['$resource'];

    function Companydetail ($resource) {
        var resourceUrl =  'api/companydetails/:id';

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

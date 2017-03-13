(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Tehsil_subtehsil', Tehsil_subtehsil);

    Tehsil_subtehsil.$inject = ['$resource'];

    function Tehsil_subtehsil ($resource) {
        var resourceUrl =  'api/tehsil-subtehsils/:id';

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

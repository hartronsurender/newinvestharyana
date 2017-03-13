(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Foreignfundingresource', Foreignfundingresource);

    Foreignfundingresource.$inject = ['$resource'];

    function Foreignfundingresource ($resource) {
        var resourceUrl =  'api/foreignfundingresources/:id';

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

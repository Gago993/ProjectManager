(function () {
    'use strict';

    ProjecManagerApp.factory('MemberData', MemberData);

    MemberData.$inject= ['$resource'];

    function MemberData($resource) {
        return $resource("members/:id", {}, {
            'update': {
                method: 'PUT'
            },
            'search': {
            	method: 'GET',
            	url: '/search/:email',
            	isArray: true,
            }
        });
    }
})();
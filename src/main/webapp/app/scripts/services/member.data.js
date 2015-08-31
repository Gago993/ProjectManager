(function () {
    'use strict';

    ProjectManagerApp.factory('MemberData', MemberData);

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
            },
            'changePicture': {
            	method: 'POST',
            	url: '/change-picture'
            },
            'removePicture': {
            	method: 'GET',
            	url: '/remove-picture'
            }
        });
    }
})();
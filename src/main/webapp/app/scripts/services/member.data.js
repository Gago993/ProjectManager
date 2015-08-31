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
            	url: 'members/:id/change-picture',
            	headers: {'Content-Type': undefined}
            },
            'removePicture': {
            	method: 'GET',
            	url: 'members/:id/remove-picture'
            }
        });
    }
})();
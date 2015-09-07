(function () {
    'use strict';

    ProjectManagerApp.factory('ProjectData', ProjectData);

    ProjectData.$inject= ['$resource'];

    function ProjectData($resource) {
        return $resource("projects/:id", {}, {
            'update': {
                method: 'PUT'
            },
            'uploadAttachment': {
            	method: 'POST',
            	url: 'projects/:id/attachment',
            	headers: {'Content-Type': undefined}
            },
            'removeAttachment': {
            	method: 'DELETE',
            	url: 'projects/:id/attachment/:index'
            }
        });
    }
})();
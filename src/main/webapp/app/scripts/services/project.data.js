(function () {
    'use strict';

    ProjectManagerApp.factory('ProjectData', ProjectData);

    ProjectData.$inject= ['$resource'];

    function ProjectData($resource) {
        return $resource("projects/:id", {}, {
            'update': {
                method: 'PUT'
            }
        });
    }
})();
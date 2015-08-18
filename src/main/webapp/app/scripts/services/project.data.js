(function () {
    'use strict';

    ProjecManagerApp.factory('ProjectData', ProjectData);

    ProjectData.$inject= ['$resource'];

    function ProjectData($resource) {
        return $resource("projects/:id", {}, {
            'update': {
                method: 'PUT'
            }
        });
    }
})();
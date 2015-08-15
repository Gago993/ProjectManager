(function () {
    'use strict';

    ProjecManagerApp.factory('DashboardData', DashboardData);

    DashboardData.$inject= ['$resource'];

    function DashboardData($resource) {
        return $resource("api do login rest", {}, {
            'update': {
                method: 'PUT'
            }
        });
    }
})();
(function () {
    'use strict';

    ProjecManagerApp.factory('CompanyData', CompanyData);

    CompanyData.$inject= ['$resource'];

    function CompanyData($resource) {
        return $resource("api do login rest", {}, {
            'update': {
                method: 'PUT'
            }
        });
    }
})();
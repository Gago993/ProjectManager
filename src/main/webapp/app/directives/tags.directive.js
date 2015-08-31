(function () {
    'use strict';

    /* 
        Usage: <tag-input></tag-input>


        Attributes:
            1.  initial-data - An array of objects which represent the tag and needs to have property which will be the name of tag, propertyName : Address
            2.  add-tag(result) - callback function that triggers when pressed enter and saves the tag
            3.  remove-tag(result) - callback function that deletes the tag when pressed on the X on the tag
    */

    ProjectManagerApp
    .directive('pmTags', pmTags);

    pmTags.$inject = ["$stateParams"];

    function pmTags($stateParams) {
        var directive = {
            link: link,
            templateUrl: "app/directives/tags.directive.html",
            restrict: 'AE',
            replace: true,
            scope: {
                initialData: "=",
                viewName: '@',
            }
        };

        return directive;

        function link(scope, element, attrs) {
            scope.inputWidth = 65;

            console.log(scope.initialData);
            console.log(scope.viewName);
            
            scope.$watch('textTag', function (value) {
                if (value != undefined) {
                	console.log(value);
                }
            });

        }
    }

})();
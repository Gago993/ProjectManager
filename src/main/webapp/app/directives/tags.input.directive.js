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
    .directive('pmInputTags', pmTags);

    pmTags.$inject = ["$stateParams"];

    function pmTags($stateParams) {
        var directive = {
            link: link,
            templateUrl: "app/directives/tags.input.directive.html",
            restrict: 'AE',
            replace: true,
            scope: {
                initialData: "=",
                addTag: "&",
                removeTag: "&",
                viewName: '@',
                addPlaceholder: "@",
            }
        };

        return directive;

        function link(scope, element, attrs) {
            scope.inputWidth = 100;
            scope.remove = remove;


            element.bind('keypress', function (e) {
                var key = e.which;

                if (key == 13 || key == 32) {
                    e.preventDefault();

                    var query = scope.textTag;

                    if (query.length == 0) {
                        return;
                    }
                    scope.addTag({ result: query });
                    scope.textTag = "";
                    
                }                
            })

            function remove(index) {
            	console.log(index);
            	scope.initialData.splice(index, 1);
            	scope.removeTag({ index: index });
            }

        }
    }

})();
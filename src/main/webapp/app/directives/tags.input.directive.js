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

            }
        };

        return directive;

        function link(scope, element, attrs) {
            scope.inputWidth = 65;
            scope.remove = remove;

            var callback = scope.$watch('initialData', function(newVal){
                if(newVal)
                {
                	scope.initialData = newVal;
                    callback();
                }
            });
            
            
            scope.$watch('textTag', function (value) {
                if (value != undefined) {
                	//console.log(value);
                }
            });


            element.bind('keypress', function (e) {
                var key = e.which;

                if (key == 13) {
                    e.preventDefault();

                    var result = scope.textTag;

                    if (result.length == 0) {
                        return;
                    }
                    scope.textTag = "";
                    scope.addTag({ result: result });
                                     
                }                
            })

            function remove(index) {
            	console.log(index);
            	scope.removeTag({ index: index });
            }

        }
    }

})();
ProjecManagerApp
        .factory(
                "Service",
                [function() {
                  var content = [
                      {
                        title: 'Why AngularJS?',
                        content: 'HTML is great for declaring static documents, but it falters when we try to use it for declaring dynamic views in web-applications. AngularJS lets you extend HTML vocabulary for your application. The resulting environment is extraordinarily expressive, readable, and quick to develop.'
                      },
                      {
                        title: 'Alternatives',
                        content: 'Other frameworks deal with HTML`s shortcomings by either abstracting away HTML, CSS, and/or JavaScript or by providing an imperative way for manipulating the DOM. Neither of these address the root problem that HTML was not designed for dynamic views.'
                      },
                      {
                        title: 'Extensibility',
                        content: 'AngularJS is a toolset for building the framework most suited to your application development. It is fully extensible and works well with other libraries. Every feature can be modified or replaced to suit your unique development workflow and feature needs. Read on to find out how.'
                      }
                  ];

                  return {
                    get: function() {
                      return content;
                    }
                  };
                }]);


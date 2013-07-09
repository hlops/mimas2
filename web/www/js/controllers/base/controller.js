define([
    'chaplin',
    'views/site-view',
    'lib/utils'
], function (Chaplin, SiteView, Utils) {
    'use strict';

    var Controller = Chaplin.Controller.extend({
        // Place your application-specific controller features here.
        beforeAction:function () {
            this.compose('site', SiteView);
        },

        loadModel: function() {
            var controller = this;
            var dfd = new $.Deferred();
            controller.model.on("change", function() {
                var names = controller.model.get("i18n");
                if (names) {
                    for (var i = 0; i < names.length; i++) {
                        names[i] = "json!i18n/ru/" + names[i] + ".json";
                    }
                    require(names, function () {
                        dfd.resolve(arguments);
                    });
                } else {
                    dfd.resolve();
                }
                controller.view.render();
            });

            controller.view.on('addedToDOM', function () {
                dfd.done(function () {
                    if (arguments.length) {
                        var arr = arguments[0];
                        for (var i = 0; i < arr.length; i++) {
                            Utils.i18n(arr[i]);
                        }
                    }
                });
            });
            controller.model.fetch();
        }

    });

    return Controller;
});

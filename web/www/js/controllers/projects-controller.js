define([
    'chaplin',
    'controllers/base/controller',
    'models/projects',
    'views/projects-view',
    'lib/utils'
], function (Chaplin, Controller, Projects, ProjectsView, Utils) {
    'use strict';

    return Controller.extend({
        show:function (params) {
            this.model = new Projects();
            var controller = this;

            var dfd = new $.Deferred();
            this.model.on("change", function() {
                var files = controller.model.get("i18n");
                if (files) {
                    for (var i = 0; i < files.length; i++) {
                        files[i] = "json!i18n/ru/" + files[i] + ".json";
                    }
                    require(files, function () {
                        dfd.resolve(arguments);
                    });
                } else {
                    dfd.resolve();
                }
                controller.view.render();
            });

            this.view = new ProjectsView({model:this.model});
            this.view.on('addedToDOM', function () {
                dfd.done(function () {
                    if (arguments.length) {
                        var arr = arguments[0];
                        for (var i = 0; i < arr.length; i++) {
                            Utils.i18n(arr[i]);
                        }
                    }
                });
            });
            this.model.fetch();

            Chaplin.mediator.subscribe("search", function (query) {
                controller.model.fetch({data: {query:query}});
            });

        }
    });
});

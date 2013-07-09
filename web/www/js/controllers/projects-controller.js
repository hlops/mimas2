define([
    'controllers/base/controller',
    'models/projects',
    'views/projects-view',
    'cookies'
], function (Controller, Projects, ProjectsView) {
    'use strict';

    return Controller.extend({
        query: "",
        menu: "",

        show:function (params) {
            this.model = new Projects();
            this.view = new ProjectsView({model:this.model});

            this.loadModel();

            var controller = this;

            this.subscribeEvent("search", function (query) {
                controller.query = query;
                controller.model.fetch({data: {query:query}});
            });

            this.subscribeEvent("menuClick", function (el) {
                //$.cookie();
                //alert($(el).attr("item"))
                var itemId = $(el).attr("item");
                alert(this.model.get("leftMenu").get("leftMenuView").get("items")[0])
                controller.model.fetch({data: {query:controller.query, menu: el.id}});
            });
        }
    });
});

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
                var itemId = $(el).attr("item");
                var parentItemId = $(el).attr("parentItem");
                /*
                 var parentItem = this.model.get("leftMenu").get(parentItemId);
                 var menu = _.find(parentItem.get("items"), function(el) {
                 return el.id === itemId;
                 });
                 */
                $.cookie(parentItemId, itemId);
                controller.model.fetch({data: {query:controller.query, menu: el.id}});
            });
        }
    });
});

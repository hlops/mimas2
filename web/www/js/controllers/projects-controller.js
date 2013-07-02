define([
    'controllers/base/controller',
    'models/projects',
    'views/projects-view'
], function (Controller, Projects, ProjectsView) {
    'use strict';

    return Controller.extend({
        show:function (params) {
            this.model = new Projects();
            var ctlr = this;
            this.model.on("change", function() {
                ctlr.view.render();
            });
            this.view = new ProjectsView({model:this.model});
            this.model.fetch();
        }
    });
});

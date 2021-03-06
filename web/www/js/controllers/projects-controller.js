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
        }
    });
});

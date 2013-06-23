define([
    'controllers/base/controller',
    'models/projectsCollection',
    'views/projectsCollection-view'
], function (Controller, Projects, ProjectsView) {
    'use strict';

    var ProjectsController = Controller.extend({
        show:function (params) {
            this.collection = new Projects();
            this.view = new ProjectsView({collection:this.collection});
        }
    });

    return ProjectsController;
});

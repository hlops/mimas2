define([
    'controllers/base/controller',
    'models/projectsCollection',
    'models/projectItem',
    'views/projectsCollection-view',
    'views/projectItem-view'
], function (Controller, Projects, Project, ProjectsView, ProjectView) {
    'use strict';

    var ProjectsController = Controller.extend({
        show:function (params) {
            this.collection = new Projects();
            this.collection.add(new Project({name:"trololo"}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.collection.add(new Project({}))
            this.view = new ProjectsView({collection:this.collection});
        }
    });

    return ProjectsController;
});

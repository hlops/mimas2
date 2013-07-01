define([
    'controllers/base/controller',
    'models/projects',
    'models/leftMenuCollection',
    'models/projectItem',
    'models/projectItem',
    'views/projects-view'
], function (Controller, Projects, LeftMenuCollection, LeftMenuItem, Project, ProjectsView) {
    'use strict';

    var ProjectsController = Controller.extend({
        show:function (params) {
            this.model = new Projects();
            var ctlr = this;
            this.model.on("change", function() {
                //alert(JSON.stringify(this))
                ctlr.view.render();
            });
            this.model.fetch();
            this.view = new ProjectsView({model:this.model});

            /*
             var menuCollection = new LeftMenuCollection();
             this.model.menu = menuCollection;
             menuCollection.add(new LeftMenuItem({}));
             menuCollection.add(new LeftMenuItem({}));
             menuCollection.add(new LeftMenuItem({}));

             var projectCollection = new ProjectCollection();
             this.model.projects = projectCollection;
             //this.model.set("projects", projectCollection);
             projectCollection.add(new Project({name:"trololo"}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))
             projectCollection.add(new Project({}))

             */
        }
    });

    return ProjectsController;
});

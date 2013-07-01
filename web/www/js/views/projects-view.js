define([
    'chaplin',
    'views/base/view',
    'text!templates/projects.hbs',
    'views/leftMenu-view',
    'views/projectsCollection-view'
], function (Chaplin, View, template, LeftMenuView, ProjectsCollectionView) {
    'use strict';

    var ProjectView = View.extend({
        // Automatically render after initialize
        autoRender:false,
        id:"projects",
        region:'main',
        regions:{
            '#projectsContainerLeft':'left',
            '#projectsContainerRight':'right'
        },

        // Save the template string in a prototype property.
        // This is overwritten with the compiled template function.
        // In the end you might want to used precompiled templates.
        template:template,

        render:function () {
            //alert(this.model)
            //alert(this.model.get("projects") instanceof Chaplin.Collection)
            //alert(this.model.get("projects") instanceof Array)
            View.prototype.render.apply(this, arguments);
            //this.subview('leftMenu', new LeftMenuView({collection:this.model, container:this.el}));
            this.subview('projectCollection', new ProjectsCollectionView({collection:this.model.get("projects"), container:this.el}));
        }
    });


    return ProjectView;
});

define([
    'chaplin',
    'views/base/view',
    'views/base/collection-view',
    'text!templates/projects/projects.hbs',
    'text!templates/projects/projectsCollection.hbs',
    'text!templates/projects/projectItem.hbs',
    'views/leftMenu-view'
], function (Chaplin, View, CollectionView, projectContainerTemplate, projectsTemplate, projectItemTemplate, LeftMenuView) {
    'use strict';

    var ProjectItemView = View.extend({
        // Automatically render after initialize
        autoRender:true,
        className:'projectItem-view',

        // Save the template string in a prototype property.
        // This is overwritten with the compiled template function.
        // In the end you might want to used precompiled templates.
        template:projectItemTemplate
    });

    var ProjectsCollectionView = CollectionView.extend({
        // Automatically render after initialize
        itemView:ProjectItemView,
        autoRender:true,

        className:'projects-view',
        id:"divProjects",

        region:'right',
        listSelector: "#projectsCollection",

        // Save the template string in a prototype property.
        // This is overwritten with the compiled template function.
        // In the end you might want to used precompiled templates.
        template:projectsTemplate

    });

    var ProjectView = View.extend({
        autoRender:false,
        id:"projects",
        region:'main',
        regions:{
            '#projectsContainerLeft':'left',
            '#projectsContainerRight':'right'
        },
        template:projectContainerTemplate,

        render:function () {
            View.prototype.render.apply(this, arguments);
            this.subview('leftMenu', new LeftMenuView({collection:this.model.get("leftMenu"), container:this.el}));
            this.subview('projectCollection', new ProjectsCollectionView({collection:this.model.get("projects"), container:this.el}));
        }
    });


    return ProjectView;
});

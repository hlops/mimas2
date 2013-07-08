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
        className:'projectItem-view',
        template:projectItemTemplate
    });

    var ProjectsCollectionView = CollectionView.extend({
        id:"divProjects",
        className:'projects-view',
        template:projectsTemplate,
        region:'right',
        itemView:ProjectItemView,
        listSelector: "#projectsCollection"

    });

    //noinspection UnnecessaryLocalVariableJS
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

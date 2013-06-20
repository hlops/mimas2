define([
    'views/base/collection-view',
    'text!templates/projectsCollection.hbs',
    'views/projectItem-view'
], function (CollectionView, template, ItemView) {
    'use strict';

    var ProjectsView = CollectionView.extend({
        // Automatically render after initialize
        itemView:ItemView,
        autoRender:true,

        className:'projects-view',
        id:"divProjects",

        region:'main',
        listSelector: "#projects",

        // Save the template string in a prototype property.
        // This is overwritten with the compiled template function.
        // In the end you might want to used precompiled templates.
        template:template
    });

    return ProjectsView;
});

define([
    'views/base/collection-view',
    'text!templates/leftMenu.hbs',
    'views/leftMenuItem-view'
], function (CollectionView, template, ItemView) {
    'use strict';

    var MenuView = CollectionView.extend({
        // Automatically render after initialize
        itemView:ItemView,
        autoRender:true,

        className:'projects-view',
        id:"divProjects",

        region:'left',
        //listSelector: "#menuCollection",

        // Save the template string in a prototype property.
        // This is overwritten with the compiled template function.
        // In the end you might want to used precompiled templates.
        template:template
    });

    return MenuView;
});

define([
    'lib/view-helper',
    'views/base/view',
    'views/base/collection-view',
    'text!templates/menu/leftMenuItem.hbs',
    'text!templates/menu/leftMenu.hbs'
], function (Helper, View, CollectionView, itemTemplate, template) {
    'use strict';

    var ItemView = View.extend({
        autoRender:true,
        template:itemTemplate
    });

    return CollectionView.extend({
        itemView:ItemView,
        autoRender:true,
        region:'left',
        template:template
    });
});

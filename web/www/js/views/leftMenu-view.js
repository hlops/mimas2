define([
    'lib/view-helper',
    'views/base/view',
    'views/base/collection-view',
    'text!templates/menu/leftMenuItem.hbs'
], function (Helper, View, CollectionView, itemTemplate) {
    'use strict';

    var ItemView = View.extend({
        template:itemTemplate
    });

    return CollectionView.extend({
        itemView:ItemView,
        region:'left',
        className: "affix span2"
    });
});

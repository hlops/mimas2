define([
    'chaplin',
    'lib/view-helper',
    'views/base/view',
    'views/base/collection-view',
    'text!templates/menu/leftMenuItem.hbs'
], function (Chaplin, Helper, View, CollectionView, itemTemplate) {
    'use strict';

    var ItemView = View.extend({
        template:itemTemplate
    });

    return CollectionView.extend({
        itemView:ItemView,
        region:'left',
        className: "affix span2",
        animationDuration: 0,

        events: {
            "click a.menuItem": "menuClick"
        },
        menuClick: function (e) {
            Chaplin.mediator.publish('menuClick', e.target);
            return false;
        }
    });
});

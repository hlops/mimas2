define([
    'models/base/model',
    'models/base/collection'
], function (Model, Collection) {
    'use strict';

    var MenuItem = Model.extend({
    });

    var Menu = Collection.extend({
        model:MenuItem
    });

    return Menu;
});

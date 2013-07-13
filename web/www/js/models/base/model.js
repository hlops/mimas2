define([
    'chaplin',
    'models/base/collection'
], function (Chaplin, Collection) {
    'use strict';

    var Model = Chaplin.Model.extend({
        parse: function (data) {
            if (data.leftMenu) {
                this.set("leftMenu", new Menu(data.leftMenu), {silent: true});
            }
            if (data.i18n) {
                this.set("i18n", data.i18n, {silent: true});
            }
        }
    });

    var MenuItem = Model.extend({
    });

    var Menu = Collection.extend({
        model:MenuItem
    });

    return Model;
});

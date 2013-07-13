define([
    'models/base/model',
    'models/base/collection'
], function (Model, Collection) {
    'use strict';

    var PhotoItem = Model.extend({
    });

    var Photos = Collection.extend({
        model:PhotoItem
    });

    //noinspection UnnecessaryLocalVariableJS
    var Photo = Model.extend({
        url:"rest/photos",
        parse: function (data) {
            Model.prototype.parse.apply(this, arguments);
            this.set("photos", new Photos(data.photos), {silent: true});
            this.trigger("change");
        }
    });

    return Photo;
});

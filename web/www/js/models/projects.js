define([
    'chaplin',
    'models/base/model',
    'models/base/collection',
    'models/projectItem'
], function (Chaplin, Model, Collection, Item) {
    'use strict';

    var Projects = Collection.extend({
        model:Item
        ,initialize: function(attributes, options) {
        }
    });

    var Project = Model.extend({
        url:"rest/projects",
        collection: Projects,
        parse: function (data) {
            this.set("projects", new Projects(data.projects))
        }
    });

    return Project;
});

define([
    'models/base/model',
    'models/base/collection'
], function (Model, Collection) {
    'use strict';

    var ProjectItem = Model.extend({
    });

    var Projects = Collection.extend({
        model:ProjectItem
    });

    //noinspection UnnecessaryLocalVariableJS
    var Project = Model.extend({
        url:"rest/projects",
        parse: function (data) {
            Model.prototype.parse.apply(this, arguments);
            this.set("projects", new Projects(data.projects), {silent: true});
            this.trigger("change");
        }
    });

    return Project;
});

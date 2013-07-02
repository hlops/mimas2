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

    var MenuItem = Model.extend({
    });

    var Menu = Collection.extend({
        model:MenuItem
    });

    //noinspection UnnecessaryLocalVariableJS
    var Project = Model.extend({
        url:"rest/projects",
        parse: function (data) {
            this.set("projects", new Projects(data.projects), {silent: true});
            this.set("leftMenu", new Menu(data.leftMenu), {silent: true});
            this.trigger("change");
        }
    });

    return Project;
});

define([
    'chaplin',
    'models/base/model'
], function (Chaplin, Model) {
    'use strict';

    var Project = Model.extend({
        url:"rest/projects"
        // ,initialize: function(attributes, options) {
        //  Model.prototype.initialize.apply(this, arguments);
        //  console.debug('Project#initialize');
        // }
    });

    return Project;
});

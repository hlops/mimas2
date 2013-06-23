define([
    'chaplin',
    'models/base/collection',
    'models/projectItem'
], function (Chaplin, Collection, Project) {
    'use strict';

    var Projects = Collection.extend({

        collection:new Collection(),

        initialize:function (attributes, options) {
        }

        // ,initialize: function(attributes, options) {
        //  Collection.prototype.initialize.apply(this, arguments);
        //  console.debug('Projects#initialize');
        // }
    });

    return Projects;
});

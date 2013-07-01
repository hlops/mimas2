define([
    'chaplin',
    'models/base/model'
], function (Chaplin, Model) {
    'use strict';

    var Project = Model.extend({
        defaults:{
            name:"Test"
        }

         ,initialize: function(attributes, options) {
        //  Model.prototype.initialize.apply(this, arguments);
        //  console.debug('Project#initialize');
         }
    });

    return Project;
});

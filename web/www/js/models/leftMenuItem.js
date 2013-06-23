define([
    'chaplin',
    'models/base/model'
], function (Chaplin, Model) {
    'use strict';

    var LeftMenuItem = Model.extend({
        defaults:{
            name:"Test"
        }

        // ,initialize: function(attributes, options) {
        //  Model.prototype.initialize.apply(this, arguments);
        //  console.debug('Project#initialize');
        // }
    });

    return LeftMenuItem;
});

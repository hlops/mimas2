define([
    'chaplin',
    'models/base/model'
], function (Chaplin, Model) {
    'use strict';

    var Mimas = Model.extend({
        defaults:{
        }

        // ,initialize: function(attributes, options) {
        //  Model.prototype.initialize.apply(this, arguments);
        //  console.debug('HelloWorld#initialize');
        // }
    });

    return Mimas;
});

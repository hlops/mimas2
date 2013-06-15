define([
  'chaplin',
  'models/base/model'
], function(Chaplin, Model) {
  'use strict';

  var Projects = Model.extend({
    defaults: {
      message: 'Hello World!'
    }

    // ,initialize: function(attributes, options) {
    //  Model.prototype.initialize.apply(this, arguments);
    //  console.debug('Projects#initialize');
    // }
  });

  return Projects;
});

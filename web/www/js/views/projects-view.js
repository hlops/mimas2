define([
  'views/base/view',
  'text!templates/projects.hbs'
], function(View, template) {
  'use strict';

  var ProjectsView = View.extend({
    // Automatically render after initialize
    autoRender: true,

    className: 'projects',

    // Automatically append to the DOM on render
    region: 'main',

    // Save the template string in a prototype property.
    // This is overwritten with the compiled template function.
    // In the end you might want to used precompiled templates.
    template: template
  });

  return ProjectsView;
});

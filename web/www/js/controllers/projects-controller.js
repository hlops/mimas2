define([
  'controllers/base/controller',
  'models/projects',
  'views/projects-view'
], function(Controller, Projects, ProjectsView) {
  'use strict';

  var ProjectsController = Controller.extend({
    show: function(params) {
      this.model = new Projects();
      this.view = new ProjectsView({model: this.model});
    }
  });

  return ProjectsController;
});

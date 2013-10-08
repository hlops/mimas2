define(function() {
  'use strict';

  // The routes for the application. This module returns a function.
  // `match` is match method of the Router
  return function(match) {
      match('', 'projects#show');
      match('projects', 'projects#show');
      match('photos', 'photos#show');
  };
});

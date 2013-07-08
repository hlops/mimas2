define([
    'chaplin',
    'views/base/view',
    'text!templates/site.hbs'
], function (Chaplin, View, template) {
    'use strict';

    return View.extend({
        container:'body',
        id:'site-container',
        regions:{
            '#main-container':'main'
        },
        template:template,

        events: {
            "submit #searchForm": "search"
        },

        search: function () {
            Chaplin.mediator.publish('search', $("#i18n_query", this.el).val());
            return false;
        }
    });
});

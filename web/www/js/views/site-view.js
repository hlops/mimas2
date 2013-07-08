define([
    'views/base/view',
    'text!templates/site.hbs'
], function (View, template) {
    'use strict';

    return View.extend({
        container:'body',
        id:'site-container',
        regions:{
            '#main-container':'main'
        },
        template:template,

        events: {
            "submit #searchForm": "testEvent"
        },

        testEvent: function () {
            alert(32312);
            return false
        }
    });
});

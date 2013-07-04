define(['views/base/view', 'text!templates/site.hbs'], function (View, template) {
    'use strict';

    return View.extend({
        container:'body',
        id:'site-container',
        regions:{
            '#main-container':'main'
        },
        template:template,
        i18n:"site"
    });
});

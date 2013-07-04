define([
    'chaplin',
    'views/base/view',
    'lib/utils'
], function (Chaplin, View, Utils) {
    'use strict';

    var CollectionView = Chaplin.CollectionView.extend({

        // This class doesn’t inherit from the application-specific View class,
        // so we need to borrow the method from the View prototype:
        getTemplateFunction:View.prototype.getTemplateFunction,

        i18n:[],
        attach: function() {
            View.prototype.attach.apply(this, arguments);
            if (this.i18n && this.i18n.length) {
                Utils.i18n(this.i18n.split(" "));
            }
        }
    });

    return CollectionView;
});

define([
    'underscore',
    'chaplin'
], function(_, Chaplin) {
    'use strict'

    // Application-specific utilities
    // ------------------------------

    // Delegate to Chaplin’s utils module
    var utils = Chaplin.utils.beget(Chaplin.utils);

    _(utils).extend(
        {
            i18n: function(data) {
                for (var key in data) {
                    if (data.hasOwnProperty(key)) {
                        var value = data[key];
                        var n = key.indexOf("@");
                        if (n > 0) {
                            $(key.substring(0, n)).attr(key.substring(n + 1), value);
                        } else {
                            $(key).text(value);
                        }
                    }
                }
            }
        });

    return utils;
});

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
            i18n: function(arr) {
                if (arr && arr.length) {
                    for (var i = 0; i < arr.length; i++) {
                        $.getJSON("i18n/ru/" + arr[i] + ".json", function (data) {
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
                        });
                    }
                }
            }
        });

    return utils;
});

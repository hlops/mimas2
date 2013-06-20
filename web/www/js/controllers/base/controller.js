define(['chaplin', 'views/site-view'], function (Chaplin, SiteView) {
    'use strict';

    var Controller = Chaplin.Controller.extend({
        // Place your application-specific controller features here.
        beforeAction:function () {
            this.compose('site', SiteView);

            $.getJSON("test.json",
                function (data) {
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
            )
        }
    });

    return Controller;
});

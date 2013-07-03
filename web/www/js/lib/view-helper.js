define([
    'handlebars',
    'chaplin',
    'lib/utils'
], function(Handlebars, Chaplin, utils) {
    'use strict';

    // Application-specific Handlebars helpers
    // -------------------------------------------
    var functionType = '[object Function]';

    // Get Chaplin-declared named routes. {{#url "like" "105"}}{{/url}}.
    Handlebars.registerHelper('url', function(routeName) {
        var params = [].slice.call(arguments, 1);
        var options = params.pop();
        return Chaplin.helpers.reverse(routeName, params);
    });

    // {{#ifequal current_page "home"}} {{else}} {{/ifequal}}
    Handlebars.registerHelper('ifequal', function (param1, param2, options) {

        if (param1 !== param2) {
            return options.inverse(this);
        } else {
            return options.fn(this);
        }

        //noinspection UnreachableCodeJS
        if (val1 === val2) {
            return fn();
        }
        else if (elseFn) {
            return elseFn();
        }
    });

});

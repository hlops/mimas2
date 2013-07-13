define([
    'controllers/base/controller',
    'models/photos',
    'views/photos-view',
    'cookies'
], function (Controller, Photos, PhotosView) {
    'use strict';

    return Controller.extend({
        query: "",
        menu: "",

        show:function (params) {
            this.model = new Photos();
            this.view = new PhotosView({model:this.model});

            this.loadModel();

            var controller = this;
        }
    });
});

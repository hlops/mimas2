define([
    'chaplin',
    'views/base/view',
    'views/base/collection-view',
    'text!templates/photo/photos.hbs',
    'text!templates/photo/photosCollection.hbs',
    'text!templates/photo/photoItem.hbs',
    'views/leftMenu-view'
], function (Chaplin, View, CollectionView, photoContainerTemplate, photosTemplate, photoItemTemplate, LeftMenuView) {
    'use strict';

    var PhotoItemView = View.extend({
        className:'photoItem-view',
        template:photoItemTemplate
    });

    var PhotosCollectionView = CollectionView.extend({
        id:"divPhotos",
        className:'photos-view',
        template:photosTemplate,
        region:'right',
        itemView:PhotoItemView,
        listSelector: "#photosCollection"

    });

    //noinspection UnnecessaryLocalVariableJS
    var PhotoView = View.extend({
        autoRender:false,
        id:"photos",
        region:'main',
        regions:{
            '#photosContainerLeft':'left',
            '#photosContainerRight':'right'
        },
        template:photoContainerTemplate,

        render:function () {
            View.prototype.render.apply(this, arguments);
            this.subview('leftMenu', new LeftMenuView({collection:this.model.get("leftMenu"), container:this.el}));
            this.subview('photoCollection', new PhotosCollectionView({collection:this.model.get("photos"), container:this.el}));
        }
    });

    return PhotoView;
});

var swfmini=function(){function e(){if(!A){try{var e=x.getElementsByTagName("body")[0].appendChild(h("span"));e.parentNode.removeChild(e)}catch(t){return}A=!0;for(var a=T.length,i=0;a>i;i++)T[i]()}}function t(e){A?e():T[T.length]=e}function a(){}function i(){C&&n()}function n(){var e=x.getElementsByTagName("body")[0],t=h(v);t.setAttribute("type",b);var a=e.appendChild(t);if(a){var i=0;(function(){if(typeof a.GetVariable!=f){var n=a.GetVariable("$version");n&&(n=n.split(" ")[1].split(","),M.pv=[parseInt(n[0],10),parseInt(n[1],10),parseInt(n[2],10)])}else if(10>i)return i++,setTimeout(arguments.callee,10),void 0;e.removeChild(t),a=null})()}}function r(e){var t=null,a=u(e);if(a&&"OBJECT"==a.nodeName)if(typeof a.SetVariable!=f)t=a;else{var i=a.getElementsByTagName(v)[0];i&&(t=i)}return t}function s(e,t,a){var i,n=u(a);if(M.wk&&312>M.wk)return i;if(n)if(typeof e.id==f&&(e.id=a),M.ie&&M.win){var r="";for(var s in e)e[s]!=Object.prototype[s]&&("data"==s.toLowerCase()?t.movie=e[s]:"styleclass"==s.toLowerCase()?r+=' class="'+e[s]+'"':"classid"!=s.toLowerCase()&&(r+=" "+s+'="'+e[s]+'"'));var l="";for(var c in t)t[c]!=Object.prototype[c]&&(l+='<param name="'+c+'" value="'+t[c]+'" />');n.outerHTML='<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"'+r+">"+l+"</object>",E[E.length]=e.id,i=u(e.id)}else{var d=h(v);d.setAttribute("type",b);for(var p in e)e[p]!=Object.prototype[p]&&("styleclass"==p.toLowerCase()?d.setAttribute("class",e[p]):"classid"!=p.toLowerCase()&&d.setAttribute(p,e[p]));for(var m in t)t[m]!=Object.prototype[m]&&"movie"!=m.toLowerCase()&&o(d,m,t[m]);n.parentNode.replaceChild(d,n),i=d}return i}function o(e,t,a){var i=h("param");i.setAttribute("name",t),i.setAttribute("value",a),e.appendChild(i)}function l(e){var t=u(e);t&&"OBJECT"==t.nodeName&&(M.ie&&M.win?(t.style.display="none",function(){4==t.readyState?c(e):setTimeout(arguments.callee,10)}()):t.parentNode.removeChild(t))}function c(e){var t=u(e);if(t){for(var a in t)"function"==typeof t[a]&&(t[a]=null);t.parentNode.removeChild(t)}}function u(e){var t=null;try{t=x.getElementById(e)}catch(a){}return t}function h(e){return x.createElement(e)}function d(e){var t=M.pv,a=e.split(".");return a[0]=parseInt(a[0],10),a[1]=parseInt(a[1],10)||0,a[2]=parseInt(a[2],10)||0,t[0]>a[0]||t[0]==a[0]&&t[1]>a[1]||t[0]==a[0]&&t[1]==a[1]&&t[2]>=a[2]?!0:!1}function p(e,t){if(S){var a,i=t?"visible":"hidden";A&&a&&u(e)&&(u(e).style.visibility=i)}}var f="undefined",v="object",m=jQuery.webshims||window.webshims,g="Shockwave Flash",y="ShockwaveFlash.ShockwaveFlash",b="application/x-shockwave-flash",w=window,x=document,k=navigator,C=!1,T=[i],E=[],P=[],A=!1,S=!0,M=function(){var e=typeof x.getElementById!=f&&typeof x.getElementsByTagName!=f&&typeof x.createElement!=f,t=k.userAgent.toLowerCase(),a=k.platform.toLowerCase(),i=a?/win/.test(a):/win/.test(t),n=a?/mac/.test(a):/mac/.test(t),r=/webkit/.test(t)?parseFloat(t.replace(/^.*webkit\/(\d+(\.\d+)?).*$/,"$1")):!1,s=!1,o=[0,0,0],l=null;if(typeof k.plugins!=f&&typeof k.plugins[g]==v)l=k.plugins[g].description,!l||typeof k.mimeTypes!=f&&k.mimeTypes[b]&&!k.mimeTypes[b].enabledPlugin||(C=!0,s=!1,l=l.replace(/^.*\s+(\S+\s+\S+$)/,"$1"),o[0]=parseInt(l.replace(/^(.*)\..*$/,"$1"),10),o[1]=parseInt(l.replace(/^.*\.(.*)\s.*$/,"$1"),10),o[2]=/[a-zA-Z]/.test(l)?parseInt(l.replace(/^.*[a-zA-Z]+(.*)$/,"$1"),10):0);else if(typeof w.ActiveXObject!=f)try{var c=new ActiveXObject(y);c&&(l=c.GetVariable("$version"),l&&(s=!0,l=l.split(" ")[1].split(","),o=[parseInt(l[0],10),parseInt(l[1],10),parseInt(l[2],10)]))}catch(u){}return{w3:e,pv:o,wk:r,ie:s,win:i,mac:n}}();return function(){M.ie&&M.win&&window.attachEvent&&window.attachEvent("onunload",function(){for(var e=P.length,t=0;e>t;t++)P[t][0].detachEvent(P[t][1],P[t][2]);for(var a=E.length,i=0;a>i;i++)l(E[i]);for(var n in M)M[n]=null;M=null;for(var r in swfmini)swfmini[r]=null;swfmini=null})}(),m.ready("DOM",e),{registerObject:function(){},getObjectById:function(e){return M.w3?r(e):void 0},embedSWF:function(e,a,i,n,r,o,l,c,u,h){var m={success:!1,id:a};M.w3&&!(M.wk&&312>M.wk)&&e&&a&&i&&n&&r?(p(a,!1),t(function(){i+="",n+="";var t={};if(u&&typeof u===v)for(var o in u)t[o]=u[o];t.data=e,t.width=i,t.height=n;var g={};if(c&&typeof c===v)for(var y in c)g[y]=c[y];if(l&&typeof l===v)for(var b in l)typeof g.flashvars!=f?g.flashvars+="&"+b+"="+l[b]:g.flashvars=b+"="+l[b];if(d(r)){var w=s(t,g,a);t.id==a&&p(a,!0),m.success=!0,m.ref=w}else p(a,!0);h&&h(m)})):h&&h(m)},switchOffAutoHideShow:function(){S=!1},ua:M,getFlashPlayerVersion:function(){return{major:M.pv[0],minor:M.pv[1],release:M.pv[2]}},hasFlashPlayerVersion:d,createSWF:function(e,t,a){return M.w3?s(e,t,a):void 0},showExpressInstall:function(){},removeSWF:function(e){M.w3&&l(e)},createCSS:function(){},addDomLoadEvent:t,addLoadEvent:a,expressInstallCallback:function(){}}}();webshims.register("form-core",function(e,t,a,i,n,r){"use strict";t.capturingEventPrevented=function(t){if(!t._isPolyfilled){var a=t.isDefaultPrevented,i=t.preventDefault;t.preventDefault=function(){return clearTimeout(e.data(t.target,t.type+"DefaultPrevented")),e.data(t.target,t.type+"DefaultPrevented",setTimeout(function(){e.removeData(t.target,t.type+"DefaultPrevented")},30)),i.apply(this,arguments)},t.isDefaultPrevented=function(){return!(!a.apply(this,arguments)&&!e.data(t.target,t.type+"DefaultPrevented"))},t._isPolyfilled=!0}},Modernizr.formvalidation&&!t.bugs.bustedValidity&&t.capturingEvents(["invalid"],!0);var s=function(t){return(e.prop(t,"validity")||{valid:1}).valid},o=function(){var a=["form-validation"];r.lazyCustomMessages&&(r.customMessages=!0,a.push("form-message")),r.addValidators&&a.push("form-validators"),t.reTest(a),e(i).off(".lazyloadvalidation")},l=function(t){var a=!1;return e(t).jProp("elements").each(function(){return a=e(this).is(":invalid"),a?!1:n}),a},c=/^(?:form)$/i;e.extend(e.expr[":"],{"valid-element":function(t){return c.test(t.nodeName||"")?!l(t):!(!e.prop(t,"willValidate")||!s(t))},"invalid-element":function(t){return c.test(t.nodeName||"")?l(t):!(!e.prop(t,"willValidate")||s(t))},"required-element":function(t){return!(!e.prop(t,"willValidate")||!e.prop(t,"required"))},"user-error":function(t){return e.prop(t,"willValidate")&&e(t).hasClass("user-error")},"optional-element":function(t){return!(!e.prop(t,"willValidate")||e.prop(t,"required")!==!1)}}),["valid","invalid","required","optional"].forEach(function(t){e.expr[":"][t]=e.expr.filters[t+"-element"]}),e.expr[":"].focus=function(e){try{var t=e.ownerDocument;return e===t.activeElement&&(!t.hasFocus||t.hasFocus())}catch(a){}return!1},t.triggerInlineForm=function(t,a){e(t).trigger(a)};var u=function(e,a,i){o(),t.ready("form-validation",function(){e[a].apply(e,i)})};t.wsPopover={id:0,_create:function(){this.options=e.extend({},t.cfg.wspopover,this.options),this.id=t.wsPopover.id++,this.eventns=".wsoverlay"+this.id,this.timers={},this.element=e('<div class="ws-popover" tabindex="-1"><div class="ws-po-outerbox"><div class="ws-po-arrow"><div class="ws-po-arrowbox" /></div><div class="ws-po-box" /></div></div>'),this.contentElement=e(".ws-po-box",this.element),this.lastElement=e([]),this.bindElement(),this.element.data("wspopover",this)},options:{},content:function(e){this.contentElement.html(e)},bindElement:function(){var e=this,t=function(){e.stopBlur=!1};this.preventBlur=function(){e.stopBlur=!0,clearTimeout(e.timers.stopBlur),e.timers.stopBlur=setTimeout(t,9)},this.element.on({mousedown:this.preventBlur})},show:function(){u(this,"show",arguments)}},t.validityAlert={showFor:function(){u(this,"showFor",arguments)}},function(){var t,a,n=[];e(i).on("invalid",function(r){if(!r.wrongWebkitInvalid){var s=e(r.target);if(!t){t=e.Event("firstinvalid"),t.isInvalidUIPrevented=r.isDefaultPrevented;var o=e.Event("firstinvalidsystem");e(i).triggerHandler(o,{element:r.target,form:r.target.form,isInvalidUIPrevented:r.isDefaultPrevented}),s.trigger(t)}t&&t.isDefaultPrevented()&&r.preventDefault(),n.push(r.target),r.extraData="fix",clearTimeout(a),a=setTimeout(function(){var a={type:"lastinvalid",cancelable:!1,invalidlist:e(n)};t=!1,n=[],e(r.target).trigger(a,a)},9),s=null}})}(),t.getContentValidationMessage=function(t,a,i){var r=e(t).data("errormessage")||t.getAttribute("x-moz-errormessage")||"";return i&&r[i]&&(r=r[i]),"object"==typeof r&&(a=a||e.prop(t,"validity")||{valid:1},a.valid||e.each(a,function(e,t){return t&&"valid"!=e&&r[e]?(r=r[e],!1):n})),"object"==typeof r&&(r=r.defaultMessage),r||""},e.fn.getErrorMessage=function(a){var i="",n=this[0];return n&&(i=t.getContentValidationMessage(n,!1,a)||e.prop(n,"customValidationMessage")||e.prop(n,"validationMessage")),i},t.ready("forms",function(){e(i).on("focusin.lazyloadvalidation",function(t){"form"in t.target&&e(t.target).is(":invalid")&&o()})}),t.ready("WINDOWLOAD",o),r.overrideMessages&&(r.customMessages=!0,t.reTest("form-message"),t.error("overrideMessages is deprecated. use customMessages instead.")),r.replaceValidationUI&&t.ready("DOM forms",function(){e(i).on("firstinvalid",function(e){e.isInvalidUIPrevented()||(e.preventDefault(),t.validityAlert.showFor(e.target))})})}),function(e,t,a){"use strict";var i,n,r=t.audio&&t.video,s=!1,o=a.bugs,l="mediaelement-jaris",c=function(){a.ready(l,function(){a.mediaelement.createSWF||(a.mediaelement.loadSwf=!0,a.reTest([l],r))})},u=a.cfg.mediaelement;if(!u)return a.error("mediaelement wasn't implemented but loaded"),void 0;if(r){var h=document.createElement("video");t.videoBuffered="buffered"in h,s="loop"in h,a.capturingEvents(["play","playing","waiting","paused","ended","durationchange","loadedmetadata","canplay","volumechange"]),t.videoBuffered||(a.addPolyfill("mediaelement-native-fix",{d:["dom-support"]}),a.loader.loadList(["mediaelement-native-fix"]))}if(r&&!u.preferFlash){var d={1:1,2:1},p=function(t){var i,r;!u.preferFlash&&(e(t.target).is("audio, video")||(r=t.target.parentNode)&&e("source:last",r)[0]==t.target)&&(i=e(t.target).closest("audio, video"))&&!d[i.prop("error")]&&e(function(){n&&!u.preferFlash?(c(),a.ready("WINDOWLOAD "+l,function(){setTimeout(function(){u.preferFlash||!a.mediaelement.createSWF||i.is(".nonnative-api-active")||(u.preferFlash=!0,document.removeEventListener("error",p,!0),e("audio, video").each(function(){a.mediaelement.selectSource(this)}),a.error("switching mediaelements option to 'preferFlash', due to an error with native player: "+t.target.src+" Mediaerror: "+i.prop("error")))},9)})):document.removeEventListener("error",p,!0)})};document.addEventListener("error",p,!0),e("audio, video").each(function(){var t=e.prop(this,"error");return t&&!d[t]?(p({target:this}),!1):void 0})}t.track&&!o.track&&function(){if(o.track||(o.track="number"!=typeof e("<track />")[0].readyState),!o.track)try{new TextTrackCue(2,3,"")}catch(t){o.track=!0}}(),i=t.track&&!o.track,a.register("mediaelement-core",function(e,t,a,o,h){n=swfmini.hasFlashPlayerVersion("9.0.115"),e("html").addClass(n?"swf":"no-swf");var d=t.mediaelement;d.parseRtmp=function(e){var a,i,n,r=e.src.split("://"),s=r[1].split("/");for(e.server=r[0]+"://"+s[0]+"/",e.streamId=[],a=1,i=s.length;i>a;a++)n||-1===s[a].indexOf(":")||(s[a]=s[a].split(":")[1],n=!0),n?e.streamId.push(s[a]):e.server+=s[a]+"/";e.streamId.length||t.error("Could not parse rtmp url"),e.streamId=e.streamId.join("/")};var p=function(t,a){t=e(t);var i,n={src:t.attr("src")||"",elem:t,srcProp:t.prop("src")};return n.src?(i=t.attr("data-server"),null!=i&&(n.server=i),i=t.attr("type"),i?(n.type=i,n.container=e.trim(i.split(";")[0])):(a||(a=t[0].nodeName.toLowerCase(),"source"==a&&(a=(t.closest("video, audio")[0]||{nodeName:"video"}).nodeName.toLowerCase())),n.server?(n.type=a+"/rtmp",n.container=a+"/rtmp"):(i=d.getTypeForSrc(n.src,a,n),i&&(n.type=i,n.container=i))),i=t.attr("media"),i&&(n.media=i),("audio/rtmp"==n.type||"video/rtmp"==n.type)&&(n.server?n.streamId=n.src:d.parseRtmp(n)),n):n},f=!n&&"postMessage"in a&&r,v=function(){v.loaded||(v.loaded=!0,t.ready("WINDOWLOAD",function(){g(),t.loader.loadList(["track-ui"])}))},m=function(){var a;return function(){!a&&f&&(a=!0,t.loader.loadScript("https://www.youtube.com/player_api"),e(function(){t._polyfill(["mediaelement-yt"])}))}}(),g=function(){n?c():m()};t.addPolyfill("mediaelement-yt",{test:!f,d:["dom-support"]}),d.mimeTypes={audio:{"audio/ogg":["ogg","oga","ogm"],'audio/ogg;codecs="opus"':"opus","audio/mpeg":["mp2","mp3","mpga","mpega"],"audio/mp4":["mp4","mpg4","m4r","m4a","m4p","m4b","aac"],"audio/wav":["wav"],"audio/3gpp":["3gp","3gpp"],"audio/webm":["webm"],"audio/fla":["flv","f4a","fla"],"application/x-mpegURL":["m3u8","m3u"]},video:{"video/ogg":["ogg","ogv","ogm"],"video/mpeg":["mpg","mpeg","mpe"],"video/mp4":["mp4","mpg4","m4v"],"video/quicktime":["mov","qt"],"video/x-msvideo":["avi"],"video/x-ms-asf":["asf","asx"],"video/flv":["flv","f4v"],"video/3gpp":["3gp","3gpp"],"video/webm":["webm"],"application/x-mpegURL":["m3u8","m3u"],"video/MP2T":["ts"]}},d.mimeTypes.source=e.extend({},d.mimeTypes.audio,d.mimeTypes.video),d.getTypeForSrc=function(t,a){if(-1!=t.indexOf("youtube.com/watch?")||-1!=t.indexOf("youtube.com/v/"))return"video/youtube";if(0===t.indexOf("rtmp"))return a+"/rtmp";t=t.split("?")[0].split("."),t=t[t.length-1];var i;return e.each(d.mimeTypes[a],function(e,a){return-1!==a.indexOf(t)?(i=e,!1):h}),i},d.srces=function(t,a){if(t=e(t),!a){a=[];var i=t[0].nodeName.toLowerCase(),n=p(t,i);return n.src?a.push(n):e("source",t).each(function(){n=p(this,i),n.src&&a.push(n)}),a}t.removeAttr("src").removeAttr("type").find("source").remove(),e.isArray(a)||(a=[a]),a.forEach(function(e){var a=o.createElement("source");"string"==typeof e&&(e={src:e}),a.setAttribute("src",e.src),e.type&&a.setAttribute("type",e.type),e.media&&a.setAttribute("media",e.media),t.append(a)})},e.fn.loadMediaSrc=function(t,a){return this.each(function(){a!==h&&(e(this).removeAttr("poster"),a&&e.attr(this,"poster",a)),d.srces(this,t),e(this).mediaLoad()})},d.swfMimeTypes=["video/3gpp","video/x-msvideo","video/quicktime","video/x-m4v","video/mp4","video/m4p","video/x-flv","video/flv","audio/mpeg","audio/aac","audio/mp4","audio/x-m4a","audio/m4a","audio/mp3","audio/x-fla","audio/fla","youtube/flv","video/jarisplayer","jarisplayer/jarisplayer","video/youtube","video/rtmp","audio/rtmp"],d.canThirdPlaySrces=function(t,a){var i="";return(n||f)&&(t=e(t),a=a||d.srces(t),e.each(a,function(e,t){return t.container&&t.src&&(n&&-1!=d.swfMimeTypes.indexOf(t.container)||f&&"video/youtube"==t.container)?(i=t,!1):h})),i};var y={};d.canNativePlaySrces=function(t,a){var i="";if(r){t=e(t);var n=(t[0].nodeName||"").toLowerCase(),s=(y[n]||{prop:{_supvalue:!1}}).prop._supvalue||t[0].canPlayType;if(!s)return i;a=a||d.srces(t),e.each(a,function(e,a){return a.type&&s.call(t[0],a.type)?(i=a,!1):h})}return i};var b=/^\s*application\/octet\-stream\s*$/i,w=function(){var t=b.test(e.attr(this,"type")||"");return t&&e(this).removeAttr("type"),t};d.setError=function(a,i){if(e("source",a).filter(w).length){t.error('"application/octet-stream" is a useless mimetype for audio/video. Please change this attribute.');try{e(a).mediaLoad()}catch(n){}}else i||(i="can't play sources"),e(a).pause().data("mediaerror",i),t.error("mediaelementError: "+i),setTimeout(function(){e(a).data("mediaerror")&&e(a).trigger("mediaerror")},1)};var x=function(){var e;return function(a,i,r){t.ready(n?l:"mediaelement-yt",function(){d.createSWF?d.createSWF(a,i,r):e||(e=!0,g(),x(a,i,r))}),e||!f||d.createSWF||m()}}(),k=function(e,t,a,i,n){var r;a||a!==!1&&t&&"third"==t.isActive?(r=d.canThirdPlaySrces(e,i),r?x(e,r,t):n?d.setError(e,!1):k(e,t,!1,i,!0)):(r=d.canNativePlaySrces(e,i),r?t&&"third"==t.isActive&&d.setActive(e,"html5",t):n?(d.setError(e,!1),t&&"third"==t.isActive&&d.setActive(e,"html5",t)):k(e,t,!0,i,!0))},C=/^(?:embed|object|datalist)$/i,T=function(a,i){var n=t.data(a,"mediaelementBase")||t.data(a,"mediaelementBase",{}),r=d.srces(a),s=a.parentNode;clearTimeout(n.loadTimer),e.data(a,"mediaerror",!1),r.length&&s&&1==s.nodeType&&!C.test(s.nodeName||"")&&(i=i||t.data(a,"mediaelement"),k(a,i,u.preferFlash||h,r))};d.selectSource=T,e(o).on("ended",function(a){var i=t.data(a.target,"mediaelement");(!s||i&&"html5"!=i.isActive||e.prop(a.target,"loop"))&&setTimeout(function(){!e.prop(a.target,"paused")&&e.prop(a.target,"loop")&&e(a.target).prop("currentTime",0).play()},1)}),t.ready("dom-support",function(){s||t.defineNodeNamesBooleanProperty(["audio","video"],"loop"),["audio","video"].forEach(function(a){var i=t.defineNodeNameProperty(a,"load",{prop:{value:function(){var e=t.data(this,"mediaelement");T(this,e),!r||e&&"html5"!=e.isActive||!i.prop._supvalue||i.prop._supvalue.apply(this,arguments)}}});y[a]=t.defineNodeNameProperty(a,"canPlayType",{prop:{value:function(t){var i="";return r&&y[a].prop._supvalue&&(i=y[a].prop._supvalue.call(this,t),"no"==i&&(i="")),!i&&n&&(t=e.trim((t||"").split(";")[0]),-1!=d.swfMimeTypes.indexOf(t)&&(i="maybe")),i}}})}),t.onNodeNamesPropertyModify(["audio","video"],["src","poster"],{set:function(){var e=this,a=t.data(e,"mediaelementBase")||t.data(e,"mediaelementBase",{});clearTimeout(a.loadTimer),a.loadTimer=setTimeout(function(){T(e),e=null},9)}})});var E=function(){var n=function(){if(t.implement(this,"mediaelement")&&(T(this),r)){var i,n,s=this,o=function(){var t=e.prop(s,"buffered");if(t){for(var a="",i=0,n=t.length;n>i;i++)a+=t.end(i);return a}},l=function(){var a=o();a!=n&&(n=a,t.info("needed to trigger progress manually"),e(s).triggerHandler("progress"))};e(this).on({"play loadstart progress":function(e){"progress"==e.type&&(n=o()),clearTimeout(i),i=setTimeout(l,999)},"emptied stalled mediaerror abort suspend":function(e){"emptied"==e.type&&(n=!1),clearTimeout(i)}}),"ActiveXObject"in a&&e.prop(this,"paused")&&!e.prop(this,"readyState")&&e(this).is('audio[preload="none"][controls]:not([autoplay],.nonnative-api-active)')&&e(this).prop("preload","metadata").mediaLoad()}},s=!1;t.ready("dom-support",function(){s=!0,t.addReady(function(t,a){var i=e("video, audio",t).add(a.filter("video, audio")).each(n);!v.loaded&&e("track",i).length&&v(),i=null})}),r&&!s&&t.addReady(function(a,n){s||e("video, audio",a).add(n.filter("video, audio")).each(function(){return d.canNativePlaySrces(this)?(i&&!t.modules.track.options.override||v.loaded||!e("track",this).length||v(),h):(g(),s=!0,!1)})})};i&&t.defineProperty(TextTrack.prototype,"shimActiveCues",{get:function(){return this._shimActiveCues||this.activeCues}}),r?(t.isReady("mediaelement-core",!0),E(),t.ready("WINDOWLOAD mediaelement",g)):t.ready(l,E),t.ready("track",v)})}(jQuery,Modernizr,webshims);
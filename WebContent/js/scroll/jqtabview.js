/**
 * @author A.Myagmar
 * @description jqtab-old.html deerh jisheeg Ganbold gedeg zaluu hiisen. Hiisen shiidel ni huurhun baisan bolovch ashiglah
 argachlal ni jaahan oilgomjgui baisan.
 jqtab.html deerh jisheeg A.Myagmar hiisen buguud iluu oilgomjtoi bolgoj saijruulav.
 Shiidsen shiidel gevel class -aar elementuudee barij avdag bolson.
 Iluu dutuu uildel hiigeegui.
 * @created 2011.10.05
 * @last update 2011.10.06 by A.Myagmar
 */

if(typeof SODON == 'undefined') { SODON = {}; SODON.widget = {}; SODON.example = {}} else { if( typeof SODON.widget == 'undefined') { SODON.widget = {} } if( typeof SODON.example == 'undefined') { SODON.example = {} } }

(function() {

    SODON.widget.JQTabView = function(arg) {
        this.init.apply(this, arguments);
    };

    SODON.widget.JQTabView.prototype = {

        // for doms start
        navs: '',                         // navs's id
        navClassName: 'tab-nav',          // nav's class name
        contents: '',                     // contents's id
        contentClassName: 'tab-content',  // content's class name
        // end of doms

        // for config start
        tag: 'ul',                 // ul | table | div | a Tag gedeg ni yamar argachlalaar hiihiig zaaj baigaa yum. Jishee ni ul argachlalaar hiij bolno.
        decorateCorners: false,    // false | true
        behavior: 'click',         // click | over
        defaultIndex: 0,           // default index
        // end of config

        navSelector: '',           // uridchilan beldsen nav selector
        navParentSelector: '',     // uridchilan beldsen nav parent selector
        currIndex: 0,             // guideg index -iig hadgalna.
        contentSelector: '',       // uridchilan beldsen content selector
        status : 'ok',             // status: ok | template_error status == "ok" bol heviin aldaagui gesen ug. Herev "ok" bish bol yamar negen aldaa garsan gesen ug.
        status_msg: '',            // status message. Aldaa garahad ene msg utgatai bolson baidag.
        log: false,                // log hevleh esehiig shiidne. true bol log bichne. false bol log bichihgui.

        /**
         * JQTabView class iin ehlel function bogood constructoroor damjij irsen argument
         * objectiig shalgaj event uudiig uyj ogno.
         * @param arg Constructoroos ireh tohirgoonii object
         */
        init: function(arg) {
            var self = this;
            if ( arg != null && typeof arg.contents != 'undefined' && typeof arg.navs != 'undefined' ) {

                // for doms start
                this.navs = arg.navs;
                this.contents = arg.contents;

                if ( typeof arg.navClassName != "undefined" ) {
                    this.navClassName = arg.navClassName;
                }

                if ( typeof arg.contentClassName != "undefined" ) {
                    this.contentClassName = arg.contentClassName;
                }
                // end of doms

                // for config start
                if(typeof arg.tag != 'undefined') {
                    this.tag = arg.tag;
                }

                // for config start
                if ( typeof arg.decorateCorners != 'undefined' ) {
                    this.decorateCorners = arg.decorateCorners;
                }

                if ( typeof arg.behavior != 'undefined' ) {
                    this.behavior = arg.behavior;
                }

                if ( typeof arg.defaultIndex != 'undefined' ) {
                    this.defaultIndex = arg.defaultIndex;
                    this.currIndex = this.defaultIndex;
                }

                if ( typeof arg.log != 'undefined' ) {
                    this.log = arg.log;
                }
                // end of

                // selectoruudiig uridchilan beldej bn
                if ( this.tag == 'ul' ) {
                    this.navSelector = '#' + this.navs + ' > ul > li.' + this.navClassName + ' a';
                    this.navParentSelector = '#' + this.navs + ' > ul > li.' + this.navClassName;
                    this.contentSelector = '#' + this.contents + ' > div.' + this.contentClassName;
                } else if ( this.tag == 'table' ) {
                    this.navSelector = '#' + this.navs + ' > table > tr > td.' + this.navClassName + ' a';
                    this.navParentSelector = '#' + this.navs + ' > table > tr > td.' + this.navClassName;
                    this.contentSelector = '#' + this.contents + ' > div.' + this.contentClassName;
                } else if ( this.tag == 'div' ) {
                    this.navSelector = '#' + this.navs + ' > div.' + this.navClassName + ' a';
                    this.navParentSelector = '#' + this.navs + ' > div.' + this.navClassName;
                    this.contentSelector = '#' + this.contents + ' > div.' + this.contentClassName;
                } else if ( this.tag == 'a' ) {
                    this.navSelector = '#' + this.navs + ' > a.' + this.navClassName;
                    this.navParentSelector = '#' + this.navs + ' > a.' + this.navClassName;
                    this.contentSelector = '#' + this.contents + ' > div.' + this.contentClassName;
                }
                // end of

                // template -iig zuv bichigdsen esehiig shalgaj bn.
                if ( !$(this.navSelector) || !$(this.navParentSelector) || !$(this.contentSelector) ) {
                    this.status = 'template_error';
                }

                if ( this.status == 'ok' ) {
                    this.__tabFunction(this.defaultIndex);
                    self.__bindEventHandler();
                    if(this.decorateCorners) {
                        self.__doDecorateCorners();
                    }
                } else {
                    self.__errorReport();
                }
            } else {
                this.status = 'arg_error';
            }
        },

        prev: function() {
            if ( this.log ) console.log("prev button clicked");
            if ( this.log ) console.log("this.currIndex= " + this.currIndex);
            if ( this.currIndex > 0 ) {
                this.currIndex--;
                if ( this.log ) console.log("this.currIndex= " + this.currIndex);
                this.__tabFunction(this.currIndex);
            }
        },

        next: function() {
            if ( this.log ) console.log("next button clicked");
            if ( this.log ) console.log("this.currIndex= " + this.currIndex);
            if ( this.currIndex < $(this.navSelector).length - 1 ) {
                this.currIndex++;
                if ( this.log ) console.log("this.currIndex= " + this.currIndex);
                this.__tabFunction(this.currIndex);
            }
        },

        __bindEventHandler: function() {
            var self = this;
            if ( this.behavior == 'click' ) {
                $(this.navSelector).each(function(i) {
                    $(this).bind('click', {index:i}, function(event){
                        event.preventDefault();
                        self.__tabFunction(event.data.index);
                        self.currIndex = event.data.index;
                    });
                });
            } else if ( this.behavior == 'over' ) {
                $(this.navSelector).each(function(i) {
                    $(this).bind('mouseover', {index:i}, function(event){
                        event.preventDefault();
                        self.__tabFunction(event.data.index);
                        self.currIndex = event.data.index;
                    });
                });
            }
        },

        __tabFunction: function(index) {
            $(this.navParentSelector).removeClass('sel');
            $(this.navParentSelector + ':eq(' + index + ')').addClass('sel');
            $(this.contentSelector).hide();
            $(this.contentSelector + ':eq(' + index + ')').show();
        },

        /**
         * @description nav's a tagnii dotor zuun, center, baruun nemelt span tag -uud avdag yum
         * @method __doDecorateCorners
         */
        __doDecorateCorners: function() {
            $(this.navSelector).each(function() {
                var text = $(this).text();
                $(this).html('<span class="left"></span><span class="center">' + text + '</span><span class="right"></span>');
            });
        },

        /**
         * @description Aldaag hevlegch method yum.
         * @method __errorReport
         */
        __errorReport: function() {
            console.log(this.status);
            console.log(this.status_msg);
        }
    };
})();
if(typeof SODON == "undefined" || !SODON){ var SODON = {}; SODON.util = {}; SODON.widget = {}; SODON.example = {}; }

(function() {

    /**
     * @namespace SODON.widget
     * @class JQScrollView
     * @constructor
     * @param {HTMLElement | String} container
     * @param {Object} config
     */
    SODON.widget.JQScrollView = function(config) {
        this.init.apply(this, arguments);
    };

    SODON.widget.JQScrollView.prototype = {

        contents: null,
        upBtn: null,
        downBtn: null,
        prevBtn: null,
        nextBtn: null,

        screenSize: 1,
        itemWidth: null,
        itemHeight: null,
        itemSpace: null,
        direction: "h",
        speed: 500,
        auto: false,
        repeat: "repeat",
        log: false,

        // nemelt tohirgoonuud
        followBtn: null,         // button dagagch unen.mn iin ontsloh deer ashiglav. bbc.com deerees sanaa avav.

        status:null,        // mminfo.mn -d ashiglahad zoriulav scroll-iin status-iig ni haruulahad hereglesen

        currIndex: 0,
        limitIndex: 0,

        init: function(config) {
//            if ( this.log ) console.log("init succesful");

            // set config
            this.__setConfig(config);

            // call init css
            this.__initCSS();

            //
            this.limitIndex = (this.itemsSelector.length - this.screenSize);
//            if ( this.log ) console.log("this.itemsSelector.length= " + this.itemsSelector.length);
//            if ( this.log ) console.log("this.screenSize= " + this.screenSize);
//            if ( this.log ) console.log("this.limitIndex= " + this.limitIndex);

            // call init events
            this.__initEvents();

            // for nemelt tohirgootoi holbootoi heseg start
            if ( this.followBtn != null ) this.__initFollowBtn();

            if(this.status != null) this.__initStatus();
            // end of nemelt tohirgootoi holbootoi heseg
        },

        /**
         * @method prevMove
         */
        prevMove: function() {
//            if ( this.log ) console.log("prevBtn clicked");
            if ( this.currIndex > 0 ) {
//                if ( this.log ) console.log("prevMove successful");
                this.currIndex--;
                this.__move("h");
            }
        },

        /**
         * @method nextMove
         */
        nextMove: function() {
//            if ( this.log ) console.log("nextBtn clicked");
            if ( this.currIndex < this.limitIndex ) {
//                if ( this.log ) console.log("nextMove successful");
                this.currIndex++;
                this.__move("h");
            }
        },

        /**
         * @method upMove
         */
        upMove: function() {
//            if ( this.log ) console.log("upBtn clicked");
            if ( this.currIndex > 0 ) {
//                if ( this.log ) console.log("upMove successful");
                this.currIndex--;
                this.__move("v");
            }
        },

        /**
         * @method downMove
         */
        downMove: function() {
//            if ( this.log ) console.log("downBtn clicked");
            if ( this.currIndex < this.limitIndex ) {
//                if ( this.log ) console.log("downMove successful");
                this.currIndex++;
                this.__move("v");
            }
        },

        /**
         * @method play
         */
        play: function() {
            if ( this.currIndex >= this.limitIndex ) {
                this.currIndex = 0;
            }
            else {
                this.currIndex++;
            }
            this.__move(this.direction);
        },

        __setConfig: function(config) {

            // for doms start
            this.contents = config.contents;

            // for config
            if ( typeof config.upBtn != "undefined" ) {
                this.upBtn = config.upBtn;
            }

            if ( typeof config.downBtn != "undefined" ) {
                this.downBtn = config.downBtn;
            }

            if ( typeof config.prevBtn != "undefined" ) {
                this.prevBtn = config.prevBtn;
            }

            if ( typeof config.nextBtn != "undefined" ) {
                this.nextBtn = config.nextBtn;
            }

            if ( typeof config.screenSize != "undefined" ) {
                this.screenSize = config.screenSize;
            }

            if ( typeof config.itemWidth != "undefined" ) {
                this.itemWidth = config.itemWidth;
            }

            if ( typeof config.itemHeight != "undefined" ) {
                this.itemHeight = config.itemHeight;
            }

            if ( typeof config.itemSpace != "undefined" ) {
                this.itemSpace = config.itemSpace;
            }

            if ( typeof config.direction != "undefined" ) {
                this.direction = config.direction;
            }

            if ( typeof config.speed != "undefined" ) {
                this.speed = config.speed;
            }

            if ( typeof config.auto != "undefined" ) {
                this.auto = config.auto;
            }

            if ( typeof config.repeat != "undefined" ) {
                this.repeat = config.repeat;
            }

            if ( typeof config.log != "undefined" ) {
                this.log = config.log;
            }

            if ( typeof config.followBtn != "undefined" ) {
                this.followBtn = config.followBtn;
            }

            if ( typeof config.status != "undefined" ) {
                this.status = config.status;
            }
        },

        __initCSS: function() {
//            if ( this.log ) console.log("init __initCSS");

            this.contentsSelector =       $('#' + this.contents);
            this.itemsSelector =          $('#' + this.contents + ' > .item');
            this.maskSelector =           $('#' + this.contents).parent();
            this.sepSelector =            $('#' + this.contents + ' > .sep');

            this.maskSelector.css({
                "position": "relative",
                "overflow": "hidden"
            });

            this.contentsSelector.css({
                "position": "absolute",
                "top": "0px",
                "left": "0px"
            });

            if ( this.direction == "h" ) {

                //
                this.maskSelector.css({
                    "width": ( this.screenSize * ( this.itemWidth + this.itemSpace ) - this.itemSpace ) + "px",
                    "height": this.itemHeight + "px"
                });

                //
                this.contentsSelector.css({
                    "width": ( this.itemsSelector.length * ( this.itemWidth + this.itemSpace ) ) + "px",
                    "height": this.itemHeight + "px"
                });

                //
                this.itemsSelector.css({
                    "float": "left",
                    "width": this.itemWidth + "px",
                    "height": this.itemHeight + "px"
                });

                //
                this.sepSelector.css({
                    "float": "left",
                    "width": this.itemSpace + "px",
                    "height": this.itemHeight + "px"
                });
            } else {

                //
                this.maskSelector.css({
                    "width": this.itemWidth + "px",
                    "height": ( this.screenSize * ( this.itemHeight + this.itemSpace ) - this.itemSpace ) + "px"
                });

                //
                this.contentsSelector.css({
                    "width": this.itemWidth + "px",
                    "height": ( this.itemsSelector.length * ( this.itemHeight + this.itemSpace ) ) + "px"
                });

                //
                this.itemsSelector.css({
                    "width": this.itemWidth + "px",
                    "height": this.itemHeight + "px"
                });

                //
                this.sepSelector.css({
                    "height": this.itemSpace + "px",
                    "width": this.itemWidth + "px"
                });
            }
        },

        __initEvents: function() {
            var self = this;

            if ( this.direction == "h" ) {

                $('#' + this.prevBtn).click(function(event){
                    self.prevMove();

                    //
                    if ( self.followBtn != null ) self.__showPrevBtnFollow();
                });

                $('#' + this.nextBtn).click(function(event){
                    self.nextMove();

                    //
                    if ( self.followBtn != null ) self.__showNextBtnFollow();
                });
            } else {

                $('#' + this.upBtn).click(function(event){
                    self.upMove();

                    //
                    if ( self.followBtn != null ) self.__showUpBtnFollow();
                });

                $('#' + this.downBtn).click(function(event){
                    self.downMove();

                    //
                    if ( self.followBtn != null ) self.__showDownBtnFollow();
                });
            }
        },

        /**
         * @method __move
         */
        __move: function(direction) {
//            if ( this.log ) console.log("call __move and direction= " + direction);

            if ( direction == "h" ) {
                this.contentsSelector.animate({
                    left: '-' + (this.currIndex * ( this.itemWidth + this.itemSpace ))
                }, this.speed, 'linear', function() {
                    // Animation complete.
                });
            } else {
                this.contentsSelector.animate({
                    top: '-' + (this.currIndex * ( this.itemHeight + this.itemSpace ))
                }, this.speed, 'linear', function() {
                    // Animation complete.
                });
            }

            if(this.status!=null) {
                this.__initStatus();
            }

        },

        /* FOR FOLLOW BUTTON START */

        /**
         * @method __initFollowBtn
         */
        __initFollowBtn: function() {
//            if ( this.log ) console.log("__initFollowBtn");
            var self = this;

            if ( this.direction == "h" ) {
                $('#' + this.prevBtn).bind('mouseover', function(event) {
//                    if ( this.log ) console.log("this.prevBtn mouseover");
                    self.__showPrevBtnFollow();
                });
                $('#' + this.prevBtn).bind('mouseout', function(event) {
//                    if ( this.log ) console.log("this.prevBtn mouseout");
                    self.__hidePrevBtnFollow();
                });

                $('#' + this.nextBtn).bind('mouseover', function(event) {
//                    if ( this.log ) console.log("this.nextBtn mouseover");
                    self.__showNextBtnFollow();
                });
                $('#' + this.nextBtn).bind('mouseout', function(event) {
//                    if ( this.log ) console.log("this.nextBtn mouseout");
                    self.__hideNextBtnFollow();
                });
            } else {
                $('#' + this.upBtn).bind('mouseover', function(event) {
//                    if ( this.log ) console.log("this.upBtn mouseover");
                    self.__showUpBtnFollow();
                });
                $('#' + this.upBtn).bind('mouseout', function(event) {
//                    if ( this.log ) console.log("this.upBtn mouseout");
                    self.__hideUpBtnFollow();
                });

                $('#' + this.downBtn).bind('mouseover', function(event) {
//                    if ( this.log ) console.log("this.downBtn mouseover");
                    self.__showDownBtnFollow();
                });

                $('#' + this.downBtn).bind('mouseout', function(event) {
//                    if ( this.log ) console.log("this.downBtn mouseout");
                    self.__hideDownBtnFollow();
                });
            }
        },

        __showPrevBtnFollow: function() {
            $('#' + this.followBtn.prevFollow + ' .item').hide();
            $('#' + this.followBtn.prevFollow + ' .item:eq(' + ( this.currIndex - 1 ) + ')').show();
        },

        __hidePrevBtnFollow: function() {
            $('#' + this.followBtn.prevFollow + ' .item').hide();
        },

        __showNextBtnFollow: function() {
            $('#' + this.followBtn.nextFollow + ' .item').hide();
            $('#' + this.followBtn.nextFollow + ' .item:eq(' + ( this.currIndex + this.screenSize ) + ')').show();
        },

        __hideNextBtnFollow: function() {
            $('#' + this.followBtn.nextFollow + ' .item').hide();
        },

        __showUpBtnFollow: function() {
            $('#' + this.followBtn.upFollow + ' .item').hide();
            $('#' + this.followBtn.upFollow + ' .item:eq(' + ( this.currIndex - 1 ) + ')').show();
        },

        __hideUpBtnFollow: function() {
            $('#' + this.followBtn.upFollow + ' .item').hide();
        },

        __showDownBtnFollow: function() {
            $('#' + this.followBtn.downFollow + ' .item').hide();
            $('#' + this.followBtn.downFollow + ' .item:eq(' + ( this.currIndex + this.screenSize ) + ')').show();
        },

        __hideDownBtnFollow: function() {
            $('#' + this.followBtn.downFollow + ' .item').hide();
        },

        /* END OF FOLLOW BUTTON */


        /* FOR STATUS */
        /**
         * @method __initStatus
         */
        __initStatus: function() {
//            if ( this.log ) console.log("__initStatus");
            this.statusSelector = '#' + this.status + ' > li.item';
            $(this.statusSelector).removeClass('sel');
            var first = this.currIndex;
            var last = this.currIndex + this.screenSize;
            if(last>$(this.statusSelector).length) {
                last = $(this.statusSelector).length;
                first = 1;
            }
            for(var i=first; i<last; i++) {
                $(this.statusSelector  + ':eq(' + i + ')' ).addClass('sel');
            }
        }
        /* END OF STATUS */
    }

})();
/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {

 // Define changes to default configuration here.
 // For the complete reference:
 // http://docs.ckeditor.com/#!/api/CKEDITOR.config

 // The toolbar groups arrangement, optimized for two toolbar rows.

 config.toolbarGroups = [
 
 { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
 { name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
 { name: 'links' },
 { name: 'insert' },
 { name: 'forms' },
 { name: 'tools' },
 { name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
 { name: 'others' },
 '/',
 { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
 { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
 { name: 'styles' },
 { name: 'colors'},
 { name: 'about' }
 
 ];
 
 //

 //


 // Remove some buttons, provided by the standard plugins, which we don't
 // need to have in the Standard(s) toolbar.
 config.removeButtons = 'Underline,Subscript,Superscript';
 //config.extraPlugins = "nextpage";//新建插件
 // Se the most common block elements.
 config.format_tags = 'p;h1;h2;h3;pre';
 // config.width = 98%;
 config.height = 500;
 // Make dialogs simpler.
 config.removeDialogTabs = 'image:advanced;link:advanced';
 config.language="utf-8";
 //config.filebrowserBrowseUrl = '/mongoliawebsite/ckfinder/imageupload.html';
 //config.filebrowserBrowseUrl = '/mongoliawebsite/ckfinder/ckfinder.html';
 //config.filebrowserImageBrowseUrl = '/mongoliawebsite/ckfinder/ckfinder.html?type=Images';
 //config.filebrowserFlashBrowseUrl = '/mongoliawebsite/ckfinder/ckfinder.html?type=Flash';
 //config.filebrowserUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
 //config.filebrowserImageUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
 config.filebrowserImageUploadUrl = 'fileupload.do?command=QuickUpload&type=Images';
 //config.filebrowserFlashUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
 config.filebrowserWindowWidth = '1000';   
 config.filebrowserWindowHeight = '700'; 
 config.fontSize_style =
    {
        element		: 'span',
        styles		: { 'font-size' :21 },
        overrides	: [ { element : 'font', attributes : { 'size' : null } } ]
    };


 };
/*
CKEDITOR.editorConfig = function(config) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// 编辑器样式，有三种：'kama'（默认）、'office2003'、'v2'
	config.skin = 'moono';
	config.uiColor = '#BFEE62';
	config.width = '80%';
	config.height = 240;
	config.toolbar = 'Define';
	config.toolbar_Define = [
			['Cut', 'Copy', 'Paste'],
			['Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll',
					'RemoveFormat'],
			['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript',
					'Superscript'],
			['Format', 'Font', 'FontSize', 'JustifyLeft', 'JustifyCenter',
					'JustifyRight'], ['TextColor', 'BGColor']];
	config.font_names = '宋体;黑体;楷体_GB2312;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana';

	//是否强制复制来的内容去除格式 plugins/pastetext/plugin.js 
	config.forcePasteAsPlainText = false//不去除

	//当从word里复制文字进来时，是否进行文字的格式化去除 plugins/pastefromword/plugin.js
	config.pasteFromWordIgnoreFontFace = false; //默认为忽略格式
	//是否使用等标签修饰或者代替从word文档中粘贴过来的内容 plugins/pastefromword/plugin.js	
	config.pasteFromWordKeepsStructure = false;
	//从word中粘贴内容时是否移除格式 plugins/pastefromword/plugin.js
	config.pasteFromWordRemoveStyle = false
	config.pasteFromWordRemoveFontStyles = false;
	//是否使用完整的html编辑模式 如使用，其源码将包含：<html><body></body></html>等标签
	//config.fullPage = true;
	config.language = "utf-8";
	config.filebrowserImageUploadUrl = 'fileupload.do?command=QuickUpload&type=Images';
	config.filebrowserWindowWidth = '1000';
	config.filebrowserWindowHeight = '700';

};*/

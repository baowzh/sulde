var switchdiv = function(currentdivid,nextdivid) {
	var display= $("#"+currentdivid).css("display");
	if(display!='none'){
	 $("#"+currentdivid).css("display","none");
	 $("#"+nextdivid).css("display","block");
	}else{
	 $("#"+currentdivid).css("display","block");
	 $("#"+nextdivid).css("display","none");	
	}
};
var setHtml=function(divid,html){
 $("#"+divid).html("<p>"+html+"</p>");	
}
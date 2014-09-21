/**
 * 根据查询条件查询用户
 */
var searchusers = function() {
	$("#queryForm").submit();
};
var paginguser = function(pageindex) {
	$("#pageindex").val(pageindex);
	$("#queryForm").submit();
};
/**
 * 清空查询表单
 */
var clearQueryForm = function() {
	$("#district").val("");
	$("#qx").val("");
	$("#username").val("");
	$("#strregtime").val("");
	$("#endregtime").val("");
};
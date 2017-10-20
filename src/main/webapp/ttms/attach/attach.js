$(document).ready(function(){
   $("#uploadFormId")
   .on("click",".btn-upload",doUpload)
   .on("click",".btn-down",doDownload)
   doGetObjects();
});
function doDownload(){
	var id=$(this).parent().parent().data("id");
	var url="attachment/doDownload.do?id="+id;
	document.location.href=url;
}
function doGetObjects(){
	var url="attachment/doFindObjects.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			setTableBodyRows(result.data);
		}else{
			alert(result.message);
		}
	});
}
function setTableBodyRows(list){
	var tBody=$("#tbodyId");tBody.empty();
	for(var i in list){
		var tr=$("<tr></tr>");
		tr.data("id",list[i].id);
		tr.append("<td>"+list[i].title+"</td>");
		tr.append("<td>"+list[i].fileName+"</td>");
		tr.append("<td>"+list[i].contentType+"</td>");
		tr.append('<td><button type="button" class="btn btn-default btn-down">down</button></td>')
	    tBody.append(tr);
	}
}

/*点击文件上传按钮执行此函数*/
function doUpload(){
	//异步提交表单($.ajaxSubmit为异步提交表单)
	//使用此函数时需要在页面引入(jquery.form.js )
	$("#uploadFormId").ajaxSubmit({
		type:"post",
		url:"attachment/doUpload.do",
		dataType:"json",
		success:function(result){
		alert(result.message);
		doGetObjects();
		}
	});
	//$("#uploadFormId").resetForm();
	return false;//防止表单重复提交的一种方式
}

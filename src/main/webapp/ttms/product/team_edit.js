//页面加载完成执行
$(document).ready(function(){
	//初始化所属项目的select列表
	doGetProjectIdAndNames();
	//在模态框的对应按钮上注册事件
	$("#modal-dialog")
	.on("click",".ok",doSaveObject);
	//在模态框隐藏时解除事件的注册
	$("#modal-dialog")
	//hidden.bs.modal为固定写法表示模态框隐藏事件
	.on("hidden.bs.modal",function(){
		//.ok上移除click事件
		$(this).off("click",".ok");
		$(this).removeData("id");
	});
	//判定模态框上是否绑定了id值
	//var id=$("#modal-dialog").data("id");
	//if(id)doFindObjectById();
});
function doFindObjectById(){
	var url="team/doFindObjectById.do";
	var id=$("#modal-dialog").data("id");
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
		//初始化页面数据
		 doInitEditFormData(result.data);
		}else{
		 alert(result.message);
		}
	})
}
function doInitEditFormData(result){
	$("#nameId").val(result.name);
	$("#noteId").html(result.note);
	$('#editFormId input[name="valid"]')
	.each(function(){
		if($(this).val()==result.valid){
			$(this).prop("checked",true)
		}
	});
	$("#projectId").val(result.projectId);
	/*$('#projectId option').each(function(){
		console.log("$(option).val()="
				+$(this).val()+"/"+result.projectId)
		if($(this).val()==result.projectId){
			$(this).prop("selected",true)
		}
	});*/
}

function doSaveObject(){
	//1.验证表单数据(非空验证)
	if(!$("#editFormId").valid())return;
	//2.获得表单数据
	var params=getEditFormData();
	var id=$("#modal-dialog").data("id");
	if(id)params.id=id;//假如是修改要追加id
	//3.提交异步请求,将数据写入到服务端
	var saveUrl="team/doSaveObject.do";
	var updateUrl="team/doUpdateObject.do";
	var url=id?updateUrl:saveUrl;
	$.post(url,params,function(result){
		if(result.state==1){
			$("#modal-dialog").modal("hide");
			doGetObjects();
		}else{alert(result.message);}
	});
}
/*获得表单数据*/
function getEditFormData(){
var params={
"name":$("#nameId").val(),
"projectId":$("#projectId").val(),
"valid":$('input[name="valid"]:checked').val(),
"note":$('#noteId').val()
};return params;
}
/*获得项目的id和名称*/
function doGetProjectIdAndNames(){
	var url="team/doFindPrjIdAndNames.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			doInitProjectSelect(result.data);
			//修改时,等select列表页面初始化完成要根据
			//id初始化其它数据
			var id=$("#modal-dialog").data("id");
			if(id)doFindObjectById();
		}else{
			alert(result.message);
		}
	});
}
/*初始化所属项目的select下拉框*/
function doInitProjectSelect(list){
	var select=$("#projectId");
	select.append(
	"<option>==请选择==</option>")
	var option=
	"<option value=[id]>[name]</option>"
	for(var i in list){
		select.append(
		option.replace("[id]",list[i].id)
		      .replace("[name]",list[i].name));
	}
}




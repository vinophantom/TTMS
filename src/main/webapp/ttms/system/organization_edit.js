//获得模态框绑定的id
$(document).ready(function(){
	$('#modal-dialog').on('click', '.ok', doSubmitEditForm);
	$("#modal-dialog").on("hidden.bs.modal", function() {
		$(this).off('click', '.ok')
		       .removeData("bs.modal")
		       .data("id","")
		       .data("parentId","");
		console.log("parentId="+$("#modal-dialog"));
	});
	var id=$('#modal-dialog').data('id');
	console.log("id="+id);
	if(id)doGetObjectById();
})
//提交表单数据
function doSubmitEditForm(){
    var params=doGetFormParameterValues();
    var id=$('#modal-dialog').data('id');
    var url=id?'org/update.do':'org/save.do';
    $.post(url,params,function(result){
    	if(result.state==1){
    		$('#modal-dialog').modal('hide');
    		doGetObjects();
    	}else{
    		alert(result.message);
    	}
    })
}
//获得表单数据
function doGetFormParameterValues(){
	var params={
		"id":$('#modal-dialog').data('id'),
		"name":$('#nameId').val(),
		"code":$('#codeId').val(),
		"parentId": $("#modal-dialog").data("parentId"),
	    "valid":$('input[name="valid"]:checked').val(),
	    "note":$('#noteId').val(),
	}
	return params;
}
//修改时根据id获得相关团信息
function doGetObjectById(){
	var url='org/findById.do';
	var id=$('#modal-dialog').data('id');
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
		 doFillUpdateForm(result.data);
		}else{
		 alert(result.message);
		}
	});
}
//修改时填充页面数据
function doFillUpdateForm(obj){
	$('#nameId').val(obj.name);
	$('#codeId').val(obj.code);
 	$('#noteId').html(obj.note);
 	$('input[name="valid"]').each(function(){
 		if($(this).val()==obj.valid){
 			$(this).prop("checked",true);
 		}
 	})
}




$(document).ready(function(){
	//条件查询
	$('#formHead').on('click','.btn-search',doGetObjects)
	//新增
	$('#formHead').on('click','.btn-add',showAddUI)
	//修改
	$('#formHead').on('click','.btn-update',showEditUI)
	//删除
	$('#formHead').on('click','.btn-delete',deleteObject)
	doGetObjects()
})
//删除角色
function deleteObject(){
	var checkedIds = getCheckedId();
	if(!checkedIds){
		alert('请选择要删除的选项！');
		return;
	}
	var param = {'roleId':checkedIds};
	var url = 'role/doDeleteObject.do';
	$.post(url,param,function(result){
		if(result.state==1){
			alert('删除成功！');
			doGetObjects();
		}else{
			alert(result.message);
		}
	})
}
//显示修改角色页面
function showEditUI(){
	var checkecIds = getCheckedId();
	if(!checkecIds){
		alert('请选择要修改的选项！');
		return;
	}
	$('.content').data('roleId',checkecIds);
	$('.content').load('role/editUI.do');
}
//显示新增页面
function showAddUI(){
	var url = 'role/editUI.do';
	$('.content').load(url);
}
function doGetObjects(){
	var params = getQueryFormData();
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent)pageCurrent=1;
	params.pageCurrent=pageCurrent;
	var url = 'role/doFindObjects.do';
	$.post(url,params,function(result){
	 if(result.state==1){
	 setTableBodyRows(result.data.list);
	 setPagination(result.data.pageObject);
	 }else{
	  alert(result.message);
	 }
	})
}

//获取条件查询条件
function getQueryFormData(){
	var params = {'name':$('#roleName').val()}
	return params;
}

//初始化列表页面
function setTableBodyRows(list){
	var tBody=$('#tbodyId');
	tBody.empty();
	var tds='<td><input type="radio" name="checkedItem" value="[id]"></td>'+
	        '<td>[roleId]</td>'+
	        '<td>[name]</td>'+
	        '<td>[note]</td>';
	for(var i in list){
	    var tr=$('<tr></tr>');
	    tr.append(tds.replace('[id]',list[i].id)
	    		  .replace('[roleId]',list[i].id)
	    		  .replace('[name]',list[i].name)
	    		  .replace('[note]',list[i].note));
	    
	    tBody.append(tr);
	}
}

//获得选中的id，然后拼接成字符串
function getCheckedId(){
	var checkedId;
	$('tbody input[name="checkedItem"]').each(function(){
		if($(this).is(':checked')){  //或者用prop('checked')
			checkedId=$(this).val();
		}
	})
	return checkedId;
}
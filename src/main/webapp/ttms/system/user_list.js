$(document).ready(function(){
$('#userFormHead').on('click','.btn-search',doQueryObjects)
$('#userFormHead').on('click','.btn-add,.btn-update',doLoadEditPage)
$('#tbody').on('click','.btn-valid',doValidById)
doGetObjects();
});
/*禁用启用用户信息*/
function doValidById(){
	var userId = $(this).parent().parent().data('userId');
	var valid = $(this).val();
	var params = {'userId':userId,'valid':valid};
	var url ='user/doValidById.do';
	$.post(url,params,function(result){
		if(result.state==1){
		 alert("操作成功！");
		 doGetObjects();
		}else{
		 alert(result.message);
		}
	});
}
/*加载编辑页面到制定位置*/
function doLoadEditPage(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加菜单信息";
	}
	if($(this).hasClass("btn-update")){
	    title="修改菜单信息"
	    var id=getCheckedId();//获得选中的记录id值
		if(!id){
		  alert("请先选择");return;
		}
		$('.content').data('userId',id);
		console.log("id="+id);
	}
	var url="user/editUI.do"
	$(".content").load(url,function(){
		$(".panel-heading").html(title)
	})
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

//条件查询  -- 每次条件查询要将当前也设为1
function doQueryObjects(){
	$("#pageId").data('pageCurrent',1);
	doGetObjects();
}
function doGetObjects(){
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent){
		pageCurrent=1;
	}
	var params = getQueryFormData();
	params.pageCurrent=pageCurrent;
	var url = 'user/doFindObjects.do';
	$.post(url,params,function(result){
		if(result.state==1){
			setTableBodyRows(result.data.list);
			setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}
	})
}

//初始化列表页面
function setTableBodyRows(list){
	var tBody=$('tbody');tBody.empty();
	var tds='<td><input type="radio" name="checkedItem" value="[id]"></td>'+
	'<td>[username]</td>'+
	'<td>[email]</td>'+
	'<td>[mobile]</td>'+
	'<td>[state]</td>'+
	'<td><button type="button" class="btn btn-default btn-xs btn-valid" value="[validchange]">[stateStr]</button></td>';
	for(var i in list){
		var state = list[i].valid==0?'<span class="label label-danger">禁用</span>':'<span class="label label-success">启用</span>';
		var stateStr = list[i].valid==0?'启用':'禁用';
		var validchange = list[i].valid==0?'1':'0';
		var tr=$('<tr></tr>');
		tr.data('userId',list[i].id);
	    tr.append(
	    tds.replace('[id]',list[i].id)
	    .replace('[username]',list[i].username)
	    .replace('[email]',list[i].email)
	    .replace('[mobile]',list[i].mobile)
	    .replace('[state]',state)
	    .replace('[validchange]',validchange)
	    .replace('[stateStr]',stateStr));
	    tBody.append(tr);
	}
}
//获取条件查询条件
function getQueryFormData(){
	var params = {'username':$('#userName').val()}
	return params;
}
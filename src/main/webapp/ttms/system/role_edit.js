/**
 * 点击修改，角色id绑定在container上
 */
var ztree,SUCCESS=1; 
var setting = {
	data : {
	    simpleData : {
			enable : true,
			idKey : "id",  //节点数据中保存唯一标识的属性名称
			pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
			rootPId : null  //根节点id
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
}
$(document).ready(function(){
	setBtnVal();
	//点击返回按钮
	$('#editRoleForm').on('click','.btn_return',doBack);
	//点击添加/修改按钮
	$('#editRoleForm').on('click','.btn_save',doSaveOrUpdate)
});

function setBtnVal(){
	var roleId = $('.content').data('roleId');
	if(roleId){   //如果有值，进行的是修改操作
		$('#editRoleForm .btn_save').val('修改');
		$('#editTitle').text('修改');
		doFindObjectById(roleId);
	}else{   //进行的添加操作
		$('#editRoleForm .btn_save').val('添加');
		$('#editTitle').text('新增');
		doLoadZTreeData();
	}
}
//根据id查询角色信息
function doFindObjectById(roleId){
	var param = {'roleId':roleId}
	var url = 'role/doFindObjectById.do';
	$.post(url,param,function(result){
		if(result.state == 1){
		doSetEditFormData(result.data);
		}else{
		alert(result.message);
		}
	})
}

//回显
function doSetEditFormData(data){
	$('#roleName').val(data.SysRole.name);
	$('#note').val(data.SysRole.note);
	doLoadZTreeData(data.SysRoleMenuIds);
}
//点击确定按钮提交表单信息
function doSaveOrUpdate(){
 if($('#editRoleForm').valid()){
  var params = getEditFormData();
  if(params=='nochoose'){
	alert('请选择授权！');
	return false;
  }
  var roleId = $('.content').data('roleId');
  var url = roleId?'role/doUpdateObject.do':'role/doSaveObject.do';
  params.id = roleId;
  $.post(url,params,function(result){
	if(result.state ==SUCCESS){
	 alert('操作成功！');
	 doBack();
	}else{alert(result.message);}
   })
  }
}

//获取参数
function getEditFormData(){
	var roleName = $('#roleName').val();
	var note = $('#note').val();
	//获取选择的授权
	var nodes = ztree.getCheckedNodes(true);
	if(nodes.length==0){
	 return 'nochoose';
	}
	var menuIdList = new Array();
	for(var i=0; i<nodes.length; i++) {
	menuIdList.push(nodes[i].id);
	}
	menuIdList = menuIdList.toString();
	var params = {
	'name':roleName,
	'note':note,
	'menuIdList':menuIdList
	}
	return params;
}

/*点击回退或修改结束执行此方法*/
function doBack(){
	//清空编辑页面数据,解除数据绑定?
    doClearData();
	//加载列表页面,重新显示查询结果
	var listUrl="role/listUI.do?t="+Math.random(1000);
	$(".content").load(listUrl);
}
//清除数据
function doClearData(){
	$('#roleName').val();
	$('#note').val();
	$('.content').removeData('roleId');
}

//加载菜单树
function doLoadZTreeData(menuIdList){
	var url = 'role/doFindZtreeNodes.do';
	$.getJSON(url,function(result){
	   if(result.state==1){
			ztree = $.fn.zTree.init($("#menuTree"), setting,result.data);
			if(menuIdList){
				//展开所有节点
				ztree.expandAll(true);
				//勾选角色所拥有的菜单
				var menuIds = menuIdList;
				for(var i=0; i<menuIds.length; i++) {
					var node = ztree.getNodeByParam("id",menuIds[i]);
					ztree.checkNode(node, true, false);
				}
			}
		}else{
			alert(result.message);
		}
	})
}
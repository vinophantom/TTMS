$(document).ready(function(){
	//点击返回按钮
	$('#btn-return').click(doBack);
	//点击保存/修改按钮
	$('#btn-ok').click(doSaveOrUpdate);
	var userId = $('.content').data('userId');
	//根据id查询用户信息
	if(userId){
	doFindObjectById(userId);
	}else{
	doLoadRoles();
	}
});
//点击保存/修改按钮
function doSaveOrUpdate(){
	if($('#editUserForm').valid()){   //用valid（），校验的文本框必须有name属性值
		var params = getEditFormData();
		if(params=='nochoose'){
			alert('请选择用户角色！');
			return false;
		}
		var userId = $('.content').data('userId');
		var userPwd = userId?$('#newPwd').val():$('#userPwd').val();
		params.password = userPwd;
		params.id = userId;
		var url = userId?'user/doUpdateObject.do':'user/doSaveObject.do';
		$.post(url,params,function(result){
			if(result.state==1){
				alert('操作成功！');
			    doBack();
			}else{
				alert(result.message);
			}
		})
	}
}
//获取表单参数
function getEditFormData(){
	var userName = $('#userName').val();
	var email = $('#email').val();
	var mobile = $('#mobile').val();
	var roleIds = new Array();
	$('input[name="checkedItem"]').each(function(){
		if($(this).is(':checked')){
			roleIds.push($(this).val());
		}
	})
	if(roleIds.length==0){
		return 'nochoose';
	}
	roleIds = roleIds.toString();
	var params = {
		'username':userName,
		'email':email,
		'mobile':mobile,
		'roleIds':roleIds
	}
	return params;
}
//点击返回按钮
function doBack(){
	doClearData();
	$('.content').load('user/listUI.do');
}
//查询所有角色  -- 如果使修改，有roleIdList
function doLoadRoles(roleIdList){
	var url ='user/doFindRoles.do';
	$.getJSON(url,function(result){
		if(result.state==1){
			doSetRoleList(result.data);
			if(roleIdList){
				for(var i in roleIdList){
					$('#roleList input[name="checkedItem"]').each(function(){
						if($(this).val()==roleIdList[i]){
							$(this).prop('checked',true);
						}
					})
				}
			}
		}else{
			alert(result.message);
		}
	})
}
//加载填充角色列表
function doSetRoleList(roleList){
	var parentEle = $('#roleList');
	for(var i in roleList){
		var temp = '<input type="checkbox" name="checkedItem" value="[id]"/><span style="padding-right:30px;">[name]</span>';
		temp = temp.replace('[id]',roleList[i].id).replace('[name]',roleList[i].name);
		parentEle.append(temp);
	}
}
function doFindObjectById(userId){
	var params  = {'userId':userId};
	var url = 'user/doFindObjectById.do';
	$.post(url,params,function(result){
		if(result.state==1){
		 // console.log(JSON.stringify(result.data));
		  doSetEditFormData(result.data.user,result.data.roleIds);  
		}else{
		  alert(result.message);
		}
	});
}
function doSetEditFormData(user,roleIds){
	$('#userName').val(user.username);
	$('#userPwd').val(user.password);
	$('#newPwdDiv').css('display','block');
	$('#email').val(user.email);
	$('#mobile').val(user.mobile);
	doLoadRoles(roleIds);
}
//点击返回，保存，修改按钮，清除editForm数据
function doClearData(){
	$('#editUserForm .dynamicClear').val('');
	$('#newPwdDiv').css('display','none');
	$('#roleList').empty();
	$('.content').removeData('userId');
}
function doGetUserMenus(){
	//到controller请求菜单
	var url = 'doFindUserMenus.do';
	$.getJSON(url,function(result){
		if(result.state==1){
		  doSetMenus(result.data);
		}else{
		  alert(result.message);
		}
	});
}

function doSetMenus(list){
	//加载一级菜单
	var firstLevelMenus = $('#menu-nav');
	for(var i in list){
		if(list[i].parentId==null||list[i].parentId==''){
			var template = '<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"'+
			' role="button" aria-haspopup="true" aria-expanded="false">'+list[i].name+'<span class='+
			'"caret"></span></a><ul class="dropdown-menu"  id="menu'+list[i].id+'"></ul></li>';
			firstLevelMenus.append(template);
		}
	}
	//加载二级菜单
	for(var i in list){
		if(list[i].parentId!=null && list[i].parentId!=''){
			var secondLevelMenu = $('#menu'+list[i].parentId);
			var li = $('<li></li>');
			li.data('url',list[i].url);
			var temp = '<a  class="menuBtn">'+list[i].name+'</a>';
			temp = li.append(temp);
			var separetor = '<li role="separator" class="divider"></li>';
			secondLevelMenu.append(temp).append(separetor);
		}
	}
}
function doLoadUrl(){
	var url = $(this).parent().data('url');
	url = url+'?t=;'+Math.random(1000);
	$("#container").load(url);
}

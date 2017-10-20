/**
 * 初始化表格的列
 */
var colunms = [
{
	field : 'selectItem',
	radio : true
},
{
	title : '菜单ID',
	field : 'id',
	visible : false,
	align : 'center',
	valign : 'middle',
	width : '80px'
},
{
	title : '菜单名称',
	field : 'name',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '180px'
},
{
	title : '上级菜单',
	field : 'parentName',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '180px'
},
{
	title : '类型',
	field : 'type',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '100px',
	formatter : function(item, index) {
		if (item.type == 1) {
			return '<span class="label label-success">菜单</span>';
		}
		if (item.type == 2) {
			return '<span class="label label-warning">按钮</span>';
		}
	}
}, {
	title : '排序号',
	field : 'sort',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '100px'
}, {
	title : '菜单URL',
	field : 'url',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '160px'
}, {
	title : '授权标识',
	field : 'permission',
	align : 'center',
	valign : 'middle',
	sortable : true
} ];


$(document).ready(function(){
	//编辑
	$('#formHead').on('click','.btn-add,.btn-update',doLoadEditPage)
	//删除
	$('#formHead').on('click','.btn-delete',doDeleteObject)
	doGetObjects();
})

function doGetObjects() {
	var tableId="menuTable";
	var table = new TreeTable(tableId,"menu/doFindObjects.do", colunms);
	table.setExpandColumn(2);
	table.setIdField("id");
	table.setCodeField("id");
	table.setParentCodeField("parentId");
	table.setExpandAll(false);
	table.init();
}
/**加载编辑页面到制定位置*/
  function doLoadEditPage(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加菜单信息";
	}
	if($(this).hasClass("btn-update")){
		title="修改菜单信息"
	    var id=getSelectedId();//获得选中的记录id值
		if(id==-1){
		  alert("请先选择");return;
		}
		$('.content').data('menuId',id);
	}
	var url="menu/editUI.do"
	$(".content").load(url,function(){
		$("#titleId").html(title)
	})
  }

  /*获得选中的id值*/
  function getSelectedId(){
	//1.1 获得选中的对象,默认返回值为一个对象数组
	var selections=$("#menuTable")
	.bootstrapTreeTable("getSelections");
	if(selections.length==0){
	 return -1;//表示没选择任何对象
	}
	//1.2获得选中数组中下标为0的元素id的值
	return selections[0].id;
  }
  //删除菜单项
  function doDeleteObject(){
	var menuId=getSelectedId();
	if(menuId==-1){
		alert("请先选择");
		return;
	}
	var params = {'menuId':menuId};
	var url = 'menu/doDeleteObject.do';
	$.post(url,params,function(result){
		if(result.state==1){
			alert('删除成功！');
			doGetObjects();
		}else{
			alert(result.message);
		}
	})
}


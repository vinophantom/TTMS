var columns = [
{
field : 'selectItem',
radio : true
},
{
title : '分类id',
field : 'id',
visible : false,
align : 'center',
valign : 'middle',
width : '80px'
},
{
title : '分类名称',
field : 'name',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '上级分类',
field : 'parentName',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '排序号',
field : 'sort',
align : 'center',
valign : 'middle',
sortable : true,
width : '100px'
}];
$(document).ready(function(){
	$("#formHead")
	.on("click",".btn-delete",doDeleteObject)
	.on("click",".btn-add,.btn-update",doLoadEditPage);
	doGetObjects();
});
/**加载编辑页面到制定位置*/
function doLoadEditPage(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加分类信息";
	}
	if($(this).hasClass("btn-update")){
		title="修改分类信息"
	    var id=getSelectedId();//获得选中的记录id值
		if(id==-1){
		  alert("请先选择");return;
		}
		$("#container").data("id",id);
		console.log("id="+id);
	}
	var url="type/editUI.do"
	$(".content").load(url,function(){
		$(".panel-heading").html(title)
	})
}
/**获得选中的id值*/
function getSelectedId(){
	//1.1 获得选中的对象,默认返回值为一个对象数组
	var selections=$("#typeTable")
	.bootstrapTreeTable("getSelections");
	if(selections.length==0){
	 return -1;//表示没选择任何对象
	}
	//1.2获得选中数组中下标为0的元素id的值
	return selections[0].id;
}
/**执行删除操作*/
function doDeleteObject(){
	//debugger
	//1.获得选中的id
	var typeId=getSelectedId();
	if(typeId==-1){
		alert("请先选择");
		return;
	}
	console.log("typeId="+typeId);
	//2.发送异步请求,根据id执行删除操作
	//2.1定义url
	var url="type/doDeleteObject.do";
	//2.2定义参数值("id"要与controller方法中参数的名字相同)
	var params={"id":typeId};
	//2.3执行异步删除操作
	$.post(url,params,function(result){
		if(result.state==1){
			doGetObjects();
			alert("删除ok");
		}else{
			alert(result.message);
		}
	});
}
function doGetObjects(){
 var tableId="typeTable";//对象type_list.jsp中的table id
 var url="type/doFindGridTreeObjects.do";
 var table=new TreeTable(tableId,url,columns);
 table.setIdField("id");//设置选中记录的返回id()
 table.setCodeField("id");//设置级联关系的id
 table.setParentCodeField("parentId");//设置级联关系中的parentId
 table.setExpandColumn(2);//设置默认展开列
 table.setExpandAll(false);//设置默认不展开
 table.init();//初始化对象树(底层会发起异步请求)
}



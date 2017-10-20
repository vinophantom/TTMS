var zTree;
var setting = {
		data : {   
			simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
}
$(document).ready(function(){
	$("#editTypeForm").on("click",".load-product-type",doLoadZTreeNodes)
    $("#btn-save").click(doSaveOrUpdate)
    //点击返回按钮执行doBack函数
    $("#btn-return").click(doBack);
    $("#typeLayer")
    .on("click",".btn-cancle",doHideZtree)
    .on("click",".btn-confirm",doSetSelectedNode);
    
	//获得id值(等修改或返回以后一定要将id的值移除)
	var id=$("#container").data("id");
	//假如id有值说明是修改,则根据id执行查找
	if(id)doFindObjectById(id);
});
/*点击回退或修改结束执行此方法*/
function doBack(){
	//清空编辑页面数据,解除数据绑定?
    doClearData();
	//加载列表页面,重新显示查询结果
	var listUrl="type/listUI.do?t="+Math.random(1000);
	$("#container").load(listUrl);
}
/**根据id执行查询获得记录信息*/
function doFindObjectById(id){
	var url="type/doFindObjectById.do";
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
		doSetEditFormData(result.data);//初始化表单数据
		}else{
		alert(result.message);
		}
	});
}
/*修改时初始化表单数据*/
function doSetEditFormData(obj){
	$("#typeNameId").val(obj.name);
	$("#parentNameId").val(obj.parentName);
	$("#editTypeForm").data("parentId",obj.parentId);
	$("#typeSortId").val(obj.sort);
	$("#typeNoteId").html(obj.note);
}
function doSaveOrUpdate(){
	//1.获得页面表单中的数据
	//1.1获得用户输入的数据
	var params=getEditFormData();
	//1.2获得页面绑定的id值(假如有值说明是修改)
	var id=$("#container").data("id");
	if(id)params.id=id;//在json对象中添加一个新的key/value
	//2.发送异步请求提交数据
	var saveUrl="type/doSaveObject.do";
	var updateUrl="type/doUpdateObject.do";
	var url=id?updateUrl:saveUrl;
	console.log(JSON.stringify(params));
	$.post(url,params,function(result){
		if(result.state==1){
		doBack();
		}else{
		alert(result.message);
		}
	});
}
/*清空相关数据*/
function doClearData(){
   //1清空所有类选择器dynamicClear标识的对象的内容
   $(".dynamicClear").val('');//技巧应用
   //2.移除绑定的数据(因为添加和修改要共用一个页面)
   $("#container").removeData("id");
   $("#editTypeForm").removeData("parentId");
}
function getEditFormData(){
	var params={
	  "name":$("#typeNameId").val(),
	  "parentId":$("#editTypeForm").data("parentId"),
	  "sort":$("#typeSortId").val(),
	  "note":$("#typeNoteId").val()
	};
	return params;
}
/*设置选中的节点*/
function doSetSelectedNode(){
	//1.获得选中的的节点对象
	var selectedNodes=//返回值是一个数组
	//getSelectedNodes是zTree中的一个函数
    zTree.getSelectedNodes();
	//2.获得具体的节点(node)对象
	var node=selectedNodes[0];
	//3.通过node节点数据更新页面内容
    $("#parentNameId").val(node.name);
    $("#editTypeForm").data("parentId",node.id);
    //4.隐藏zTree
    doHideZtree();
}
/**隐藏Ztree*/
function doHideZtree(){
    $("#typeLayer").css("display","none");
}
/**显示Ztree以及树上的节点信息*/
function doLoadZTreeNodes(){
//1.显示Ztree(在type_edit.jsp页面上默认是隐藏的)
 $("#typeLayer").css("display","block");
//2.发送异步请求加载分类信息,更新Ztree节点内容
 var url="type/doFindZtreeObjects.do"
 $.getJSON(url,function(result){
	 console.log("result="+JSON.stringify(result))
	 if(result.state==1){
		 //访问zTree方法通过数据初始化节点信息
		 zTree=$.fn.zTree.init($("#typeTree"),setting,result.data);
	 }else{
		alert(result.message);
	 }
 });
 
 
}
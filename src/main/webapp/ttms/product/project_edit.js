//$(function(){})
$(document).ready(function(){
//1.在模态框的保存按钮上注册事件,
	//事件执行时调用doSaveOrUpdate
	$("#modal-dialog").on("click",".ok",doSaveOrUpdate);
	//模态框隐藏时移除相关数据
	$("#modal-dialog")//获得模态框对象
	//"hidden.bs.modal"代表模态框的隐藏事件
	.on("hidden.bs.modal",function(){
		//当模态框隐藏以后移除.ok对象的click事件
		//假如没有执行此操作,可能会出现表单重复提交的问题
		$(this).off("click",".ok")
		       .removeData("id");
		//模态框隐藏以后移除绑定的数据(也可以参考上面的写法)
		//$(this).removeData("id");
	});
	//获得模态框上绑定的数据:id
	var id=$("#modal-dialog").data("id");
	//假如id有值,则根据此值进行查询.
	if(id)doFindObjectById(id);
});
/*根据id执行查找操作*/
function doFindObjectById(id){
	var url="project/doFindObjectById.do"
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
		 doInitEditFormData(result.data);	
		}else{
		 alert(result.message);
		}
	});
}
function doInitEditFormData(obj){
	$("#nameId").val(obj.name);
	$("#codeId").val(obj.code);
	$("#beginDateId").val(obj.beginDate);
	$("#endDateId").val(obj.endDate);
	$("#noteId").html(obj.note);
	$("#editFormId input[name='valid']")//radio
	//迭代input标签中name为valid的dom对象
	.each(function(){
		//假如这个对象的值等于obj.valid的值
		//则让其选中.
		if($(this).val()==obj.valid){
		   //设置radio对象的checked属性为true
		   $(this).prop("checked",true)
		}
	});
}


/* 点击模态框上的save按钮时执行此函数,
 * 通过此函数发送异步请求将页面上的数据
 * 发送到服务端.
 */
function doSaveOrUpdate(){
	//1.简单验证表单数据,当valid()返回值为false,
	//表示验证没有通过
	//1.1使用valid函数首先要引入验证框架
	//jquery.validate.min.js
	//1.2在不能为空的html对象上添加required选择器
	if(!$("#editFormId").valid())return;
	//2.获得输入的数据
	var params=getEditFormData();
	//3.将数据异步发送到服务端
	//3.1定义url(对应controller中的一个方法)
	//获得模态框上绑定的id值
	var id=$("#modal-dialog").data("id");
	if(id)params.id=id;//动态添加属性(修改时需要id的值)
	//根据id是否有值来判定是修改还是添加
	var updateUrl="project/doUpdateObject.do";
	var insertUrl="project/doSaveObject.do";
	var url=id?updateUrl:insertUrl;
	//3.2发送异步请求	
	$.post(url,params,function(result){
		console.log(JSON.stringify(result));
		if(result.state==1){
		 //隐藏模态框
		 $("#modal-dialog").modal("hide");
		 //重新查询(调用的project_list.js中的doGetObjects)
		 doGetObjects();
		}else if(result.state==2){
		 alert(result.message);
		}
	});//doSaveObject(Project entity)
}
function getEditFormData(){
 //1.获得页面上用户输入的数据,封装为json对象(相对比较灵活)
  var params={//根据id获得数据
	  "name":$("#nameId").val(),
	  "code":$("#codeId").val(),
	  "beginDate":$("#beginDateId").val(),
	  "endDate":$("#endDateId").val(),
	  "valid":$('input[name="valid"]:checked').val(),
	  "note":$("#noteId").val()
  }
  console.log(JSON.stringify(params));
  //2.返回json对象
  return params;
}





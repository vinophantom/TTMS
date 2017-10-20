package cn.tedu.ttms.product.controller;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
@RequestMapping("/project/")
@Controller
public class ProjectController {
	@Resource
    private ProjectService projectService;
	
	@RequestMapping("listUI")
	public String listUI(){
		return "product/project_list";//WEB-INF/pages/product/project_list.jsp
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "product/project_edit";//WEB-INF/pages/product/project_edit.jsp
	}
	
    //http://localhost:8080/ttms1.0/project/doFindObjects.do
	@RequestMapping("doFindObjects")
	@ResponseBody //用于将返回的对象转换为json串
	public JsonResult doFindObjects(
			String name,
			Integer valid,
			int pageCurrent){
		Map<String,Object> map=
		projectService.findObjects(
				name,valid,pageCurrent);
		//将获得的数据封装到JsonResult对象
		return new JsonResult(map);
	}//JSON string

	/**
	 * var params={
	 * "checkedIds":checkedId,
	 * "valid":valid}
	 * 
	 * */
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(
			String checkedIds,
			Integer valid){
		System.out.println(
				"checkedIds="+checkedIds);
		projectService.validById(
				checkedIds,
				  valid);
		return new JsonResult();
		//this.message="ok"
		//this.state=SUCCESS
	}
	/**执行添加操作
	 * var params={"name":"A","code":"tt20170807xxxx",..}
	 * @param entity 对象会封装页面上传入的参数值
	 * 页面上参数的名字和entity对象中属性的值
	 * 一致时会实现自动注入操作.
	 * */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			Project entity){
		projectService.saveObject(entity);
		return new JsonResult();
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer id){
		Project project=
		projectService.findObjectById(id);
		return new JsonResult(project);
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
			Project entity,
			HttpServletRequest request){
		//假设有用户登录,可以从session中获得用户信息
		/*User user=(User)
		request.getSession()
		.getAttribute("user");
		entity.setModifiedUser(user.getUsername());
		*/
		projectService.updateObject(entity);
		return new JsonResult();
	}
	
}

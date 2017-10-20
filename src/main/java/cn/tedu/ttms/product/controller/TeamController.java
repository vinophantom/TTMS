package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.TeamService;

@Controller
@RequestMapping("/team/")
public class TeamController {
	@Resource
	private TeamService teamService;
	@RequestMapping("listUI")
	public String listUI(){
		return "product/team_list";
	}
	/**当在页面上点击添加或修改按钮时
	 * 访问此方法,通过此方法返回一个
	 * 页面*/
	@RequestMapping("editUI")
	public String editUI(){
		return "product/team_edit";
	}
	/**执行修改操作 @param entity 用于封装页面团信息数据*/
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Team entity){
		//1.修改团信息
		teamService.updateObject(entity);
		//2.返回响应信息
		return new JsonResult();
	}
	/**根据id执行查找操作
	 * @param id 是页面上传过来的记录的id值*/
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer id){
		Team team=
		teamService.findObjectById(id);
		return new JsonResult(team);
	}
	/**执行保存操作*/
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Team entity){
		teamService.saveObject(entity);
		return new JsonResult();
	}
	
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(
			Integer valid,
			Integer projectId,
			Integer pageCurrent){
		    Map<String,Object> map=
			teamService.findObjects(valid,
				projectId,
				pageCurrent);
		return new JsonResult(map);
	}
	/**查询所有启用项目的id和名字*/
	@RequestMapping("doFindPrjIdAndNames")
	@ResponseBody
	public JsonResult doFindPrjIdAndNames(){
		List<Map<String,Object>>
		list=teamService.findPrjIdAndNames();
		return new JsonResult(list);
	}
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(
			String checkedIds,
			Integer valid){
		 //1.更新状态
	     teamService.validById(checkedIds,
	    		 valid);
	     //2.返回状态信息
	     return new JsonResult();
	}
	
}

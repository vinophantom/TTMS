package cn.tedu.ttms.system.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.system.entity.SysUser;
import cn.tedu.ttms.system.service.SysUserService;

@Controller
@RequestMapping("user")
public class SysUserController {
	@Resource
	private SysUserService userService;
	/**
	 * 用户列表页面
	 */
	@RequestMapping("listUI")
	//@RequiresPermissions("sys:user:view")
	public String listUI(){
		return "system/user_list";
	}
	/**
	 * 查询用户列表
	 */
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(String username,Integer pageCurrent){
		Map<String, Object> map =
		userService.findPageObjects(username, pageCurrent);
		return new JsonResult(map);
	}
	
	/**
	 * 跳转到用户编辑页面（新增/修改）
	 */
	@RequestMapping("editUI")
	public String editUser(){
		return "system/user_edit";
	}
	/**
	 * 查询所有角色列表
	 */
	@RequestMapping("doFindRoles")
	@ResponseBody
	public JsonResult doFindRoles(){
		List<Map<String, Object>> list = 
		userService.findSysRoles();
		return new JsonResult(list);
	}
	
	/**
	 * 保存用户
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysUser user,String roleIds){
		userService.saveObject(user, roleIds);
		return new JsonResult();
	}
	/**
	 * 根据id查询用户信息，用于回显
	 */
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer userId){
		Map<String, Object> map = 
		userService.findUserById(userId);
		//System.out.println("map="+map);
		return new JsonResult(map);
	}
	/**
	 * 修改更新用户信息
	 */
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysUser user,String roleIds){
		userService.updateObject(user,roleIds);
		return new JsonResult();
	}
	/**
	 * 启用/禁用
	 */
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer userId,Integer valid){
		userService.validById(userId,valid);
		return new JsonResult();
	}

}

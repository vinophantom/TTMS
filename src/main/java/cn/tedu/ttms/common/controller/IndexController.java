package cn.tedu.ttms.common.controller;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.system.entity.SysUser;
import cn.tedu.ttms.system.service.SysUserService;

@Controller
public class IndexController {
	@Autowired
	private SysUserService userService;
	@RequestMapping("/indexUI.do")
	public String indexUI(){
		System.out.println("indexUI");
		//返回的字符串会对应一个/WEB-INF/pages/index.jsp页面
		return "index";
	}
	@RequestMapping("doFindUserMenus.do")
	@ResponseBody
	public JsonResult doFindUserMenus() {
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		return new JsonResult(userService.findUserMenus(user.getId()));
	}
}

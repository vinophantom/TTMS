package cn.tedu.ttms.system.controller;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.system.entity.SysMenu;
import cn.tedu.ttms.system.service.SysMenuService;

@Controller
@RequestMapping("/menu/")
public class SysMenuController {
	
	@Resource
	private SysMenuService menuService;
	
	@RequestMapping("listUI")
	//@RequiresPermissions("sys:menu:view")
	public String listUI(){
		return "system/menu_list";
	}
	
	/**
	 * 查找所有菜单
	 * @param entity
	 * @return
	 */
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		List<Map<String, Object>> list =
				menuService.findObjects();
		return new JsonResult(list);
	}
	
	/**
	 * 新增
	 */
	@RequestMapping("editUI")
	public String editUI(){
		return "system/menu_edit";
	}
	
	/**
	 * 加载选择菜单树结构
	 */
	@RequestMapping("treeUI")
	@ResponseBody
	public JsonResult treeUI(){
		List<Map<String, Object>> list =
		menuService.findZtreeNodes();
		return new JsonResult(list);
	}
	
	/**
	 * 添加菜单
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult save(SysMenu entity){
		menuService.saveObject(entity);
		return new JsonResult();
	}
	
	/**
	 * 根据id查询菜单信息 
	 */
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer menuId){
		Map<String, Object> map =
		menuService.findMapById(menuId);
		return new JsonResult(map);
	}
	
	/**updateMenu*/
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysMenu entity){
		menuService.updateObject(entity);
		return new JsonResult();
	}
	
	/** 判断要删除的菜单是否有子菜单*/
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer menuId){
		menuService.deleteObject(menuId);
		return new JsonResult();
	}

}

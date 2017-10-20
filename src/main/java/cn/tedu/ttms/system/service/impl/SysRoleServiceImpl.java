package cn.tedu.ttms.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.system.dao.SysMenuDao;
import cn.tedu.ttms.system.dao.SysRoleDao;
import cn.tedu.ttms.system.dao.SysRoleMenuDao;
import cn.tedu.ttms.system.dao.SysUserRoleDao;
import cn.tedu.ttms.system.entity.SysRole;
import cn.tedu.ttms.system.service.SysRoleService;

@Service("roleService")
@Transactional
public class SysRoleServiceImpl implements SysRoleService {
	@Resource
	private SysRoleDao roleDao;

	@Resource
	private SysMenuDao menuDao;

	@Resource
	private SysRoleMenuDao roleMenuDao;

	@Resource
	private SysUserRoleDao userRoleDao;

	public Map<String, Object> findObjects(String name, Integer pageCurrent) {
		System.out.println("name=" + name);
		System.out.println("pageCurrent=" + pageCurrent);
		Integer pageSize = 3;
		Integer startIndex = (pageCurrent - 1) * pageSize;
		List<SysRole> list = roleDao.findPageObjects(name, startIndex, pageSize);

		PageObject pageObject = new PageObject();
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(roleDao.getRowCounts(name));
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("list.size=" + list.size());
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}

	/** 获取菜单树 */
	public List<Map<String, Object>> findZtreeNodes() {
		return menuDao.findZtreeNodes();
	}

	public void saveObject(SysRole role, String menuIdList) {
		if (role == null)
			throw new NullPointerException("添加角色，角色对象不能为空!");
		// 保存角色信息
		int i = roleDao.insertObject(role);
		if (i != 1)
			throw new RuntimeException("添加角色失败！");
		// 保存角色菜单关系
		String menuIds[] = menuIdList.split(",");
		/*
		 * Map<String, Object> map = new HashMap<String,Object>();
		 * map.put("roleId", role.getId()); map.put("menuIds", menuIds);
		 */
		int counts = roleMenuDao.insertRoleMenus(role.getId(), menuIds);
		if (counts != menuIds.length) {
			throw new RuntimeException("添加角色授权失败！");
		}
	}

	public Map<String, Object> findMapById(Integer roleId) {
		if (roleId == null)
			throw new ServiceException("角色id不能为空！");

		SysRole sysRole = roleDao.findObjectById(roleId);
		if (sysRole == null)
			throw new ServiceException("角色信息查询失败！");

		List<Integer> roleMenuIds = roleMenuDao.findMenuIdsByRoleId(roleId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("SysRole", sysRole);
		map.put("SysRoleMenuIds", roleMenuIds);
		return map;
	}

	public void updateRole(SysRole role, String menuIdList) {
		if (role == null)
			throw new ServiceException("角色对象不能为空！");

		int i = roleDao.updateObject(role); // 更新角色名称和备注
		if (i != 1)
			throw new ServiceException("更新角色信息失败！");
		// 更新角色菜单关系 - 先删除后添加
		int counts = roleMenuDao.deleteObject(role.getId());
		System.out.println("counts=" + counts);
		if (counts < 1) {
			throw new ServiceException("修改更新角色信息失败！");
		}
		String[] menuIds = menuIdList.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", role.getId());
		map.put("menuIds", menuIds);
		int cous = roleMenuDao.insertRoleMenus(role.getId(), menuIds);
		if (cous != menuIds.length)
			throw new ServiceException("更新角色授权失败！");
	}

	/**
	 * 删除角色，删除前先判断是否有用户占用此角色，如果有，那么此角色不能删除 如果可以删除，删除角色后，角色和菜单的关系也要删除
	 */
	public void deleteObject(Integer roleId) {
		if (roleId == null)
			throw new NullPointerException("删除角色信息，角色id不能为空！");
		// 查看是否有用户占用此角色
		int counts = userRoleDao.isUsedByUser(roleId);
		if (counts != 0)
			throw new RuntimeException("该角色已被用户占用，不能删除！");
		// 删除角色信息
		int i = roleDao.deleteObject(roleId);
		if (i != 1)
			throw new RuntimeException("删除角色信息失败！");
		// 删除角色菜单关系
		int rows = roleMenuDao.deleteObject(roleId);
		if (rows == 0)
			throw new RuntimeException("删除角色菜单关系失败！");
	}
}

package cn.tedu.ttms.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.system.dao.SysMenuDao;
import cn.tedu.ttms.system.entity.SysMenu;
import cn.tedu.ttms.system.service.SysMenuService;

@Service("menuService")
@Transactional
public class SysMenuServiceImpl implements SysMenuService{
	@Resource
	private SysMenuDao menuDao;
	public List<Map<String, Object>> findObjects() {
		return menuDao.findObjects();
	}
	public List<Map<String, Object>> findZtreeNodes() {
		return menuDao.findZtreeNodes();
	}
	public void saveObject(SysMenu entity) {
		if(entity==null)
		throw new ServiceException("添加菜单对象不能为空！");
		int i = menuDao.insertObject(entity);
		if(i!=1)
		throw new ServiceException("添加菜单失败！");
	}
	public Map<String, Object> findMapById(Integer menuId) {
		if(menuId==null)
		throw new ServiceException("菜单id能为空！");
		Map<String, Object> map = menuDao.findMapById(menuId);
		if(map==null || map.size()==0)
		throw new ServiceException("修改菜单信息过程中获取菜单信息失败！");
		
		return map;
	}

	public void updateObject(SysMenu entity) {
		if(entity==null)
		throw new ServiceException("修改菜单信息，菜单对象不能为空！");
		int i = menuDao.updateObject(entity);
		if(i!=1)
		throw new ServiceException("更新菜单信息失败！");
	}
	public void deleteObject(Integer menuId) {
		if(menuId==null)
		throw new ServiceException("删除菜单，菜单id不能为空！");
		int count = menuDao.hasChild(menuId);
		if(count!=0)
		throw new ServiceException("请先删除子菜单或按钮！");
		int i = menuDao.deleteObject(menuId);
		if(i!=1)throw new ServiceException("删除菜单失败！");
	}
}

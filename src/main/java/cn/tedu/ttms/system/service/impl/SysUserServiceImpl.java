package cn.tedu.ttms.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.system.dao.SysRoleDao;
import cn.tedu.ttms.system.dao.SysUserDao;
import cn.tedu.ttms.system.dao.SysUserRoleDao;
import cn.tedu.ttms.system.entity.SysUser;
import cn.tedu.ttms.system.service.SysUserService;

@Service("userService")
@Transactional
public class SysUserServiceImpl implements SysUserService {
	@Resource
	private SysUserDao userDao;
	@Resource
	private SysRoleDao roleDao;
	@Resource
	private SysUserRoleDao userRoleDao;
	public Map<String, Object> findPageObjects(String username, 
			Integer pageCurrent) {
		PageObject pageObject=new PageObject();
		pageObject.setRowCount(userDao.getRowCount(username));	
	    pageObject.setPageCurrent(pageCurrent);
	    int pageSize=2;
	    int startIndex=(pageCurrent-1)*pageSize;
	    pageObject.setPageSize(2);
	    pageObject.setStartIndex(startIndex);
	    
		List<SysUser> list = 
		userDao.findPageObjects(username,startIndex,pageSize);
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageObject",pageObject);
		return map;
	}
	public List<Map<String, Object>> findSysRoles() {
		List<Map<String, Object>> list = roleDao.findObjects();
		if(list==null || list.size()==0){
			throw new ServiceException("获取角色列表失败！");
		}
		return list;
	}

	/** 保存用户信息，先保存用户，再保存用户角色关系*/
	public void saveObject(SysUser user,String roleIds) {
		if(user==null){
			throw new ServiceException("保存用户信息，用户对象不能为空！");
		}
		String saltStr = UUID.randomUUID().toString();
		ByteSource salt = ByteSource.Util.bytes(saltStr);
		String pwd = new SimpleHash("MD5",user.getPassword(),salt).toString();
		user.setPassword(pwd);
		user.setSalt(saltStr);
		//保存用户信息
		int i = userDao.insertObject(user);
		if(i==-1)
		throw new ServiceException("保存用户信息失败！");
		//保存用户角色信息
		String[] roleIdArray=roleIds.split(",");
		int counts = userRoleDao.insertObject(user.getId(),roleIdArray);
		if(counts!=roleIdArray.length)
		throw new ServiceException("保存用户角色失败！");
	
	}
	/** 根据id查询用户信息*/
	public Map<String,Object> findUserById(Integer userId) {
		if(userId==null)
		throw new ServiceException("用户id不能为空！");
		SysUser user = userDao.findObjectById(userId);
		if(user==null)
		throw new ServiceException("查询用户信息失败！");
		List<Integer> roleIds = 
		userRoleDao.findRoleIdsByUserId(userId);
		if(roleIds==null||roleIds.size()==0)
		throw new ServiceException("查询用户角色信息失败！");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("roleIds",roleIds);
		map.put("user", user);
		return map;
	}
	public void updateObject(SysUser user,String roleIds) {
		if(user==null)
		throw new ServiceException("用户对象不能为空！");
		String saltStr = UUID.randomUUID().toString();   //生成盐值
		ByteSource salt = ByteSource.Util.bytes(saltStr);
		String pwd = new SimpleHash("MD5",user.getPassword(),salt).toString();
		user.setPassword(pwd);
		user.setSalt(saltStr);
		//更新用户信息
		int i = userDao.updateObject(user);
		if(i!=1)
		throw new ServiceException("修改更新用户信息失败！");
		//更新用户角色信息  - 先删除二者关系，再添加二者关系
		String[] roleArrayIds=roleIds.split(",");
		int counts =userRoleDao.deleteUserRoles(user.getId());
		if(counts<1)
		throw new RuntimeException("更新用户角色信息失败！");
		int rows = userRoleDao.insertObject(user.getId(),roleArrayIds);
		if(rows!=roleArrayIds.length)
		throw new ServiceException("更新用户角色失败！");
	}
	public void validById(Integer userId, Integer valid) {
		if(userId==null)
		throw new ServiceException("修改用户状态，用户id不能为空！");
		if(valid==null)
		throw new ServiceException("修改用户状态，valid值不能为空！");
		int i = userDao.validById(userId,valid);
		if(i==-1)
		throw new ServiceException("切换用户启用禁用状态失败！");
	}
	/**查询用户得所有权限*/
	public List<String> findUserPermissions(Integer userId) {
		if(userId==null)
		throw new ServiceException("用户id不能为空！");
		return userDao.findUserPermissions(userId);
	}
	/**查询用户菜单*/
	public List<Map<String, Object>> findUserMenus(Integer userId) {
		if(userId==null)
		throw new ServiceException("用户ID不能为空");
		return userDao.findUserMenus(userId);
	}
}

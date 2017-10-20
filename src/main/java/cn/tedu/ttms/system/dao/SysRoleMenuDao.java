package cn.tedu.ttms.system.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuDao{
	int insertRoleMenus(@Param("roleId")Integer roleId,@Param("menuIds")String[] menuIds);
	int deleteObject(Integer id);
	List<Integer> findMenuIdsByRoleId(Integer id);
	
}

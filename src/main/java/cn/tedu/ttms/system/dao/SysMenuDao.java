package cn.tedu.ttms.system.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.system.entity.SysMenu;


public interface SysMenuDao extends BaseDao<SysMenu> {

	List<Map<String,Object>> findObjects();
	int getRowCount();
	List<Map<String, Object>> findZtreeNodes();
	int hasChild(Integer menuId);
	Map<String,Object> findMapById(Integer id);
	int deleteObject(Integer id);

}

package cn.tedu.ttms.product.service;

import java.util.Map;

import cn.tedu.ttms.product.entity.Project;

/**
 * 项目管理模块的业务层对象:
 * 负责具体项目信息的业务处理
 */
public interface ProjectService {
	 /**获得当前页项目信息以及分页信息
	  * 1)项目信息封装到List<Project>
	  * 2)分页信息封装到PageObject
	  * 将项目信息和分页信息再次封装,
	  * 封装map,然后做统一返回
	  * */
     Map<String,Object> findObjects(
    		 String name,
    		 Integer valid,
    		 int pageCurrent); 
     /**启用禁用项目信息*/
     void validById(
    		 String idStr,
    		 Integer valid);
     /**向表中写入数据*/
     void saveObject(Project entity);
     /**修改表中数据*/
     void updateObject(Project entity);
     /**根据id查找具体对象*/
     Project findObjectById(Integer id);
}







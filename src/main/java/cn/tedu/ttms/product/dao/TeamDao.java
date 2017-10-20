package cn.tedu.ttms.product.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.product.entity.Team;
/**数据持久层对象(数据访问对象):
 * 此对象对应的mapp文件为TeamMapper*/
public interface TeamDao extends BaseDao<Team>{

	 Team findObjectById(Integer id);
	 
	 /**查询团信息以及团所在的项目的项目
	  * id,项目名称(尝试自己写SQL)
	  * 此方法用于获得当前页的团信息
	  * @param valid 团的禁用和启动状态,
	  * 可能会根据此状态进行条件查询
	  * @param projectId 项目的id,可能会
	  * 根据此id查询项目下相关团的信息
	  * @param startIndex 表示查询当前页面的起始位置
	  * @param pageSize 表示每页显示多少条记录
	  * @return 用于封装查询结果,一般结果
	  * 中的数据假如来自于多张表,可以将每条记录
	  * 封装到一个map对象,然后在将多个map封装到list
	  * */
	 List<Map<String,Object>> findObjects(
			@Param("valid")Integer valid,
	    	@Param("projectId")Integer projectId,
	    	@Param("startIndex")int startIndex,
	        @Param("pageSize") int pageSize);
	 
	 /**获得表中满足条件的记录总数
	  * @param valid 团的禁用和启动状态,
	  * 可能会根据此状态进行条件查询
	  * @param projectId 项目的id,可能会
	  * 根据此id查询项目下相关团的信息
	  * @return 返回查询到记录总数
	  * */
	 int getRowCount(
		    @Param("valid")Integer valid,
		    @Param("projectId")Integer projectId);
	 /**
	  * 此方法用于实现记录的禁用和启用
	  * @param ids 数组中封装的是页面上选择的记录的id
	  * @param valid 封装的是禁用或启用的状态值
	  * */
	 int validById(
			 @Param("ids")String[] ids,
			 @Param("valid")Integer valid);
	 
}

package cn.tedu.ttms.product.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.TeamService;
@Transactional
@Service
public class TeamServiceImpl implements TeamService {

	@Resource
	private TeamDao teamDao;
	@Resource
	private ProjectDao projectDao;
	/**执行团信息的更新操作
	 * @param entity 中用于封装页面传入的数据*/
	public void updateObject(Team entity) {
		if(entity==null)
		throw new ServiceException("修改内容不能为空");
		int rows=teamDao.updateObject(entity);
		if(rows==-1)
		throw new ServiceException("修改失败");
	}
	@Transactional(readOnly=true,
	propagation=Propagation.REQUIRED,
	isolation=Isolation.READ_COMMITTED)
	public Team findObjectById(Integer id) {
		//1.判定参数的有效性
		if(id==null||id<=0)
		throw new ServiceException("id 的值无效:id="+id);
		//2.执行查找操作
		Team team=teamDao.findObjectById(id);
		//3.根据结果进行判定
		if(team==null)
		throw new ServiceException("没找到对应结果");
		//4.返回结果
		return team;
	}
	
	
	/**查询项目id和项目名称,通过此数据
	 * 初始化页面上的select列表*/
	@Transactional(readOnly=true)
	public List<Map<String, Object>> 
	       findPrjIdAndNames() {
		return projectDao.findPrjIdAndNames();
	}
	/**获得当前页的数据以及分页信息
	 * 1)List<Map<String,Object>
	 * 2)PageObject
	 */
	@Transactional(readOnly=true)
	public Map<String,Object> 
	findObjects(Integer valid,
			    Integer projectId, 
			    Integer pageCurrent) {
		//1.判定参数数据的有效性
		if(valid!=null&&valid!=0&&valid!=1)
		throw new ServiceException("valid 的值无效");
		if(projectId!=null&&projectId<=0)
		throw new ServiceException("项目id无效");
		if(pageCurrent==null||pageCurrent<=0)
		throw new ServiceException("当前页码无效");
		//2.根据pageCurrent计算startIndex
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		//3.执行查询操作获得当前页数据.
		List<Map<String,Object>> list=
				teamDao.findObjects(valid,
				projectId, 
				startIndex, 
				pageSize);
		//4.获得记录数,计算分页相关信息并进行封装
		//4.1根据条件获得记录数
		int rowCount=teamDao.getRowCount(valid,
				projectId);
		//4.2将分页信息封装到PageObject
		PageObject pageObject=new PageObject();
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setStartIndex(startIndex);
		//5.封装数据(当前页记录,分页PageObject)
		Map<String,Object> map=
			new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject",pageObject);
		return map;
	}
	/**定义什么异常会回滚事务*/
	@Transactional(rollbackFor=Exception.class)
	public void validById(String ids,
			Integer valid) {
		//1.验证参数的有效性(无效一般会抛出异常)
		if(ids==null||ids.length()==0)
		throw new ServiceException("至少应该选择一条记录");
		if(valid!=0&valid!=1)
		throw new ServiceException("valid状态数据无效");
		//2.执行更新操作
		String[] idArray=ids.split(",");//[1,2,3,4]
		int rows=
		teamDao.validById(idArray, valid);
		//3.验证结果(成功以后返回结果应该是>=1)
		if(rows==-1)
		throw new ServiceException("修改失败");
	}
	/**执行数据写入操作*/
	public void saveObject(Team entity) {
		//1.对参数进行业务验证
		if(entity==null)
		throw new ServiceException("保存的数据不能为空");
		//2.执行保存操作
		int rows=teamDao.insertObject(entity);
		System.out.println("rows=="+rows);
		//3.对结果进行判定
		if(rows==-1)
		throw new ServiceException("写入数据失败");
	}

}

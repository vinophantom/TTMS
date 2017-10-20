package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
@Transactional
@Service
public class ProjectServiceImpl 
       implements ProjectService {
	/**使用@Resource注解为属性注入值时,
	 * 是先按名字查找,还是先按类型查找?*/
	@Resource
	private ProjectDao projectDao;
	/**查询,获取项目信息*/
	public Map<String,Object> findObjects(
			String name,
			Integer valid,
			int pageCurrent) {
		//1.通过dao对象的方法获取当前页项目信息
		//1.1定义每页最多显示2条数据
		int pageSize=2;
		//1.2计算当前页开始查找的位置
		int startIndex=(pageCurrent-1)*pageSize;
		//1.3开始查询当前页的数据
		List<Project> list=
		projectDao.findObjects(
		name,valid,startIndex,pageSize);
		//2.获得总记录数,计算总页数,然后进行封装
		//2.1 查询总记录数
		int rowCount=
		projectDao.getRowCount(name,valid);
		//2.3封装分页信息(自己定义PageObject)
		PageObject pageObject=new PageObject();
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setStartIndex(startIndex);
		//3.将数据封装到map(两个对象需要传回页面)
		Map<String,Object> map=
		new HashMap<String,Object>();
		//3.1封装当前页数据
		map.put("list", list);
		//3.2封装分页对象信息
		map.put("pageObject", pageObject);
		return map;
	}
	/**
	 * 启用或禁用项目信息
	 * @param idStr 包含页面上选中的多个id值,
	 * 具体格式:"11,12,13,15"
	 * @param valid 具体启用和禁用的值
	 * */
	public void validById(String idStr,Integer valid) {
		System.out.println("valid="+valid);
		//1.对参数进行业务验证(提高系统的容错能力)
		if(idStr==null||idStr.trim().length()==0)
		throw new ServiceException("至少选择一项");
		if(valid!=0&&valid!=1)
		throw new ServiceException("valid值必须是0或者1");
		//2.将字符串解析为数组(获得选中的所有id值)
		String[] ids=idStr.split(",");
		//3.执行启用或禁用的更新操作
		projectDao.validById(ids,valid);
	}
	/**执行写入操作
	 * @param entity 封装了用户页面上输入的数据
	 * */
	public void saveObject(Project entity) {
		//1.对参数进行业务验证
		if(entity==null)
		throw new ServiceException("写入的数据不能为空");
		//2.执行写入操作(返回值默认为写入的行数)
		int rows=
		projectDao.insertObject(entity);
		//3.对结果进行业务判定
		if(rows==-1)
		throw new ServiceException("insert error");
	}
	/**根据id查找project对象*/
	public Project findObjectById(
			Integer id) {
		//1.判定id的有效性
		if(id==null||id<=0)
		throw new ServiceException(
				"id值无效:id="+id);
		//2.根据id查找对应的对象
		Project project=
		projectDao.findObjectById(id);
		//3.判定结果是否正确
		if(project==null)
		throw new ServiceException(
		"没有找到对应的记录");
		//4.返回结果
		return project;
	}
	/**执行修改操作
	 * @param entity指向的对象封装了页面上要
	 * 修改的数据.
	 * */
	public void updateObject(Project entity) {
		//1.判定参数的有效性
		if(entity==null)
		throw new ServiceException("被修改的记录不能空");
		//2.执行修改操作
		int rows=projectDao.updateObject(entity);
		//3.根据结果判定是否修改成功
		if(rows!=1)
		throw new RuntimeException("修改失败");
	}
}









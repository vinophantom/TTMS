package cn.tedu.ttms.common.dao;
/***
 * 通过此接口实现对子类共性的提取
 * @author adminitartor
 * @param <T>
 * 通过类上的泛型<T>约束类中:
 * 1)方法的参数类型
 * 2)方法的返回值类型
 */
public interface BaseDao<T> {
	int insertObject(T entity);
	int updateObject(T entity);
}





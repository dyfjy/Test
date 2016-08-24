package org.andy.shop.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.andy.exception.OperationException;
/**
 * baseDao接口
 *@Title:  
 *@Description:  
 *@Author:cdi  
 *@Since:2016年8月24日  
 *@Version:1.1.0
 */
public interface BaseDao<T> {
	public void save(T t) throws OperationException;

	public void saveOrUpdate(T t) throws OperationException;

	public void delete(T t);

	public void deleteById(Serializable id) throws OperationException;

	public void delete(List<T> list) throws OperationException;

	public void update(T t) throws OperationException;

	public T load(Serializable id);

	public T get(Serializable id);

	public T get(String hql, Object... params);

	public List<T> list();

	public List<T> list(String hql);

	public List<T> list(String hql, Object... params);
	public List<Map> mapList(String hql);

	public int getAllRowCount(String hql);

	public PageList<T> findPageList(String hql, int page, int rows);

	public PageList<T> findPageList(String hql, int page, int rows, Object... objects);

}
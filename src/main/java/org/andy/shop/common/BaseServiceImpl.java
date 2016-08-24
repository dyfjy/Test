package org.andy.shop.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.andy.exception.OperationException;

public class BaseServiceImpl<T extends Serializable> implements BaseService<T> {
	private BaseDao<T> baseDao;

	public void setiBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public void save(T t) throws OperationException {
		baseDao.save(t);
	}

	@Override
	public void saveOrUpdate(T t) throws OperationException {
		// TODO Auto-generated method stub  
		baseDao.saveOrUpdate(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub  
		baseDao.delete(t);
	}

	@Override
	public void deleteById(Serializable id) throws OperationException {
		// TODO Auto-generated method stub  
		baseDao.deleteById(id);
	}

	@Override
	public void delete(List<T> list) throws OperationException {
		// TODO Auto-generated method stub  
		baseDao.delete(list);
	}

	@Override
	public void update(T t) throws OperationException {
		// TODO Auto-generated method stub  
		baseDao.update(t);
	}

	@Override
	public T load(Serializable id) {
		// TODO Auto-generated method stub  
		return baseDao.load(id);
	}

	@Override
	public T get(Serializable id) {
		// TODO Auto-generated method stub  
		return baseDao.get(id);
	}

	@Override
	public T get(String hql, Object... params) {
		// TODO Auto-generated method stub  
		return baseDao.get(hql, params);
	}

	@Override
	public List<T> list() {
		// TODO Auto-generated method stub  
		return baseDao.list();
	}

	@Override
	public List<T> list(String hql) {
		// TODO Auto-generated method stub  
		return baseDao.list(hql);
	}

	@Override
	public List<T> list(String hql, Object... params) {
		// TODO Auto-generated method stub  
		return baseDao.list(hql, params);
	}

	@Override
	public List<Map> mapList(String hql) {
		// TODO Auto-generated method stub  
		return baseDao.mapList(hql);
	}

	@Override
	public int getAllRowCount(String hql) {
		// TODO Auto-generated method stub  
		return baseDao.getAllRowCount(hql);
	}

	@Override
	public PageList<T> findPageList(String hql, int page, int rows) {
		// TODO Auto-generated method stub  
		return baseDao.findPageList(hql, page, rows);
	}

	@Override
	public PageList<T> findPageList(String hql, int page, int rows, Object... objects) {
		// TODO Auto-generated method stub  
		return baseDao.findPageList(hql, page, rows, objects);
	}

	
}

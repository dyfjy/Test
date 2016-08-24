package org.andy.shop.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.andy.exception.OperationException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	@Resource(name="sessionFactory")  
    public void setSuperSessionFactory(SessionFactory sessionFactory) {  
        super.setSessionFactory(sessionFactory);  
    }


	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub  
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void deleteById(Serializable id) throws OperationException {
		// TODO Auto-generated method stub  
		T t = null;
		try {
			t = this.load(id);
			this.getHibernateTemplate().delete(t);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationException("删除失败！");
		}
	}

	@Override
	public void delete(List<T> list) throws OperationException {
		// TODO Auto-generated method stub  
		try {
			for (T t : list) {
				delete(t);
			}
		} catch (Exception e) {
			throw new OperationException("操作失败！");
		}
	}

	@Override
	public void update(T t) throws OperationException {
		// TODO Auto-generated method stub  
		try {
			super.getHibernateTemplate().update(t);
		} catch (Exception e) {
			throw new OperationException("操作失败！");
		}
	}

	@Override
	public T load(Serializable id) {
		// TODO Auto-generated method stub  
		return (T) super.getHibernateTemplate().load(clazz(), id);
	}

	@Override
	public T get(Serializable id) {
		// TODO Auto-generated method stub  
		return (T) super.getHibernateTemplate().get(clazz(), id);
	}

	@Override
	public T get(String hql, Object... params) {
		T t = null;
		List<T> list = null;
		list = (List<T>) this.getHibernateTemplate().find(hql, params);

		if (list != null && list.size() > 0)
			t = list.get(0);
		return t;
	}
 
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<T> list() {
		List<T> list = null;
		String hql = "from " + this.getEntityName(clazz());
		list = (List<T>) super.getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 查询
	 * 
	 * @param hql
	 *            hql语句
	 * @return
	 */
	public List<T> list(String hql) {
		List<T> list = null;
		list = (List<T>) this.getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 查询
	 * 
	 * @param hql
	 *            hql语句
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> mapList(String hql) {
		List<Map> list = null;
		list = (List<Map>) this.getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 带参数查询
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            hql中的参数
	 * @return
	 */
	public List<T> list(String hql, Object... params) {
		List<T> list = null;
		if (params != null && params.length > 0) {
			list = (List<T>) super.getHibernateTemplate().find(hql, params);
		} else {
			list = (List<T>) super.getHibernateTemplate().find(hql);
		}
		return list;
	}

	/**
	 * 添加
	 * 
	 * @param t
	 * @throws OperationException
	 */
	public void save(T t) throws OperationException {

		try {
			super.getHibernateTemplate().save(t);
		} catch (Exception e) {
			throw new OperationException("操作失败！");
		}

	}
	/**
	 * 新增或修改修改
	 * 
	 * @param t
	 * @throws OperationException
	 */
	public void saveOrUpdate(T t) throws OperationException {
		try {
			super.getHibernateTemplate().saveOrUpdate(t);
		} catch (Exception e) {
			throw new OperationException("操作失败！");
		}
	}

	/**
	 * 获取当前实体类
	 * 
	 * @return
	 */
	private Class clazz() {
		Class clazz = null;
		Type genType = this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		clazz = (Class) params[0];
		return clazz;
	}

	/**
	 * 当前实体类名称
	 * 
	 * @param <E>
	 * @param clazz
	 * @return
	 */
	private <E> String getEntityName(Class<E> clazz) {
		String entityname = clazz.getSimpleName();
		return entityname;
	}

	/**
	 * 查询所有记录数
	 * 
	 * @return 总记录数
	 */
	public int getAllRowCount(String hql) {
		return Integer.parseInt(String.valueOf(getHibernateTemplate().find(hql).get(0)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findPageList(java.lang.String, int, int)
	 */
	@Override
	public PageList<T> findPageList(String hql, int page, int rows) {
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		return findPageList(query, page, rows);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findPageList(java.lang.String, int, int,
	 * java.lang.Object[])
	 */
	@Override
	public PageList<T> findPageList(String hql, int page, int rows, Object... objects) {
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		setParameter(query, objects);
		return findPageList(query, page, rows);
	}

	public PageList<T> findPageList(Query query, int page, int rows) {
		ScrollableResults sr = query.scroll();
		sr.last();
		int count = sr.getRowNumber() == -1 ? 0 : sr.getRowNumber() + 1;
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(page * rows);
		return new PageList<T>(page, rows, count, query.list());
	}

	void setParameter(Query query, Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
	}
	
	
	
	
	
}
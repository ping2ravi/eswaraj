package com.next.esw.persistance.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gdata.util.common.base.StringUtil;

public class BaseDaoHibernateSpring<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	@Autowired
	private SessionFactory sessionFactory;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	protected void evictObject(T object){
		this.sessionFactory.getCurrentSession().evict(object);
	}
	protected T saveObject(T object){
		this.sessionFactory.getCurrentSession().saveOrUpdate(object);
		return object;
	}
	protected void deleteObject(T object){
		this.sessionFactory.getCurrentSession().delete(object);
	}
	@SuppressWarnings("unchecked")
	protected T getObjectById(Class<T> type,long id){
		return (T)getCurrentSession().load(type, id);
	}
	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> abc){
		String className = getClassName(abc.toString());
		return this.sessionFactory.getCurrentSession().createQuery("from "+className).list();
	}
	@SuppressWarnings("unchecked")
	public T executeQueryGetObject(String query){
		return (T)this.sessionFactory.getCurrentSession().createQuery(query).uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public T executeQueryGetObject(String query,Map<String, Object> params){
		Query hibernateQuery = this.sessionFactory.getCurrentSession().createQuery(query);
		for(Entry<String, Object> oneEntry:params.entrySet()){
			hibernateQuery.setParameter(oneEntry.getKey(), oneEntry.getValue());
		}
		return (T)hibernateQuery.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<T> executeQueryGetList(String query){
		return executeQueryGetList(query, 0);
	}
	@SuppressWarnings("unchecked")
	public List<T> executeQueryGetList(String query, int pageSize){
		return executeQueryGetList(query, null, pageSize);
	}
	@SuppressWarnings("unchecked")
	public List<T> executeQueryGetList(String query,Map<String, Object> params){
		return executeQueryGetList(query, params, 0);
	}
	@SuppressWarnings("unchecked")
	public List<T> executeQueryGetList(String query,Map<String, Object> params, int pageSize){
		return executeQueryGetList(query, params, pageSize, 0);
	}
	@SuppressWarnings("unchecked")
	public List<T> executeQueryGetList(String query,Map<String, Object> params, int pageSize, int pageNumber){
		Query hibernateQuery = this.sessionFactory.getCurrentSession().createQuery(query);
		if(params != null){
			for(Entry<String, Object> oneEntry:params.entrySet()){
				if(oneEntry.getValue() instanceof Collection){
					hibernateQuery.setParameterList(oneEntry.getKey(), (Collection)oneEntry.getValue());
				}else{
					hibernateQuery.setParameter(oneEntry.getKey(), oneEntry.getValue());	
				}
				
			}
		}
		if(pageSize > 0 ){
			hibernateQuery.setMaxResults(pageSize);	
		}
		if(pageNumber > 0){
			hibernateQuery.setFirstResult((pageNumber - 1) * pageSize);
		}
		
		return (List<T>)hibernateQuery.list();
	}
	public List<Long> executeSqlQueryGetListOfLong(String query){
		return executeSqlQueryGetListOfLong(query, null);
	}
	public List<Long> executeSqlQueryGetListOfLong(String query,Map<String, Object> params){
		Query sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(query);
		if(params != null){
			for(Entry<String, Object> oneEntry:params.entrySet()){
				if(oneEntry.getValue() instanceof Collection){
					sqlQuery.setParameterList(oneEntry.getKey(), (Collection)oneEntry.getValue());
				}else{
					sqlQuery.setParameter(oneEntry.getKey(), oneEntry.getValue());	
				}
			}
		}
		@SuppressWarnings("rawtypes")
		List results = sqlQuery.list();
		List<Long> returnList = new ArrayList<Long>();
        for(ListIterator iter = results.listIterator(); iter.hasNext(); ) {
        	returnList.add(((BigInteger)iter.next()).longValue());
        }
		return returnList;
	}
	public List executeSqlQueryGetObjectList(String query){
		return executeSqlQueryGetListOfLong(query, null);
	}
	public List executeSqlQueryGetObjectList(String query,Map<String, Object> params){
		return executeSqlQueryGetObjectList(query, params, 0, 0);
	}
	public List executeSqlQueryGetObjectList(String query,Map<String, Object> params, int pageSize){
		return executeSqlQueryGetObjectList(query, params, pageSize, 0);
	}
	public List executeSqlQueryGetObjectList(String query,Map<String, Object> params, int pageSize, int pageNumber){
		Query sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(query);
		if(params != null){
			for(Entry<String, Object> oneEntry:params.entrySet()){
				if(oneEntry.getValue() instanceof Collection){
					sqlQuery.setParameterList(oneEntry.getKey(), (Collection)oneEntry.getValue());
				}else{
					sqlQuery.setParameter(oneEntry.getKey(), oneEntry.getValue());	
				}
			}
		}
		if(pageSize > 0 ){
			sqlQuery.setMaxResults(pageSize);	
		}
		if(pageNumber > 0){
			sqlQuery.setFirstResult((pageNumber - 1) * pageSize);
		}
		return sqlQuery.list();
	}
	public void executeSqlQueryUpdate(String query,Map<String, Object> params){
		Query sqlQuery = this.sessionFactory.getCurrentSession().createSQLQuery(query);
		if(params != null){
			for(Entry<String, Object> oneEntry:params.entrySet()){
				if(oneEntry.getValue() instanceof Collection){
					sqlQuery.setParameterList(oneEntry.getKey(), (Collection)oneEntry.getValue());
				}else{
					sqlQuery.setParameter(oneEntry.getKey(), oneEntry.getValue());	
				}
			}
		}
		sqlQuery.executeUpdate();
	}
	public String getOnlyName(Class<T> abc){
		String className = getClassName(abc.toString());
		return className;
	}
	protected String getClassName(String fullName){
		return fullName.substring(fullName.lastIndexOf(".") + 1);
	}
	protected void checkIfStringMissing(String name,String value){
		if(StringUtil.isEmpty(value)){
			throw new RuntimeException(name +" can not be null or empty");
		}
	}
	protected void checkIfObjectMissing(String name,Object value){
		if(value == null){
			throw new RuntimeException(name +" can not be null");
		}
	}
	
	public Long executeQueryGetLong(String query){
		return (Long)this.sessionFactory.getCurrentSession().createQuery(query).uniqueResult();
	}


}

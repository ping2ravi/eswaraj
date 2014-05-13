package com.eswaraj.core.convertors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.eswaraj.core.exceptions.ApplicationException;

public abstract class BaseConvertor<DbType, WebType> {


	public DbType convert(WebType webDto) throws ApplicationException{
		return convertInternal(webDto);
	}
	
	protected abstract DbType convertInternal(WebType webDto) throws ApplicationException;
	
	public WebType convertBean(DbType dbDto) throws ApplicationException{
		if(dbDto == null){
			return null;
		}
		return convertBeanInternal(dbDto);
	}
	
	protected abstract WebType convertBeanInternal(DbType dbDto) throws ApplicationException;
	
	public List<WebType> convertBeanList(Collection<DbType> dbTypeCollection) throws ApplicationException{
		List<WebType> webTypeList = new ArrayList<>();
		for(DbType oneDbType:dbTypeCollection){
			webTypeList.add(convertBean(oneDbType));
		}
		return webTypeList;
	}
}

package com.eswaraj.core.convertors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseConvertor<DbType, WebType> {


	public DbType convert(WebType webDto){
		if(webDto == null){
			return null;
		}
		return convertInternal(webDto);
	}
	
	protected abstract DbType convertInternal(WebType webDto);
	
	public WebType convertBean(DbType dbDto){
		if(dbDto == null){
			return null;
		}
		return convertBeanInternal(dbDto);
	}
	
	protected abstract WebType convertBeanInternal(DbType dbDto);
	
	public List<WebType> convertBeanList(Collection<DbType> dbTypeCollection){
		List<WebType> webTypeList = new ArrayList<>();
		for(DbType oneDbType:dbTypeCollection){
			webTypeList.add(convertBean(oneDbType));
		}
		return webTypeList;
	}
}

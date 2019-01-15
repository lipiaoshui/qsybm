package com.jeecg.service;
import com.jeecg.entity.QsyTermInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface QsyTermInfoServiceI extends CommonService{
	
 	public void delete(QsyTermInfoEntity entity) throws Exception;
 	
 	public Serializable save(QsyTermInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(QsyTermInfoEntity entity) throws Exception;
 	
}

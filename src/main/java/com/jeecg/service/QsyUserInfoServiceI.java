package com.jeecg.service;
import com.jeecg.entity.QsyUserInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface QsyUserInfoServiceI extends CommonService{
	
 	public void delete(QsyUserInfoEntity entity) throws Exception;
 	
 	public Serializable save(QsyUserInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(QsyUserInfoEntity entity) throws Exception;
 	
}

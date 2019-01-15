package com.jeecg.service;
import com.jeecg.entity.QsyMerchBaseInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface QsyMerchBaseInfoServiceI extends CommonService{
	
 	public void delete(QsyMerchBaseInfoEntity entity) throws Exception;
 	
 	public Serializable save(QsyMerchBaseInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(QsyMerchBaseInfoEntity entity) throws Exception;
 	
}

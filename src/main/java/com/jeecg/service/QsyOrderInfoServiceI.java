package com.jeecg.service;
import com.jeecg.entity.QsyOrderInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface QsyOrderInfoServiceI extends CommonService{
	
 	public void delete(QsyOrderInfoEntity entity) throws Exception;
 	
 	public Serializable save(QsyOrderInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(QsyOrderInfoEntity entity) throws Exception;
 	
}

package com.jeecg.service;
import com.jeecg.entity.QsyOrderDetailInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface QsyOrderDetailInfoServiceI extends CommonService{
	
 	public void delete(QsyOrderDetailInfoEntity entity) throws Exception;
 	
 	public Serializable save(QsyOrderDetailInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(QsyOrderDetailInfoEntity entity) throws Exception;
 	
}

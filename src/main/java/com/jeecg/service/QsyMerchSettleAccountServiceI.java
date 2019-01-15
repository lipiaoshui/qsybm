package com.jeecg.service;
import com.jeecg.entity.QsyMerchSettleAccountEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface QsyMerchSettleAccountServiceI extends CommonService{
	
 	public void delete(QsyMerchSettleAccountEntity entity) throws Exception;
 	
 	public Serializable save(QsyMerchSettleAccountEntity entity) throws Exception;
 	
 	public void saveOrUpdate(QsyMerchSettleAccountEntity entity) throws Exception;
 	
}

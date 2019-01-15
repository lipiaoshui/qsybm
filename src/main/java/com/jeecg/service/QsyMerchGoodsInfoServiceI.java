package com.jeecg.service;
import com.jeecg.entity.QsyMerchGoodsInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface QsyMerchGoodsInfoServiceI extends CommonService{
	
 	public void delete(QsyMerchGoodsInfoEntity entity) throws Exception;
 	
 	public Serializable save(QsyMerchGoodsInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(QsyMerchGoodsInfoEntity entity) throws Exception;
 	
}

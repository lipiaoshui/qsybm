package com.jeecg.service;
import com.jeecg.entity.QsyGoodsInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface QsyGoodsInfoServiceI extends CommonService{
	
 	public void delete(QsyGoodsInfoEntity entity) throws Exception;
 	
 	public Serializable save(QsyGoodsInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(QsyGoodsInfoEntity entity) throws Exception;
 	
}

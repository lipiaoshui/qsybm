package com.jeecg.service.impl;
import com.jeecg.service.QsyMerchBaseInfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.entity.QsyMerchBaseInfoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("qsyMerchBaseInfoService")
@Transactional
public class QsyMerchBaseInfoServiceImpl extends CommonServiceImpl implements QsyMerchBaseInfoServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(QsyMerchBaseInfoEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(QsyMerchBaseInfoEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(QsyMerchBaseInfoEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}
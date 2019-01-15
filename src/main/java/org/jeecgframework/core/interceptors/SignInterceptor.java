package org.jeecgframework.core.interceptors;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.wrapper.RequestWrapper;
import org.jeecgframework.web.cgform.util.SignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * API接口签名校验机制，拦截 /api/**的请求，匹配签名是否与服务器签名一致
 * 
 * @author LiPiaoShui
 *
 */
public class SignInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(SignInterceptor.class);
	
	@Autowired
    private JdbcDao jdbcDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        try {
        	RequestWrapper myRequestWrapper = new RequestWrapper((HttpServletRequest) request);
        	JSONObject requestData = JSONObject.parseObject(myRequestWrapper.getBody());
            String sign = requestData.getString("sign");
            if (StringUtil.isEmpty(sign)) {
                throw new BusinessException("签名值不能为空");
            }
            String termCode = requestData.getString("termCode");
            if (StringUtil.isEmpty(termCode)) {
                throw new BusinessException("终端编号不能为空");
            }
            String randomStr = requestData.getString("randomStr");
            if (StringUtil.isEmpty(randomStr)) {
                throw new BusinessException("随机字符串不能为空");
            }
            Map<String, Object> paramMap =new HashMap<String, Object>();
            for(Entry<String, Object> entry : requestData.entrySet()) {
            	String key = entry.getKey();
				Object value = entry.getValue();
				if(value instanceof JSONObject) {
					JSONObject jsonObject = (JSONObject) value;
					value = jsonObject.toString().replaceAll("\"", "");
				}
				if(value instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) value;
					value = jsonArray.toString().replaceAll("\"", "");
				}
				paramMap.put(key, value);
			}
            String key = getKeyByTermCode(termCode);
            if(!SignatureUtil.checkSign(paramMap, key, sign)){
                throw new BusinessException("签名验证失败");
            }
        } catch (Exception e) {
        	logger.error(e.getMessage());
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //返回401错误
            return false;
        }
        return true;
    }
    
    /**
     * 根据终端编号查询终端密钥
     * @param termCode
     * @return
     */
    private String getKeyByTermCode(String termCode) {
		try {
			String sql = "select secret_key as secretKey from qsy_term_info where term_code=?";
			return jdbcDao.queryForObject(sql, String.class, termCode);
		} catch (Exception e) {
			throw new RuntimeException("【终端编号】：" + termCode + "不存在");
		}
    	
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    	
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
    	
    }
}

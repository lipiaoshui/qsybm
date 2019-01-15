package com.jeecg.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.enums.ApiEnum;
import com.jeecg.service.api.ApiService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api")
public class ApiController {

	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	private ApiService apiService;
	
	/**
	 * 查询商品信息
	 * @param body
	 * @return
	 */
	@ApiOperation(value="查询商品信息")
	@ResponseBody
	@RequestMapping(value="/getGoodsInfo",method=RequestMethod.POST)
	public Map<String, Object> getGoodsInfo(@RequestBody JSONObject body) {
		Map<String, Object> retMap;
		try {
			retMap = apiService.getGoodsInfo(body);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			retMap = new HashMap<String, Object>();
			retMap.put("statusCode", ApiEnum.UNKNOWN_ERROR.getStatusCode());
			retMap.put("message", ApiEnum.UNKNOWN_ERROR.getMessage());
		}
		return retMap;
	}
	
	/**
	 * 订单信息上送
	 * @param body
	 * @return
	 */
	@ApiOperation(value="订单信息上送")
	@ResponseBody
	@RequestMapping(value="/uploadOrderInfo",method=RequestMethod.POST)
	public Map<String, Object> uploadOrderInfo(@RequestBody JSONObject body) {
		Map<String, Object> retMap;
		try {
			retMap = apiService.uploadOrderInfo(body);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			retMap = new HashMap<String, Object>();
			retMap.put("statusCode", ApiEnum.UNKNOWN_ERROR.getStatusCode());
			retMap.put("message", ApiEnum.UNKNOWN_ERROR.getMessage());
		}
		return retMap;
	}
	
	/**
	 * 退款信息上送
	 * @param body
	 * @return
	 */
	@ApiOperation(value="退款信息上送")
	@ResponseBody
	@RequestMapping(value="/uploadRefundInfo",method=RequestMethod.POST)
	public Map<String, Object> uploadRefundInfo(@RequestBody JSONObject body) {
		Map<String, Object> retMap;
		try {
			retMap = apiService.uploadRefundInfo(body);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			retMap = new HashMap<String, Object>();
			retMap.put("statusCode", ApiEnum.UNKNOWN_ERROR.getStatusCode());
			retMap.put("message", ApiEnum.UNKNOWN_ERROR.getMessage());
		}
		return retMap;
	}
	
}

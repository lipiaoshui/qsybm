package com.jeecg.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 签名工具类
 * 
 * @author LiPiaoShui
 *
 */
public class SignUtils {

	/**
	 * 生成签名，加密前将参数名按ASCII码从小到大排序
	 * 	   
	 * @param params
	 * @param privateKey
	 * @return
	 */
	public static String createSign(Map<String,Object> params, String privateKey) {
		StringBuilder sb = new StringBuilder();
		Map<String,Object> sortParams = new TreeMap<String,Object>(params);
		for (Map.Entry<String,Object> entry : sortParams.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value != null && StringUtils.isNotEmpty(value.toString().trim())) {
				sb.append("&").append(key).append("=").append(value);
			}
		}
		String signValue = sb.toString().replaceFirst("&", "") + "&key=" + privateKey;
		return MD5Utils.MD5Encode(signValue);
	}
	
	public static void main(String[] args) {
//		// 1、参数值不包含JSONObject或JSONArray
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("termCode", "ZD19010300001");
//		params.put("randomStr", "17c67bf88f074dd7b6d18f127f38cdb6");
//		params.put("goodsBarcode", "111222333");
//		String key = "42613ac47dce41c585c083616360aff6";
//		String sign = createSign(params,key);
//		System.out.println("sign="+sign.toUpperCase());
		
//		// 2、参数值包含JSONObject或JSONArray
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("termCode", "ZD19011400001");
//		params.put("randomStr", "17c67bf88f074dd7b6d18f127f38cdb6");
//		params.put("payCode", "1001");
//		params.put("payType", "01");
//		params.put("orderAmt", 20000);
//		JSONArray jsonArray = new JSONArray();
//		JSONObject jsonObject1 = new JSONObject();
//		jsonObject1.put("goodsBarcode", "101");
//		jsonObject1.put("goodsName", "可口可乐");
//		jsonObject1.put("goodsPrice", 5000);
//		jsonArray.add(jsonObject1);
//		JSONObject jsonObject2 = new JSONObject();
//		jsonObject2.put("goodsBarcode", "102");
//		jsonObject2.put("goodsName", "康师傅红烧牛肉面");
//		jsonObject2.put("goodsPrice", 15000);
//		jsonArray.add(jsonObject2);
//		params.put("goodsList", jsonArray.toString().replaceAll("\"", ""));
//		String key = "1b0cbb87989942e0b319f6aaf36864e3";
//		String sign = createSign(params,key);
//		System.out.println("sign="+sign.toUpperCase());
		
		// 2、参数值包含JSONObject或JSONArray
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("termCode", "ZD19011400001");
		params.put("randomStr", "17c67bf88f074dd7b6d18f127f38cdb6");
		params.put("payCode", "1001");
		params.put("payType", "01");
		params.put("orderAmt", 20000);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("goodsBarcode", "101");
		jsonObject1.put("goodsName", "可口可乐");
		jsonObject1.put("goodsPrice", 5000);
		jsonArray.add(jsonObject1);
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("goodsBarcode", "102");
		jsonObject2.put("goodsName", "康师傅红烧牛肉面");
		jsonObject2.put("goodsPrice", 15000);
		jsonArray.add(jsonObject2);
		params.put("goodsList", jsonArray.toString().replaceAll("\"", ""));
		String key = "1b0cbb87989942e0b319f6aaf36864e3";
		String sign = createSign(params,key);
		System.out.println("sign="+sign.toUpperCase());
	}

}

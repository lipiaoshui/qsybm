package com.jeecg.service.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.constant.ApiConstant;
import com.jeecg.entity.QsyMerchGoodsInfoEntity;
import com.jeecg.entity.QsyOrderDetailInfoEntity;
import com.jeecg.entity.QsyOrderInfoEntity;
import com.jeecg.enums.ApiEnum;
import com.jeecg.utils.DateUtils;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApiService {

	@Autowired
	private JdbcDao jdbcDao;
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * 查询商品信息
	 * 
	 * @param body
	 * @return
	 */
	public Map<String, Object> getGoodsInfo(JSONObject body) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("statusCode", ApiEnum.ACCESS_SUCCESS.getStatusCode());
		retMap.put("message", ApiEnum.ACCESS_SUCCESS.getMessage());
		
		// 商品信息
		Map<String, Object> goodMap = new HashMap<String, Object>();
		
		// 终端编号
		String termCode = body.getString("termCode");

		// 商品条形码
		String goodsBarcode = body.getString("goodsBarcode");
		
		//（1）根据终端编号从终端信息表获取商户编号
		StringBuilder sql = new StringBuilder();
		sql.append(" select merch_code from qsy_term_info where term_code=?");
		String merchCode = jdbcDao.queryForObject(sql.toString(), String.class, termCode);

		//（2）根据商户编号和商品条形码从商户商品信息表获取商品信息
		sql.delete(0, sql.length());
		sql.append(" select goods_barcode as goodsBarcode,sell_short_name as goodsName,goods_refer_price as goodsPrice from qsy_merch_goods_info where merch_code=? and goods_barcode=?");
		
		try {
			goodMap = jdbcDao.queryForMap(sql.toString(),merchCode,goodsBarcode);
		} catch (DataAccessException e) {
			// 查询结果为空，则根据商品条形码从商品信息表获取商品信息
			sql.delete(0, sql.length());
			sql.append(" select goods_barcode as goodsBarcode,sell_short_name as goodsName,goods_refer_price as goodsPrice from qsy_goods_info where goods_barcode=?");
			try {
				goodMap = jdbcDao.queryForMap(sql.toString(),goodsBarcode);
			} catch (DataAccessException e1) {
				// 查询结果为空，则返回10002状态码，返回信息查询不到数据
				retMap.put("statusCode", ApiEnum.QUERY_NO_DATA.getStatusCode());
				retMap.put("message", ApiEnum.QUERY_NO_DATA.getMessage());
				return retMap;
			}
		}
		
		retMap.put("goodsInfo", goodMap);
		return retMap;
	}
	
	/**
	 * 订单信息上送
	 * 
	 * @param body
	 * @return
	 */
	public Map<String, Object> uploadOrderInfo(JSONObject body) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("statusCode", ApiEnum.ACCESS_SUCCESS.getStatusCode());
		retMap.put("message", ApiEnum.ACCESS_SUCCESS.getMessage());
		
		// 终端编号
		String termCode = body.getString("termCode"); 
		
		// 商户编号
		StringBuilder sql = new StringBuilder();
		sql.append(" select merch_code as merchCode from qsy_term_info where term_code = ?");
		String merchCode = null;
		try {
			merchCode = jdbcDao.queryForObject(sql.toString(), String.class, termCode);
		} catch (DataAccessException e) {
			// 查询结果为空，则返回10002状态码，返回信息查询不到数据
			retMap.put("statusCode", ApiEnum.QUERY_NO_DATA.getStatusCode());
			retMap.put("message", ApiEnum.QUERY_NO_DATA.getMessage());
			return retMap;
		}
		
		// 订单详情集合
		List<QsyOrderDetailInfoEntity> orderDetailList = new ArrayList<QsyOrderDetailInfoEntity>();
		
		// 商品价格不一致的集合
		List<QsyMerchGoodsInfoEntity> merchGoodsList = new ArrayList<QsyMerchGoodsInfoEntity>();
		
		// 商品列表
		JSONArray goodsList = body.getJSONArray("goodsList"); 
		
		// 支付方式，01-扫码，99-其他
		String payType = body.getString("payType"); 
		
		// 订单金额
		long orderAmt = body.getLong("orderAmt"); 

		// 订单号
		String orderNo = DateUtils.getCurrentDate("yyyyMMddHHmmssSSS")+StringUtil.random(6); 
		
		retMap.put("orderNo", orderNo);
		retMap.put("orderAmt", orderAmt);
		
		// 订单信息
		QsyOrderInfoEntity qsyOrderInfoEntity = new QsyOrderInfoEntity();
		qsyOrderInfoEntity.setOrderNo(orderNo);
		qsyOrderInfoEntity.setOrderAmt(orderAmt);
		qsyOrderInfoEntity.setTermCode(termCode);
		qsyOrderInfoEntity.setPayType(payType);
		qsyOrderInfoEntity.setMerchCode(merchCode);
		qsyOrderInfoEntity.setOrderTime(DateUtils.getCurrentDate());
		
		// 订单商品价格汇总 
		long count = 0;
		
		for(int i=0;i<goodsList.size();i++){
			// 遍历订单信息
			JSONObject jsonObject = goodsList.getJSONObject(i);
			
			// 商品条形码
			String goodsBarcode = jsonObject.getString("goodsBarcode");

//			// 商品名称
//			String goodsName = jsonObject.getString("goodsName");

			// 商品价格
			long goodPrice = jsonObject.getLong("goodsPrice");
			
			// 保存订单详情
			QsyOrderDetailInfoEntity qsyOrderDetailInfoEntity = new QsyOrderDetailInfoEntity();
			qsyOrderDetailInfoEntity.setGoodsBarcode(goodsBarcode);
			qsyOrderDetailInfoEntity.setGoodPrice(goodPrice);
			qsyOrderDetailInfoEntity.setOrderNo(orderNo);
			
			// 前台传来的价格是否与数据库一致
			sql.delete(0, sql.length());
			sql.append(" select id,goods_price as goodsPrice,goods_name as goodsName from qsy_merch_goods_info where merch_code = ? and goods_barcode = ?");
			try {
				Map<String,Object> goodMap = jdbcDao.queryForMap(sql.toString(), merchCode, goodsBarcode);
				QsyMerchGoodsInfoEntity qsyMerchGoodsInfoEntity = systemService.getEntity(QsyMerchGoodsInfoEntity.class, (String)goodMap.get("id"));
				qsyOrderDetailInfoEntity.setGoodsName((String)goodMap.get("goodsName"));
				
				if(goodPrice!=(long)goodMap.get("goodsPrice")){
					qsyMerchGoodsInfoEntity.setGoodsPrice(goodPrice);
					// 不一致则修改
					merchGoodsList.add(qsyMerchGoodsInfoEntity);
				}
			} catch (DataAccessException e1) {
			}
			count += goodPrice;
//			systemService.saveOrUpdate(qsyOrderDetailInfoEntity);
			orderDetailList.add(qsyOrderDetailInfoEntity);
		}
		if(orderAmt!=count){ // 校验商品信息列表的总金额是否与订单金额一致，不一致则不允许下订单，并返回订单金额不一致错误
			retMap.put("payFlag", ApiConstant.PAYMENT_STATE_FAIL); // 失败
			retMap.put("statusCode", ApiEnum.AMOUNT_NOT_EQUAL.getStatusCode());
			retMap.put("message", ApiEnum.AMOUNT_NOT_EQUAL.getMessage());
			return retMap;
		}
		if(ApiConstant.PAYMENT_METHOD_OTHER.equals(payType)){ // 其他支付
			retMap.put("payFlag", ApiConstant.PAYMENT_STATE_SUCCESS); // 直接成功
			qsyOrderInfoEntity.setOrderState(ApiConstant.ORDER_STATE_03);
			qsyOrderInfoEntity.setPayType(payType);
			// 保存订单信息
			systemService.saveOrUpdate(qsyOrderInfoEntity);
			return retMap;
		}else{
			// 保存订单信息
			qsyOrderInfoEntity.setOrderState("02"); // 付款中
			systemService.saveOrUpdate(qsyOrderInfoEntity);
			sql.delete(0, sql.length());
			
			// 1.根据终端号查询商户编号
			sql.append(" select merch_code as merchCode from qsy_term_info where term_code=?");
			merchCode = jdbcDao.queryForObject(sql.toString(), String.class, termCode);
			
			// 2.查询商户开通的支付渠道，调用渠道支付接口
			sql.delete(0, sql.length());
			sql.append(" select channel_code as channelCode from qsy_channel_merch_info where merch_code=?");
			String channelCode = jdbcDao.queryForObject(sql.toString(), String.class, merchCode);
			qsyOrderInfoEntity.setChannelCode(channelCode);
			
			boolean flag = false;
			boolean channelPay = true;
			for(int i=0;i<3;i++){
				// TODO 调用渠道支付接口
//				if(i==2) channelPay = true;
				if(channelPay){
					flag = true;
					break;
				}else{
					System.err.println("第"+(i+1)+"次接口出错，5秒后重复运行");
					threadSleep();
				}
			}
			if(flag){
				// 成功
				retMap.put("payFlag", ApiConstant.PAYMENT_STATE_SUCCESS); 
				qsyOrderInfoEntity.setOrderState(ApiConstant.ORDER_STATE_03);
				
				// 保存商品订单详情
				if(orderDetailList.size()>0){
					for(QsyOrderDetailInfoEntity detail : orderDetailList){
						systemService.saveOrUpdate(detail);
					}
				}
				
				// 修改价格不一致的商品
				if(merchGoodsList.size()>0){
					for(QsyMerchGoodsInfoEntity merchGoods:merchGoodsList){
						systemService.updateEntitie(merchGoods);
					}
				}
			}else{
				// 失败
				retMap.put("payFlag", ApiConstant.PAYMENT_STATE_FAIL); 
				qsyOrderInfoEntity.setOrderState(ApiConstant.ORDER_STATE_04);
			}
			qsyOrderInfoEntity.setTradeTime(DateUtils.getCurrentDate());
			systemService.saveOrUpdate(qsyOrderInfoEntity);
		}
		return retMap;
	}
	
	/**
	 * 线程睡眠5秒
	 */
	private void threadSleep(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 退款信息上送
	 * 
	 * @param body
	 * @return
	 */
	public Map<String, Object> uploadRefundInfo(JSONObject body) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("statusCode", ApiEnum.ACCESS_SUCCESS.getStatusCode());
		retMap.put("message", ApiEnum.ACCESS_SUCCESS.getMessage());
		
		// 商户订单号
		String orderNo = body.getString("orderNo"); 

		retMap.put("orderNo", orderNo);
		
		try {
			QsyOrderInfoEntity qsyOrderInfoEntity = systemService.findUniqueByProperty(QsyOrderInfoEntity.class, "orderNo", orderNo);
			
			// 获取订单支付方式
			String payType = qsyOrderInfoEntity.getPayType();
			
			// 订单状态
			String orderState = qsyOrderInfoEntity.getOrderState();
			
			// 判断订单状态 
			if(ApiConstant.ORDER_STATE_03.equals(orderState)){
				
				// 判断支付方式
				if(ApiConstant.PAYMENT_METHOD_CODE.equals(payType)){// 扫码
					
					// TODO 调用渠道方的退款接口
					boolean flag = false;
					
					if(flag){
						retMap.put("refundState", ApiConstant.REFUND_STATE_SUCCESS);
						qsyOrderInfoEntity.setOrderState(ApiConstant.ORDER_STATE_06);
						systemService.updateEntitie(qsyOrderInfoEntity);
					}else{
						retMap.put("refundState", ApiConstant.REFUND_STATE_FAIL);
					}
					
				}else{ 
					// 现金支付
					qsyOrderInfoEntity.setOrderState(ApiConstant.ORDER_STATE_06);
					systemService.updateEntitie(qsyOrderInfoEntity);
					retMap.put("refundState", ApiConstant.REFUND_STATE_SUCCESS);
					return retMap;
				}
			} else {
				// 订单状态不为支付成功
				retMap.put("statusCode", ApiEnum.ORDER_NOT_PAY.getStatusCode());
				retMap.put("message", ApiEnum.ORDER_NOT_PAY.getMessage());
				retMap.put("refundState", ApiConstant.REFUND_STATE_FAIL);
			}
		} catch (EmptyResultDataAccessException e) {
			// 查询不到订单号
			retMap.put("statusCode", ApiEnum.QUERY_NO_DATA.getStatusCode());
			retMap.put("message", ApiEnum.QUERY_NO_DATA.getMessage());
			retMap.put("refundState", ApiConstant.REFUND_STATE_FAIL);
		}
		return retMap;
	}
	
}

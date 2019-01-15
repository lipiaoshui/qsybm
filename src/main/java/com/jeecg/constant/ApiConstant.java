package com.jeecg.constant;

/**
 * API常量类
 * 
 * @author LiPiaoShui
 *
 */
public class ApiConstant {

	/** 自定义条形码编号 **/
	public static String CUSTOM_BARCODE_CODE = "9999999999999";
	
	/** 自定义条形码名称 **/
	public static String CUSTOM_BARCODE_NAME = "其他商品";
	
	/** 支付方式：01-扫码 **/
	public static String PAYMENT_METHOD_CODE = "01";

	/** 支付方式：99-其他 **/
	public static String PAYMENT_METHOD_OTHER = "99";

	/** 支付状态：1-成功 **/
	public static String PAYMENT_STATE_SUCCESS = "1";
	
	/** 支付状态：2-失败 **/
	public static String PAYMENT_STATE_FAIL = "2";
	
	/** 订单状态：01-待付款 **/
	public static String ORDER_STATE_01 = "01";
	
	/** 订单状态：02-付款中 **/
	public static String ORDER_STATE_02 = "02";
	
	/** 订单状态：03-付款成功 **/
	public static String ORDER_STATE_03 = "03";
	
	/** 订单状态：04-付款失败 **/
	public static String ORDER_STATE_04 = "04";
	
	/** 订单状态：05-订单取消 **/
	public static String ORDER_STATE_05 = "05";
	
	/** 订单状态：06-退款成功 **/
	public static String ORDER_STATE_06 = "06";
	
	/** 退款状态：1-成功 **/
	public static String REFUND_STATE_SUCCESS = "1";
	
	/** 退款状态：2-失败 **/
	public static String REFUND_STATE_FAIL = "2";
	
}
package com.jeecg.enums;

public enum ApiEnum {

	ACCESS_SUCCESS("10000", "访问成功"),
	UNKNOWN_ERROR("10001", "未知的错误"),
	QUERY_NO_DATA("10002", "查询不到数据"),
	AMOUNT_NOT_EQUAL("10003", "订单金额不一致"),
	ORDER_ALREADY_PAY("10004", "订单已支付"),
	ORDER_ALREADY_CLOSED("10005", "订单已关闭"),
	ORDER_NOT_PAY("10006", "订单未支付成功，不允许退款");
	
	private String statusCode;
	private String message;
	
	private ApiEnum(String statusCode,String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}
	
}

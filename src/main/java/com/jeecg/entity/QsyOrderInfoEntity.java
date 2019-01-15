package com.jeecg.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 订单信息表
 * @author onlineGenerator
 * @date 2019-01-09 14:59:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "qsy_order_info", schema = "")
@SuppressWarnings("serial")
public class QsyOrderInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**订单号*/
	@Excel(name="订单号",width=15)
	private java.lang.String orderNo;
	/**订单金额*/
	@Excel(name="订单金额",width=15)
	private long orderAmt;
	/**商户编号*/
	@Excel(name="商户编号",width=15)
	private java.lang.String merchCode;
	/**商品简述*/
	@Excel(name="商品简述",width=15)
	private java.lang.String goodsDesc;
	/**终端编号*/
	@Excel(name="终端编号",width=15)
	private java.lang.String termCode;
	/**支付方式*/
	@Excel(name="支付方式",width=15)
	private java.lang.String payType;
	/**渠道代码*/
	@Excel(name="渠道代码",width=15)
	private java.lang.String channelCode;
	/**渠道流水号*/
	@Excel(name="渠道流水号",width=15)
	private java.lang.String channelSerialNo;
	/**授权码信息*/
	@Excel(name="授权码信息",width=15)
	private java.lang.String authorCode;
	/**订单状态*/
	@Excel(name="订单状态",width=15)
	private java.lang.String orderState;
	/**订单说明*/
	@Excel(name="订单说明",width=15)
	private java.lang.String orderDesc;
	/**订单创建时间*/
	@Excel(name="订单创建时间",width=15)
	private java.lang.String orderTime;
	/**交易完成时间*/
	@Excel(name="交易完成时间",width=15)
	private java.lang.String tradeTime;
	/**创建时间*/
	private java.util.Date createTime;
	/**创建人*/
	private java.lang.String createBy;
	/**修改时间*/
	private java.util.Date updateTime;
	/**修改人*/
	private java.lang.String updateBy;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=32)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单号
	 */

	@Column(name ="ORDER_NO",nullable=true,length=32)
	public java.lang.String getOrderNo(){
		return this.orderNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单号
	 */
	public void setOrderNo(java.lang.String orderNo){
		this.orderNo = orderNo;
	}
	/**
	 *方法: 取得long
	 *@return: long  订单金额
	 */

	@Column(name ="ORDER_AMT",nullable=true,length=12)
	public long getOrderAmt(){
		return this.orderAmt;
	}

	/**
	 *方法: 设置long
	 *@param: long  订单金额
	 */
	public void setOrderAmt(long orderAmt){
		this.orderAmt = orderAmt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户编号
	 */

	@Column(name ="MERCH_CODE",nullable=true,length=32)
	public java.lang.String getMerchCode(){
		return this.merchCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户编号
	 */
	public void setMerchCode(java.lang.String merchCode){
		this.merchCode = merchCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品简述
	 */

	@Column(name ="GOODS_DESC",nullable=true,length=100)
	public java.lang.String getGoodsDesc(){
		return this.goodsDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品简述
	 */
	public void setGoodsDesc(java.lang.String goodsDesc){
		this.goodsDesc = goodsDesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  终端编号
	 */

	@Column(name ="TERM_CODE",nullable=true,length=32)
	public java.lang.String getTermCode(){
		return this.termCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端编号
	 */
	public void setTermCode(java.lang.String termCode){
		this.termCode = termCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付方式
	 */

	@Column(name ="PAY_TYPE",nullable=true,length=2)
	public java.lang.String getPayType(){
		return this.payType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付方式
	 */
	public void setPayType(java.lang.String payType){
		this.payType = payType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道代码
	 */

	@Column(name ="CHANNEL_CODE",nullable=true,length=20)
	public java.lang.String getChannelCode(){
		return this.channelCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道代码
	 */
	public void setChannelCode(java.lang.String channelCode){
		this.channelCode = channelCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  渠道流水号
	 */

	@Column(name ="CHANNEL_SERIAL_NO",nullable=true,length=50)
	public java.lang.String getChannelSerialNo(){
		return this.channelSerialNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道流水号
	 */
	public void setChannelSerialNo(java.lang.String channelSerialNo){
		this.channelSerialNo = channelSerialNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  授权码信息
	 */

	@Column(name ="AUTHOR_CODE",nullable=true,length=50)
	public java.lang.String getAuthorCode(){
		return this.authorCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  授权码信息
	 */
	public void setAuthorCode(java.lang.String authorCode){
		this.authorCode = authorCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单状态
	 */

	@Column(name ="ORDER_STATE",nullable=true,length=2)
	public java.lang.String getOrderState(){
		return this.orderState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单状态
	 */
	public void setOrderState(java.lang.String orderState){
		this.orderState = orderState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单说明
	 */

	@Column(name ="ORDER_DESC",nullable=true,length=200)
	public java.lang.String getOrderDesc(){
		return this.orderDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单说明
	 */
	public void setOrderDesc(java.lang.String orderDesc){
		this.orderDesc = orderDesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单创建时间
	 */

	@Column(name ="ORDER_TIME",nullable=true,length=20)
	public java.lang.String getOrderTime(){
		return this.orderTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单创建时间
	 */
	public void setOrderTime(java.lang.String orderTime){
		this.orderTime = orderTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易完成时间
	 */

	@Column(name ="TRADE_TIME",nullable=true,length=20)
	public java.lang.String getTradeTime(){
		return this.tradeTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易完成时间
	 */
	public void setTradeTime(java.lang.String tradeTime){
		this.tradeTime = tradeTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */

	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */

	@Column(name ="CREATE_BY",nullable=true,length=32)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */

	@Column(name ="UPDATE_TIME",nullable=true)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=32)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
}
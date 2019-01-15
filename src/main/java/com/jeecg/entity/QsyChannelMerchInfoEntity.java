package com.jeecg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 渠道商户信息表
 * @author fuwenjie
 * @date 2019-01-04 11:22:12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "qsy_channel_merch_info", schema = "")
@SuppressWarnings("serial")
public class QsyChannelMerchInfoEntity {

	/**主键*/
	private java.lang.String id;
	/**渠道代码*/
	@Excel(name="渠道代码",width=15)
	private java.lang.String channelCode;
	/**商户编号*/
	@Excel(name="商户编号",width=15)
	private java.lang.String merchCode;
	/**渠道方商户编号*/
	@Excel(name="渠道方商户编号",width=15)
	private java.lang.String channelMerchCode;
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
	 *@return: java.lang.String  渠道方商户编号
	 */
	@Column(name ="CHANNEL_MERCH_CODE",nullable=true,length=32)
	public java.lang.String getChannelMerchCode(){
		return this.channelMerchCode;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  渠道方商户编号
	 */
	public void setChannelMerchCode(java.lang.String channelMerchCode){
		this.channelMerchCode = channelMerchCode;
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

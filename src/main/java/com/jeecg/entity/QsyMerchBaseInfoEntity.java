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
 * @Description: 商户基本信息表
 * @author onlineGenerator
 * @date 2018-12-28 17:05:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "qsy_merch_base_info", schema = "")
@SuppressWarnings("serial")
public class QsyMerchBaseInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**商户编号*/
	private java.lang.String merchCode;
	/**商户名称*/
	@Excel(name="商户名称",width=15)
	private java.lang.String merchName;
	/**营业执照号*/
	@Excel(name="营业执照号",width=15)
	private java.lang.String businessLicenseNo;
	/**法人姓名*/
	@Excel(name="法人姓名",width=15)
	private java.lang.String legalRepreName;
	/**法人身份证号*/
	@Excel(name="法人身份证号",width=15)
	private java.lang.String legalRepreNum;
	/**证件有效期*/
	@Excel(name="证件有效期",width=15)
	private java.lang.String credExpireDate;
	/**商户地址*/
	@Excel(name="商户地址",width=15)
	private java.lang.String merchAddr;
	/**商户状态*/
	@Excel(name="商户状态",width=15)
	private java.lang.String merchState;
	/**联系电话*/
	@Excel(name="联系电话",width=15)
	private java.lang.String telephone;
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
	 *@return: java.lang.String  商户名称
	 */

	@Column(name ="MERCH_NAME",nullable=true,length=120)
	public java.lang.String getMerchName(){
		return this.merchName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户名称
	 */
	public void setMerchName(java.lang.String merchName){
		this.merchName = merchName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  营业执照号
	 */

	@Column(name ="BUSINESS_LICENSE_NO",nullable=true,length=30)
	public java.lang.String getBusinessLicenseNo(){
		return this.businessLicenseNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  营业执照号
	 */
	public void setBusinessLicenseNo(java.lang.String businessLicenseNo){
		this.businessLicenseNo = businessLicenseNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法人姓名
	 */

	@Column(name ="LEGAL_REPRE_NAME",nullable=true,length=30)
	public java.lang.String getLegalRepreName(){
		return this.legalRepreName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法人姓名
	 */
	public void setLegalRepreName(java.lang.String legalRepreName){
		this.legalRepreName = legalRepreName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法人身份证号
	 */

	@Column(name ="LEGAL_REPRE_NUM",nullable=true,length=20)
	public java.lang.String getLegalRepreNum(){
		return this.legalRepreNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法人身份证号
	 */
	public void setLegalRepreNum(java.lang.String legalRepreNum){
		this.legalRepreNum = legalRepreNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  证件有效期
	 */

	@Column(name ="CRED_EXPIRE_DATE",nullable=true,length=8)
	public java.lang.String getCredExpireDate(){
		return this.credExpireDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件有效期
	 */
	public void setCredExpireDate(java.lang.String credExpireDate){
		this.credExpireDate = credExpireDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户地址
	 */

	@Column(name ="MERCH_ADDR",nullable=true,length=200)
	public java.lang.String getMerchAddr(){
		return this.merchAddr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户地址
	 */
	public void setMerchAddr(java.lang.String merchAddr){
		this.merchAddr = merchAddr;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商户状态
	 */

	@Column(name ="MERCH_STATE",nullable=true,length=1)
	public java.lang.String getMerchState(){
		return this.merchState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商户状态
	 */
	public void setMerchState(java.lang.String merchState){
		this.merchState = merchState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系电话
	 */

	@Column(name ="TELEPHONE",nullable=true,length=20)
	public java.lang.String getTelephone(){
		return this.telephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	
	@Column(name ="CREATE_TIME",nullable=true,length=32)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改时间
	 */

	@Column(name ="UPDATE_TIME",nullable=true,length=20)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改时间
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
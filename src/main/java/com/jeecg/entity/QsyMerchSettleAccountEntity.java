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
 * @Description: 商户结算信息表
 * @author onlineGenerator
 * @date 2018-12-29 10:29:50
 * @version V1.0   
 *
 */
@Entity
@Table(name = "qsy_merch_settle_account", schema = "")
@SuppressWarnings("serial")
public class QsyMerchSettleAccountEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**商户编号*/
	@Excel(name="商户编号",width=15)
	private java.lang.String merchCode;
	/**银行账号*/
	@Excel(name="银行账号",width=15)
	private java.lang.String accountNo;
	/**开户名称*/
	@Excel(name="开户名称",width=15)
	private java.lang.String accountName;
	/**开户银行*/
	@Excel(name="开户银行",width=15)
	private java.lang.String openBankName;
	/**开户银行城市*/
	@Excel(name="开户银行城市",width=15)
	private java.lang.Integer openBankCity;
	/**开户支行*/
	@Excel(name="开户支行",width=15)
	private java.lang.String openBankBranch;
	/**账户类型*/
	@Excel(name="账户类型",width=15)
	private java.lang.String accountType;
	/**创建时间*/
	@Excel(name="创建时间",width=15)
	private java.util.Date createTime;
	/**创建人*/
	@Excel(name="创建人",width=15)
	private java.lang.String createBy;
	/**修改时间*/
	@Excel(name="修改时间",width=15)
	private java.util.Date updateTime;
	/**修改人*/
	@Excel(name="修改人",width=15)
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
	 *@return: java.lang.String  银行账号
	 */

	@Column(name ="ACCOUNT_NO",nullable=true,length=20)
	public java.lang.String getAccountNo(){
		return this.accountNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行账号
	 */
	public void setAccountNo(java.lang.String accountNo){
		this.accountNo = accountNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户名称
	 */

	@Column(name ="ACCOUNT_NAME",nullable=true,length=100)
	public java.lang.String getAccountName(){
		return this.accountName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户名称
	 */
	public void setAccountName(java.lang.String accountName){
		this.accountName = accountName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户银行
	 */

	@Column(name ="OPEN_BANK_NAME",nullable=true,length=32)
	public java.lang.String getOpenBankName(){
		return this.openBankName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户银行
	 */
	public void setOpenBankName(java.lang.String openBankName){
		this.openBankName = openBankName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  开户银行城市
	 */

	@Column(name ="OPEN_BANK_CITY",nullable=true,length=10)
	public java.lang.Integer getOpenBankCity(){
		return this.openBankCity;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  开户银行城市
	 */
	public void setOpenBankCity(java.lang.Integer openBankCity){
		this.openBankCity = openBankCity;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户支行
	 */

	@Column(name ="OPEN_BANK_BRANCH",nullable=true,length=100)
	public java.lang.String getOpenBankBranch(){
		return this.openBankBranch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户支行
	 */
	public void setOpenBankBranch(java.lang.String openBankBranch){
		this.openBankBranch = openBankBranch;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户类型
	 */

	@Column(name ="ACCOUNT_TYPE",nullable=true,length=1)
	public java.lang.String getAccountType(){
		return this.accountType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户类型
	 */
	public void setAccountType(java.lang.String accountType){
		this.accountType = accountType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建时间
	 */

	@Column(name ="CREATE_TIME",nullable=true,length=20)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建时间
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

	@Override
	public String toString() {
		return "QsyMerchSettleAccountEntity [id=" + id + ", merchCode=" + merchCode + ", accountNo=" + accountNo
				+ ", accountName=" + accountName + ", openBankName=" + openBankName + ", openBankCity=" + openBankCity
				+ ", openBankBranch=" + openBankBranch + ", accountType=" + accountType + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", updateTime=" + updateTime + ", updateBy=" + updateBy + "]";
	}
	
	
}
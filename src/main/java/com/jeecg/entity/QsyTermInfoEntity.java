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
 * @Description: 终端信息表
 * @author onlineGenerator
 * @date 2019-01-03 19:28:18
 * @version V1.0   
 *
 */
@Entity
@Table(name = "qsy_term_info", schema = "")
@SuppressWarnings("serial")
public class QsyTermInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**商户编号*/
	@Excel(name="商户编号",width=15)
	private java.lang.String merchCode;
	/**终端编号*/
	@Excel(name="终端编号",width=15)
	private java.lang.String termCode;
	/**终端产商*/
	@Excel(name="终端产商",width=15)
	private java.lang.String termManu;
	/**终端类型*/
	@Excel(name="终端类型",width=15)
	private java.lang.String termType;
	/**终端型号*/
	@Excel(name="终端型号",width=15)
	private java.lang.String termModel;
	/**终端密钥*/
	private java.lang.String secretKey;
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
	 *@return: java.lang.String  终端产商
	 */

	@Column(name ="TERM_MANU",nullable=true,length=64)
	public java.lang.String getTermManu(){
		return this.termManu;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端产商
	 */
	public void setTermManu(java.lang.String termManu){
		this.termManu = termManu;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  终端类型
	 */

	@Column(name ="TERM_TYPE",nullable=true,length=2)
	public java.lang.String getTermType(){
		return this.termType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端类型
	 */
	public void setTermType(java.lang.String termType){
		this.termType = termType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  终端型号
	 */

	@Column(name ="TERM_MODEL",nullable=true,length=50)
	public java.lang.String getTermModel(){
		return this.termModel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端型号
	 */
	public void setTermModel(java.lang.String termModel){
		this.termModel = termModel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  终端密钥
	 */

	@Column(name ="SECRET_KEY",nullable=true,length=50)
	public java.lang.String getSecretKey(){
		return this.secretKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终端密钥
	 */
	public void setSecretKey(java.lang.String secretKey){
		this.secretKey = secretKey;
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
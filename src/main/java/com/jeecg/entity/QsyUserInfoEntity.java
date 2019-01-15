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
 * @Description: 用户信息表
 * @author onlineGenerator
 * @date 2019-01-04 11:22:12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "qsy_user_info", schema = "")
@SuppressWarnings("serial")
public class QsyUserInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**登录账号*/
	@Excel(name="登录账号",width=15)
	private java.lang.String username;
	/**用户姓名*/
	@Excel(name="用户姓名",width=15)
	private java.lang.String realname;
	/**登录密码*/
	private java.lang.String password;
	/**手机号*/
	@Excel(name="手机号",width=15)
	private java.lang.String telephone;
	/**所属商户*/
	@Excel(name="所属商户",width=15)
	private java.lang.String merchCode;
	/**用户类型*/
	@Excel(name="用户类型",width=15)
	private java.lang.String userType;
	/**用户状态*/
	@Excel(name="用户状态",width=15)
	private java.lang.String userState;
	/**微信用户标识*/
	private java.lang.String openid;
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
	 *@return: java.lang.String  登录账号
	 */

	@Column(name ="USERNAME",nullable=true,length=50)
	public java.lang.String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登录账号
	 */
	public void setUsername(java.lang.String username){
		this.username = username;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户姓名
	 */

	@Column(name ="REALNAME",nullable=true,length=50)
	public java.lang.String getRealname(){
		return this.realname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户姓名
	 */
	public void setRealname(java.lang.String realname){
		this.realname = realname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登录密码
	 */

	@Column(name ="PASSWORD",nullable=true,length=32)
	public java.lang.String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登录密码
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号
	 */

	@Column(name ="TELEPHONE",nullable=true,length=30)
	public java.lang.String getTelephone(){
		return this.telephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号
	 */
	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属商户
	 */

	@Column(name ="MERCH_CODE",nullable=true,length=32)
	public java.lang.String getMerchCode(){
		return this.merchCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属商户
	 */
	public void setMerchCode(java.lang.String merchCode){
		this.merchCode = merchCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户类型
	 */

	@Column(name ="USER_TYPE",nullable=true,length=1)
	public java.lang.String getUserType(){
		return this.userType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户类型
	 */
	public void setUserType(java.lang.String userType){
		this.userType = userType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户状态
	 */

	@Column(name ="USER_STATE",nullable=true,length=1)
	public java.lang.String getUserState(){
		return this.userState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户状态
	 */
	public void setUserState(java.lang.String userState){
		this.userState = userState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信用户标识
	 */

	@Column(name ="OPENID",nullable=true,length=32)
	public java.lang.String getOpenid(){
		return this.openid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信用户标识
	 */
	public void setOpenid(java.lang.String openid){
		this.openid = openid;
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
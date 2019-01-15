package org.jeecgframework.web.system.pojo.base;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: ms_institution
 * @author onlineGenerator
 * @date 2018-07-16 14:42:12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ms_institution", schema = "")
@SuppressWarnings("serial")
public class MsInstitutionEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**instName*/
	@Excel(name="instName",width=15)
	private java.lang.String instName;
	/**instCode*/
	@Excel(name="instCode",width=15)
	private java.lang.String instCode;
	/**instType*/
	@Excel(name="instType",width=15)
	private java.lang.Integer instType;
	/**masterInstCode*/
	@Excel(name="masterInstCode",width=15)
	private java.lang.String masterInstCode;
	/**orgCode*/
	@Excel(name="orgCode",width=15)
	private java.lang.String orgCode;
	/**address*/
	@Excel(name="address",width=15)
	private java.lang.String address;
	/**addrCode*/
	@Excel(name="addrCode",width=15)
	private java.lang.String addrCode;
	/**connPeople*/
	@Excel(name="connPeople",width=15)
	private java.lang.String connPeople;
	/**tel*/
	@Excel(name="tel",width=15)
	private java.lang.String tel;
	/**email*/
	@Excel(name="email",width=15)
	private java.lang.String email;
	/**pic*/
	@Excel(name="pic",width=15)
	private java.lang.String pic;
	/**state*/
	@Excel(name="state",width=15)
	private java.lang.String state;
	/**stateDate*/
	@Excel(name="stateDate",width=15,format = "yyyy-MM-dd")
	private java.util.Date stateDate;
	/**createdDate*/
	@Excel(name="createdDate",width=15,format = "yyyy-MM-dd")
	private java.util.Date createdDate;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name ="ID",nullable=false,length=10)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  instName
	 */

	@Column(name ="INST_NAME",nullable=false,length=50)
	public java.lang.String getInstName(){
		return this.instName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  instName
	 */
	public void setInstName(java.lang.String instName){
		this.instName = instName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  instCode
	 */

	@Column(name ="INST_CODE",nullable=false,length=20)
	public java.lang.String getInstCode(){
		return this.instCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  instCode
	 */
	public void setInstCode(java.lang.String instCode){
		this.instCode = instCode;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  instType
	 */

	@Column(name ="INST_TYPE",nullable=false,length=10)
	public java.lang.Integer getInstType(){
		return this.instType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  instType
	 */
	public void setInstType(java.lang.Integer instType){
		this.instType = instType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  masterInstCode
	 */

	@Column(name ="MASTER_INST_CODE",nullable=true,length=20)
	public java.lang.String getMasterInstCode(){
		return this.masterInstCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  masterInstCode
	 */
	public void setMasterInstCode(java.lang.String masterInstCode){
		this.masterInstCode = masterInstCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  orgCode
	 */

	@Column(name ="ORG_CODE",nullable=true,length=20)
	public java.lang.String getOrgCode(){
		return this.orgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  orgCode
	 */
	public void setOrgCode(java.lang.String orgCode){
		this.orgCode = orgCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  address
	 */

	@Column(name ="ADDRESS",nullable=true,length=200)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  address
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  addrCode
	 */

	@Column(name ="ADDR_CODE",nullable=true,length=30)
	public java.lang.String getAddrCode(){
		return this.addrCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  addrCode
	 */
	public void setAddrCode(java.lang.String addrCode){
		this.addrCode = addrCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  connPeople
	 */

	@Column(name ="CONN_PEOPLE",nullable=true,length=30)
	public java.lang.String getConnPeople(){
		return this.connPeople;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  connPeople
	 */
	public void setConnPeople(java.lang.String connPeople){
		this.connPeople = connPeople;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  tel
	 */

	@Column(name ="TEL",nullable=true,length=20)
	public java.lang.String getTel(){
		return this.tel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  tel
	 */
	public void setTel(java.lang.String tel){
		this.tel = tel;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  email
	 */

	@Column(name ="EMAIL",nullable=true,length=100)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  email
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  pic
	 */

	@Column(name ="PIC",nullable=true,length=200)
	public java.lang.String getPic(){
		return this.pic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  pic
	 */
	public void setPic(java.lang.String pic){
		this.pic = pic;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  state
	 */

	@Column(name ="STATE",nullable=false,length=3)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  state
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  stateDate
	 */

	@Column(name ="STATE_DATE",nullable=false)
	public java.util.Date getStateDate(){
		return this.stateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  stateDate
	 */
	public void setStateDate(java.util.Date stateDate){
		this.stateDate = stateDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createdDate
	 */

	@Column(name ="CREATED_DATE",nullable=false)
	public java.util.Date getCreatedDate(){
		return this.createdDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createdDate
	 */
	public void setCreatedDate(java.util.Date createdDate){
		this.createdDate = createdDate;
	}
	
}

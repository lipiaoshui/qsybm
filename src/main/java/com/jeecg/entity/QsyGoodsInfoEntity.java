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
 * @Description: 商品信息表
 * @author onlineGenerator
 * @date 2019-01-08 15:30:12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "qsy_goods_info", schema = "")
@SuppressWarnings("serial")
public class QsyGoodsInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**商品条形码*/
	@Excel(name="商品条形码",width=15)
	private java.lang.String goodsBarcode;
	/**商品名称*/
	@Excel(name="商品名称",width=15)
	private java.lang.String goodsName;
	/**商品规格*/
	@Excel(name="商品规格",width=15)
	private java.lang.String goodsSpec;
	/**商品分类*/
	@Excel(name="商品分类",width=15)
	private java.lang.String goodsType;
	/**商品参考价*/
	@Excel(name="商品参考价",width=15)
	private long goodsPrice;
	/**商标品牌名称*/
	@Excel(name="商标品牌名称",width=15)
	private java.lang.String brandName;
	/**售卖简称*/
	@Excel(name="售卖简称",width=15)
	private java.lang.String sellShortName;
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
	 *@return: java.lang.String  商品条形码
	 */

	@Column(name ="GOODS_BARCODE",nullable=true,length=64)
	public java.lang.String getGoodsBarcode(){
		return this.goodsBarcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品条形码
	 */
	public void setGoodsBarcode(java.lang.String goodsBarcode){
		this.goodsBarcode = goodsBarcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品名称
	 */

	@Column(name ="GOODS_NAME",nullable=true,length=64)
	public java.lang.String getGoodsName(){
		return this.goodsName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品名称
	 */
	public void setGoodsName(java.lang.String goodsName){
		this.goodsName = goodsName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品规格
	 */

	@Column(name ="GOODS_SPEC",nullable=true,length=50)
	public java.lang.String getGoodsSpec(){
		return this.goodsSpec;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品规格
	 */
	public void setGoodsSpec(java.lang.String goodsSpec){
		this.goodsSpec = goodsSpec;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品分类
	 */

	@Column(name ="GOODS_TYPE",nullable=true,length=4)
	public java.lang.String getGoodsType(){
		return this.goodsType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品分类
	 */
	public void setGoodsType(java.lang.String goodsType){
		this.goodsType = goodsType;
	}
	/**
	 *方法: 取得long
	 *@return: long  商品参考价
	 */

	@Column(name ="GOODS_PRICE",nullable=true,length=12)
	public long getGoodsPrice(){
		return this.goodsPrice;
	}

	/**
	 *方法: 设置long
	 *@param: long  商品参考价
	 */
	public void setGoodsPrice(long goodsPrice){
		this.goodsPrice = goodsPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商标品牌名称
	 */

	@Column(name ="BRAND_NAME",nullable=true,length=64)
	public java.lang.String getBrandName(){
		return this.brandName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商标品牌名称
	 */
	public void setBrandName(java.lang.String brandName){
		this.brandName = brandName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  售卖简称
	 */

	@Column(name ="SELL_SHORT_NAME",nullable=true,length=50)
	public java.lang.String getSellShortName(){
		return this.sellShortName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  售卖简称
	 */
	public void setSellShortName(java.lang.String sellShortName){
		this.sellShortName = sellShortName;
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
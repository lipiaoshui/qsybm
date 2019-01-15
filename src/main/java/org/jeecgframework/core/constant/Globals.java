package org.jeecgframework.core.constant;

import org.jeecgframework.core.util.ResourceUtil;


/**  
* 项目名称：jeecg
* 类名称：Globals   
* 类描述：  全局变量定义
* 创建人： 张代浩      
* @version    
*
 */
public final class Globals {
	/**
	 * 保存用户到SESSION
	 */
	public static final String USER_SESSION="USER_SESSION";
	/**
	 * 人员类型
	 */
	public static final Short User_Normal=1;//正常
	public static final Short User_Forbidden=0;//禁用
	public static final Short User_ADMIN=-1;//超级管理员

	/**
	 * 用户类型：1、系统类型用户
	 */
	public static final String USER_TYPE_SYSTEM = "1";
	/**
	 * 用户类型：2、接口类型用户
	 *
	 */
	public static final String USER_TYPE_INTERFACE = "2";

	
	/**
	 * 逻辑删除标记
	 */
	/**
	 * 删除
	 */
	public static final Short Delete_Forbidden=1;
	/**
	 * 正常
	 */
	public static final Short Delete_Normal=0;
	
	/**
	 *日志级别定义
	 */
	public static final Short Log_Leavel_INFO=1;
	public static final Short Log_Leavel_WARRING=2;
	public static final Short Log_Leavel_ERROR=3;
	 /**
	  * 日志类型
	  */
	 public static final Short Log_Type_LOGIN=1; //登陆
	 public static final Short Log_Type_EXIT=2;  //退出
	 public static final Short Log_Type_INSERT=3; //插入
	 public static final Short Log_Type_DEL=4; //删除
	 public static final Short Log_Type_UPDATE=5; //更新
	 public static final Short Log_Type_UPLOAD=6; //上传
	 public static final Short Log_Type_OTHER=7; //其他
	 
	 
	 /**
	  * 词典分组定义
	  */
	 public static final String TypeGroup_Database="database";//数据表分类
	 
	 /**
	  * 权限等级
	  */
	 public static final Short Function_Leave_ONE=0;//一级权限
	 public static final Short Function_Leave_TWO=1;//二级权限
	 
	 /**
	  * 权限等级前缀
	  */
	 public static final String Function_Order_ONE="ofun";//一级权限
	 public static final String Function_Order_TWO="tfun";//二级权限
	 /**
	  * 权限类型
	  */
	 public static final Short Function_TYPE_PAGE=0;//菜单：菜单类型
	 public static final Short Function_TYPE_FROM=1;//菜单：权限类型(权限使用，不作为菜单首页加载)
	 /**
	  * 没有勾选的操作code
	  */
	 public static final String NOAUTO_OPERATIONCODES ="noauto_operationCodes";
	 /**
	  * 勾选了的操作code
	  */
	 public static final String OPERATIONCODES ="operationCodes";
	 
	 
	 /**
	  * 权限类型
	  */
	 public static final Short OPERATION_TYPE_HIDE = 0;//页面
	 public static final Short OPERATION_TYPE_DISABLED = 1;//表单/或者弹出
	 
	 
	 /**
	  * 数据权限 - 菜单数据规则集合
	  */
	 public static final String MENU_DATA_AUTHOR_RULES ="MENU_DATA_AUTHOR_RULES";
	 /**
	  * 数据权限 - 菜单数据规则sql
	  */
	 public static final String MENU_DATA_AUTHOR_RULE_SQL ="MENU_DATA_AUTHOR_RULE_SQL";
	 /**
	  * 新闻法规
	  */
	 public static final Short Document_NEW=0; //新建
	 public static final Short Document_PUBLICH=0; //发布
	 
	 /**
	  * 内部邮件系统
	  */
	 public static final String MAIL_STATUS_UNSEND ="00"; //草稿
	 public static final String MAIL_STATUS_SEND ="01"; //已发送
	 public static final String MAIL_STATUS_DEL ="02"; //删除   已发送的邮件不能真正删除，不然接收人就看不到邮件了。
	 public static final String MAILRECEIVER_STATUS_UNREAD ="00"; //未读
	 public static final String MAILRECEIVER_STATUS_READ ="01";//已读
	 /**工号编码*/
	 public static final String CODE_PREFIX_STAFF = "10"; // 工号编码
	 /**批次号*/
	 public static final String CODE_PREFIX_TASK = "8"; // 批次号
	 
	 /**
	  * 数据字典
	  * 
	  */
	 /**有效*/
	 public static final String ENABLE_FLAG_true = "1"; // 有效
	 /**无效*/
	 public static final String ENABLE_FLAG_false = "2"; // 无效
	 /**常规巡检*/
	 public static final String TASK_TYPE_ROUTINE= "01"; // 常规巡检
	 /**抽查巡检*/
	 public static final String TASK_TYPE_SPOT_CHECK= "02"; // 抽查巡检
	 /**营销巡检*/
	 public static final String TASK_TYPE_MARKETING= "03"; // 营销巡检
	 /**自定义*/
	 public static final String TASK_TYPE_CUSTOM= "04"; // 自定义
	 /**正常*/
	 public static final String RECORD_STATE_NORMAL= "1"; // 正常
	 /**删除*/
	 public static final String RECORD_STATE_DELETE= "2"; // 删除
	 
	 //机构类型accept_flag
	 /**监管机构*/
	 public static final String INST_TYPE_SUPERVISE= "01"; // 监管机构
	 /**清算组织*/
	 public static final String INST_TYPE_LIGUIDATION= "02"; // 清算组织
	 /**商业银行 */
	 public static final String INST_TYPE_BUSINESS= "03"; // 商业银行
	 /**支付机构 */
	 public static final String INST_TYPE_PAYMENT= "04"; // 支付机构
	 /**外包机构 */
	 public static final String INST_TYPE_OUTSOURSE= "05"; // 外包机构
	 /**其他机构*/
	 public static final String INST_TYPE_OUTHER= "99"; // 其他机构

	 //是否收单
	 /**有收单*/
	 public static final String ACCEPT_FLAG_YES= "1"; // 有收单
	 /**无收单*/
	 public static final String ACCEPT_FLAG_NO= "2"; // 无收单
	 
	 //机构状态
	 /**正常*/
	 public static final String INST_STATE_NORMAL= "1"; // 正常
	 /**注销*/
	 public static final String INST_STATE_DELETE= "2"; // 注销
	 
	 //巡检状态
	 /**已巡检*/
	 public static final String INSP_STAFF_YES= "2"; // 2-已巡检
	 /**未巡检*/
	 public static final String INSP_STAFF_NO= "1"; // 1-未巡检	 
	 
	 //授权方式
	 /**整体授权*/
	 public static final String ACRT_TYPE_ALL= "1"; // 1-整体授权
	 /**部分授权*/
	 public static final String ACRT_TYPE_PART= "2"; // 2-部分授权
	 //审核标志qualified_flag
	 /**审核通过*/
	 public static final String QUALIFIED_FLAG_YES= "2"; // 
	 /**审核不通过*/
	 public static final String QUALIFIED_FLAG_NO= "1"; // 	 	 
	 /**后台图片上传子路径 **/
	 public static final String BACK_PICTURE = "prop";
	 /**银联上传模板父级目录 **/
	 public static final String  YINLIAN= "yinlian";
	 /**银联上传模板子级目录 **/
	 public static final String  TEMPPLATE= "template";
	 /**机构上传数据子级目录 **/
	 public static final String  INST= "inst";
	 /**文件状态已报送**/
	 public static final String  FILE_STATUS_SUM= "1";
	 /**文件状态未报送**/
	 public static final String  FILE_STATUS_NOSUM= "2";
	 /**报送周期每日**/
	 public static final String  DELIVERY_CYCLE_DAY= "01";
	 /**报送周期每月**/
	 public static final String  DELIVERY_CYCLE_MATH= "02";

	 /**数据来源界面录入**/
	 public static final String  SOURCE_WINDOW= "01";
	 /**数据来源巡检同步**/
	 public static final String  SOURCE_INSPET= "02";
	 /**数据来源界面银行卡系统**/
	 public static final String  SOURCE_BANK= "03";
	 /**数据来源银联**/
	 public static final String  SOURCE_YINLIAN= "04";
	 /**Excel最大条数常量**/
	 public static final int EXCEL_MAX_SIZE = 65536;
	 
	 /**商户巡检图片下载*/
	 public static final String INSP_MERCH_PICTURE_DOWNLOAD = "01";
	 /**门店巡检图片下载*/
	 public static final String INSP_SHOP_PICTURE_DOWNLOAD = "02";
	 /**终端巡检图片下载*/
	 public static final String INSP_TERM_PICTURE_DOWNLOAD = "03";
	 /**条码巡检图片下载*/
	 public static final String INSP_BARCODE_PICTURE_DOWNLOAD = "04";
	 
	 /**工作状态：正常*/
	 public static final String WORK_STATUS_NORMAL = "1";
	 /**工作状态：取消*/
	 public static final String WORK_STATUS_CANCEL = "2";
	 
	 /**下载数量限制*/
	 public static final int DOWNLOAD_LIMIT = 100;
	 
	 // 处理状态
	 /**未处理*/
	 public static final String HANDLE_STATUS_1 = "1";
	 /**处理中*/
	 public static final String HANDLE_STATUS_2 = "2";
	 /**处理成功*/
	 public static final String HANDLE_STATUS_3 = "3";
	 /**处理失败*/
	 public static final String HANDLE_STATUS_4 = "4";
	 
	 /**
	  * 配置系统是否开启按钮权限控制
	  */
	 public static boolean BUTTON_AUTHORITY_CHECK = false;
	 static{
		 String button_authority_jeecg = ResourceUtil.getSessionattachmenttitle("button.authority.jeecg");
		 if("true".equals(button_authority_jeecg)){
			 BUTTON_AUTHORITY_CHECK = true;
		 }
	 }
}

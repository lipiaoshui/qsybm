package com.jeecg.controller;
import com.jeecg.entity.QsyOrderInfoEntity;
import com.jeecg.service.QsyOrderInfoServiceI;
import com.jeecg.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: 订单信息表
 * @author onlineGenerator
 * @date 2019-01-09 14:59:28
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/qsyOrderInfoController")
public class QsyOrderInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QsyOrderInfoController.class);

	@Autowired
	private QsyOrderInfoServiceI qsyOrderInfoService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private JdbcDao jdbcDao;

	/**
	 * 订单信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/qsyorderinfo/qsyOrderInfoList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(QsyOrderInfoEntity qsyOrderInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		// 订单号
		String orderNo = qsyOrderInfo.getOrderNo();
		// 订单成交时间
		String createTimeBegin = request.getParameter("createTime_begin");
		String createTimeEnd = request.getParameter("createTime_end");
		
		CriteriaQuery cq = new CriteriaQuery(QsyOrderInfoEntity.class, dataGrid);
		if(!StringUtil.isEmpty(orderNo)){
			qsyOrderInfo.setOrderNo("*"+ orderNo +"*");
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyOrderInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
			if(!StringUtil.isEmpty(createTimeBegin) && !StringUtil.isEmpty(createTimeEnd)){
				createTimeEnd = createTimeEnd + " 23:59:59";
				DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				cq.between("createTime", format1.parse(createTimeBegin), format2.parse(createTimeEnd));
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.qsyOrderInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
		
//		// 商户编号
//		String merchCode = qsyOrderInfo.getMerchCode();
//		
//		
//		// 支付方式
//		String payType = qsyOrderInfo.getPayType();
//		
//		// 订单状态
//		String orderState = qsyOrderInfo.getOrderState();
		
		
//		StringBuilder countSql = new StringBuilder("select count(1) from (");
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select id,order_no as orderNo,merch_code as merchCode,goods_desc as goodsDesc,term_code as termCode,");
//		sql.append(" pay_type as pay_type,channel_code as channelCode,channel_serial_no as channelSerialNo,author_code as authorCode,");
//		sql.append(" order_state as orderState,order_desc as orderDesc,order_time as orderTime,trade_time as tradeTime,");
//		sql.append(" create_time as createTime,create_by as createBy,update_time as updateTime,update_by as updateBy");
//		sql.append(" from qsy_order_info where 1=1 ");
//		if(!StringUtil.isEmpty(merchCode)){
//			sql.append(" and merch_code = '" + merchCode +"'");
//		}
//		if(!StringUtil.isEmpty(orderNo)){
//			sql.append(" and order_no like '%" + orderNo +"%'");
//		}
//		if(!StringUtil.isEmpty(payType)){
//			sql.append(" and pay_type = '" + payType +"'");
//		}
//		if(!StringUtil.isEmpty(orderState)){
//			sql.append(" and order_state = '"+ orderState +"'");
//		}
//		if(!StringUtil.isEmpty(createTimeBegin) && !StringUtil.isEmpty(createTimeEnd)){
//			createTimeEnd = createTimeEnd +" 23:59:59";
//			sql.append(" and create_time between '"+ createTimeBegin +"' and '"+ createTimeEnd +"'");
//		}
//		sql.append(" order by create_time desc ");
//		countSql.append(sql.toString()).append(") as t ");
//		String total = systemService.getCountForJdbcParam(countSql.toString()) + "";
//		sql.append(" limit ?,?");
//		List<Map<String,Object>> resultList = jdbcDao.queryForList(sql.toString(), (dataGrid.getPage()-1)*dataGrid.getRows(), dataGrid.getRows());
//		dataGrid.setTotal(Integer.valueOf(total));
//		dataGrid.setResults(resultList);
//		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除订单信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QsyOrderInfoEntity qsyOrderInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		qsyOrderInfo = systemService.getEntity(QsyOrderInfoEntity.class, qsyOrderInfo.getId());
		message = "订单信息表删除成功";
		try{
			qsyOrderInfoService.delete(qsyOrderInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除订单信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单信息表删除成功";
		try{
			for(String id:ids.split(",")){
				QsyOrderInfoEntity qsyOrderInfo = systemService.getEntity(QsyOrderInfoEntity.class, 
				id
				);
				qsyOrderInfoService.delete(qsyOrderInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "订单信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加订单信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(QsyOrderInfoEntity qsyOrderInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单信息表添加成功";
		try{
			qsyOrderInfoService.save(qsyOrderInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新订单信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(QsyOrderInfoEntity qsyOrderInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单信息表更新成功";
		QsyOrderInfoEntity t = qsyOrderInfoService.get(QsyOrderInfoEntity.class, qsyOrderInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(qsyOrderInfo, t);
			qsyOrderInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "订单信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 订单信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(QsyOrderInfoEntity qsyOrderInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyOrderInfo.getId())) {
			qsyOrderInfo = qsyOrderInfoService.getEntity(QsyOrderInfoEntity.class, qsyOrderInfo.getId());
			req.setAttribute("qsyOrderInfo", qsyOrderInfo);
		}
		return new ModelAndView("com/jeecg/qsyorderinfo/qsyOrderInfo-add");
	}
	/**
	 * 订单信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QsyOrderInfoEntity qsyOrderInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyOrderInfo.getId())) {
			qsyOrderInfo = qsyOrderInfoService.getEntity(QsyOrderInfoEntity.class, qsyOrderInfo.getId());
			req.setAttribute("qsyOrderInfo", qsyOrderInfo);
		}
		return new ModelAndView("com/jeecg/qsyorderinfo/qsyOrderInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","qsyOrderInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(QsyOrderInfoEntity qsyOrderInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(QsyOrderInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyOrderInfo, request.getParameterMap());
		List<QsyOrderInfoEntity> qsyOrderInfos = this.qsyOrderInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"订单信息表");
		modelMap.put(NormalExcelConstants.CLASS,QsyOrderInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("订单信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,qsyOrderInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(QsyOrderInfoEntity qsyOrderInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"订单信息表");
    	modelMap.put(NormalExcelConstants.CLASS,QsyOrderInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("订单信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<QsyOrderInfoEntity> listQsyOrderInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),QsyOrderInfoEntity.class,params);
				for (QsyOrderInfoEntity qsyOrderInfo : listQsyOrderInfoEntitys) {
					qsyOrderInfoService.save(qsyOrderInfo);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	 /**
     * 查看该任务分配给那些机构
     */
	@RequestMapping(params = "selectOrderDetailDatagrid")
	public void selectOrderDetailDatagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String orderNo = request.getParameter("orderNo");
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT id,order_no as orderNo,goods_name as goodsName,goods_barcode as goodsBarcode,good_price as goodPrice ");
		sb.append(" from qsy_order_detail_info where order_no = '"+ orderNo +"'");
		List<Map<String, Object>> resultList =  systemService.findForJdbc(sb.toString(),dataGrid.getPage(),dataGrid.getRows());
		dataGrid.setResults(resultList);
		dataGrid.setTotal(resultList.size());
		TagUtil.datagrid(response, dataGrid);		
	} 
	
}

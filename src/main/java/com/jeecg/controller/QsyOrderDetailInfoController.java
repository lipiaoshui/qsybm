package com.jeecg.controller;
import com.jeecg.entity.QsyOrderDetailInfoEntity;
import com.jeecg.service.QsyOrderDetailInfoServiceI;

import java.util.ArrayList;
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

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: 订单明细表
 * @author onlineGenerator
 * @date 2019-01-09 14:38:07
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/qsyOrderDetailInfoController")
public class QsyOrderDetailInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QsyOrderDetailInfoController.class);

	@Autowired
	private QsyOrderDetailInfoServiceI qsyOrderDetailInfoService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private JdbcDao jdbcDao;

	/**
	 * 订单明细表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/qsyorderdetailinfo/qsyOrderDetailInfoList");
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
	public void datagrid(QsyOrderDetailInfoEntity qsyOrderDetailInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

		// 订单开始时间及结束时间
		String timeBegin = request.getParameter("time_begin");
		String timeEnd = request.getParameter("time_end");
		
		// 商户编号
		String merchCode = request.getParameter("merchCode");
		
		// 商户订单号
		String orderNo = qsyOrderDetailInfo.getOrderNo();
		
		// 商品名称
		String goodsName = qsyOrderDetailInfo.getGoodsName();
		
//		CriteriaQuery cq = new CriteriaQuery(QsyOrderDetailInfoEntity.class, dataGrid);
//		//查询条件组装器
//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyOrderDetailInfo, request.getParameterMap());
//		try{
//			//自定义追加查询条件
//			if(!StringUtil.isEmpty(timeBegin) && !StringUtil.isEmpty(timeEnd)){
//				cq.between("createTime", timeBegin, timeEnd);
//			}
//		}catch (Exception e) {
//			throw new BusinessException(e.getMessage());
//		}
//		cq.add();
//		this.qsyOrderDetailInfoService.getDataGridReturn(cq, true);
//		TagUtil.datagrid(response, dataGrid);
		
		StringBuilder countSql = new StringBuilder("select count(1) from (");
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.id,a.order_no as orderNo,a.goods_name as goodsName,goods_barcode as goodsBarcode,a.good_price as goodPrice,");
		sql.append(" b.merch_code as merchCode,a.create_time as createTime,a.create_by as createBy,a.update_time as updateTime,a.update_by as updateBy");
		sql.append(" from qsy_order_detail_info a join qsy_order_info b on a.order_no=b.order_no where 1=1 ");
		if(!StringUtil.isEmpty(merchCode)){
			sql.append(" and b.merch_code = '" + merchCode +"'");
		}
		if(!StringUtil.isEmpty(orderNo)){
			sql.append(" and a.order_no = '"+ orderNo +"'");
		}
		if(!StringUtil.isEmpty(goodsName)){
			sql.append(" and a.goods_name like '%"+ goodsName +"%'");
		}
		if(!StringUtil.isEmpty(timeBegin) && !StringUtil.isEmpty(timeEnd)){
			timeEnd = timeEnd +" 23:59:59";
			sql.append(" and a.create_time between '"+ timeBegin +"' and '"+ timeEnd +"'");
		}
		sql.append(" order by a.create_time desc ");
		countSql.append(sql.toString()).append(") as t ");
		String total = systemService.getCountForJdbcParam(countSql.toString()) + "";
		sql.append(" limit ?,?");
		List<Map<String,Object>> resultList = jdbcDao.queryForList(sql.toString(), (dataGrid.getPage()-1)*dataGrid.getRows(), dataGrid.getRows());
		dataGrid.setTotal(Integer.valueOf(total));
		dataGrid.setResults(resultList);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除订单明细表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QsyOrderDetailInfoEntity qsyOrderDetailInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		qsyOrderDetailInfo = systemService.getEntity(QsyOrderDetailInfoEntity.class, qsyOrderDetailInfo.getId());
		message = "订单明细表删除成功";
		try{
			qsyOrderDetailInfoService.delete(qsyOrderDetailInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单明细表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除订单明细表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单明细表删除成功";
		try{
			for(String id:ids.split(",")){
				QsyOrderDetailInfoEntity qsyOrderDetailInfo = systemService.getEntity(QsyOrderDetailInfoEntity.class, 
				id
				);
				qsyOrderDetailInfoService.delete(qsyOrderDetailInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "订单明细表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加订单明细表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(QsyOrderDetailInfoEntity qsyOrderDetailInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单明细表添加成功";
		try{
			qsyOrderDetailInfoService.save(qsyOrderDetailInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订单明细表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新订单明细表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(QsyOrderDetailInfoEntity qsyOrderDetailInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订单明细表更新成功";
		QsyOrderDetailInfoEntity t = qsyOrderDetailInfoService.get(QsyOrderDetailInfoEntity.class, qsyOrderDetailInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(qsyOrderDetailInfo, t);
			qsyOrderDetailInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "订单明细表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 订单明细表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(QsyOrderDetailInfoEntity qsyOrderDetailInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyOrderDetailInfo.getId())) {
			qsyOrderDetailInfo = qsyOrderDetailInfoService.getEntity(QsyOrderDetailInfoEntity.class, qsyOrderDetailInfo.getId());
			req.setAttribute("qsyOrderDetailInfo", qsyOrderDetailInfo);
		}
		return new ModelAndView("com/jeecg/qsyorderdetailinfo/qsyOrderDetailInfo-add");
	}
	/**
	 * 订单明细表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QsyOrderDetailInfoEntity qsyOrderDetailInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyOrderDetailInfo.getId())) {
			qsyOrderDetailInfo = qsyOrderDetailInfoService.getEntity(QsyOrderDetailInfoEntity.class, qsyOrderDetailInfo.getId());
			req.setAttribute("qsyOrderDetailInfo", qsyOrderDetailInfo);
		}
		return new ModelAndView("com/jeecg/qsyorderdetailinfo/qsyOrderDetailInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","qsyOrderDetailInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(QsyOrderDetailInfoEntity qsyOrderDetailInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(QsyOrderDetailInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyOrderDetailInfo, request.getParameterMap());
		List<QsyOrderDetailInfoEntity> qsyOrderDetailInfos = this.qsyOrderDetailInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"订单明细表");
		modelMap.put(NormalExcelConstants.CLASS,QsyOrderDetailInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("订单明细表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,qsyOrderDetailInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(QsyOrderDetailInfoEntity qsyOrderDetailInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"订单明细表");
    	modelMap.put(NormalExcelConstants.CLASS,QsyOrderDetailInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("订单明细表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<QsyOrderDetailInfoEntity> listQsyOrderDetailInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),QsyOrderDetailInfoEntity.class,params);
				for (QsyOrderDetailInfoEntity qsyOrderDetailInfo : listQsyOrderDetailInfoEntitys) {
					qsyOrderDetailInfoService.save(qsyOrderDetailInfo);
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
	
	
}

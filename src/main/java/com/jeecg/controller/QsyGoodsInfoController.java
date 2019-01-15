package com.jeecg.controller;
import com.jeecg.entity.QsyGoodsInfoEntity;
import com.jeecg.service.QsyGoodsInfoServiceI;

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
 * @Description: 商品信息表
 * @author onlineGenerator
 * @date 2019-01-08 15:30:12
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/qsyGoodsInfoController")
public class QsyGoodsInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QsyGoodsInfoController.class);

	@Autowired
	private QsyGoodsInfoServiceI qsyGoodsInfoService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private JdbcDao jdbcDao;
	
	


	/**
	 * 商品信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/qsygoodsinfo/qsyGoodsInfoList");
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
	public void datagrid(QsyGoodsInfoEntity qsyGoodsInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(QsyGoodsInfoEntity.class, dataGrid);
		if(!StringUtil.isEmpty(qsyGoodsInfo.getGoodsName())){
			qsyGoodsInfo.setGoodsName("*" + qsyGoodsInfo.getGoodsName() + "*");
		}
		if(!StringUtil.isEmpty(qsyGoodsInfo.getGoodsBarcode())){
			qsyGoodsInfo.setGoodsBarcode("*" + qsyGoodsInfo.getGoodsBarcode() + "*");
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyGoodsInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.qsyGoodsInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
		
//		String goodsName = qsyGoodsInfo.getGoodsName();
//		String goodsBarcode = qsyGoodsInfo.getGoodsBarcode();
//		String goodsType = qsyGoodsInfo.getGoodsType();
//		StringBuilder countSql = new StringBuilder("select count(1) from (");
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select id,goods_barcode as goodsBarcode,goods_name as goodsName,goods_spec as goodsSpec,goods_type as goodsType,");
//		sql.append(" goods_refer_price/100 as goodsReferPrice,brand_name as brandName,sell_short_name as sellShortName,");
//		sql.append(" create_time as createTime,create_by as createBy,update_time as updateTime,update_by as updateBy");
//		sql.append(" from qsy_goods_info where 1=1 ");
//		if(!StringUtil.isEmpty(goodsName)){
//			sql.append(" and goods_name like '%" + goodsName +"%'");
//		}
//		if(!StringUtil.isEmpty(goodsBarcode)){
//			sql.append(" and goods_barcode like '%" + goodsBarcode +"%'");
//		}
//		if(!StringUtil.isEmpty(goodsType)){
//			sql.append(" and goods_type = '" + goodsType +"'");
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
	 * 删除商品信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QsyGoodsInfoEntity qsyGoodsInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		qsyGoodsInfo = systemService.getEntity(QsyGoodsInfoEntity.class, qsyGoodsInfo.getId());
		message = "商品信息表删除成功";
		try{
			qsyGoodsInfoService.delete(qsyGoodsInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除商品信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商品信息表删除成功";
		try{
			for(String id:ids.split(",")){
				QsyGoodsInfoEntity qsyGoodsInfo = systemService.getEntity(QsyGoodsInfoEntity.class, id);
				qsyGoodsInfoService.delete(qsyGoodsInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商品信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商品信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(QsyGoodsInfoEntity qsyGoodsInfo, String goodPrice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商品信息表添加成功";
		try{
			qsyGoodsInfo.setSellShortName(qsyGoodsInfo.getGoodsName()+" "+qsyGoodsInfo.getGoodsSpec());
			Number num = Float.parseFloat(goodPrice)*100;
	        int oamount = num.intValue();
	        long goodsPrice = Long.valueOf(oamount);
			qsyGoodsInfo.setGoodsPrice(goodsPrice);
			qsyGoodsInfoService.save(qsyGoodsInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商品信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新商品信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(QsyGoodsInfoEntity qsyGoodsInfo, String goodPrice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商品信息表更新成功";
		QsyGoodsInfoEntity t = qsyGoodsInfoService.get(QsyGoodsInfoEntity.class, qsyGoodsInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(qsyGoodsInfo, t);
			Number num = Float.parseFloat(goodPrice)*100;
	        int oamount = num.intValue();
	        long goodsPrice = Long.valueOf(oamount);
			t.setGoodsPrice(goodsPrice);
			qsyGoodsInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "商品信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 商品信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(QsyGoodsInfoEntity qsyGoodsInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyGoodsInfo.getId())) {
			qsyGoodsInfo = qsyGoodsInfoService.getEntity(QsyGoodsInfoEntity.class, qsyGoodsInfo.getId());
			req.setAttribute("qsyGoodsInfo", qsyGoodsInfo);
		}
		return new ModelAndView("com/jeecg/qsygoodsinfo/qsyGoodsInfo-add");
	}
	/**
	 * 商品信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QsyGoodsInfoEntity qsyGoodsInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyGoodsInfo.getId())) {
			qsyGoodsInfo = qsyGoodsInfoService.getEntity(QsyGoodsInfoEntity.class, qsyGoodsInfo.getId());
			qsyGoodsInfo.setGoodsPrice(qsyGoodsInfo.getGoodsPrice()/100);
			req.setAttribute("qsyGoodsInfo", qsyGoodsInfo);
		}
		return new ModelAndView("com/jeecg/qsygoodsinfo/qsyGoodsInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","qsyGoodsInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(QsyGoodsInfoEntity qsyGoodsInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(QsyGoodsInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyGoodsInfo, request.getParameterMap());
		List<QsyGoodsInfoEntity> qsyGoodsInfos = this.qsyGoodsInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"商品信息表");
		modelMap.put(NormalExcelConstants.CLASS,QsyGoodsInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商品信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,qsyGoodsInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(QsyGoodsInfoEntity qsyGoodsInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"商品信息表");
    	modelMap.put(NormalExcelConstants.CLASS,QsyGoodsInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商品信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<QsyGoodsInfoEntity> listQsyGoodsInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),QsyGoodsInfoEntity.class,params);
				for (QsyGoodsInfoEntity qsyGoodsInfo : listQsyGoodsInfoEntitys) {
					qsyGoodsInfoService.save(qsyGoodsInfo);
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

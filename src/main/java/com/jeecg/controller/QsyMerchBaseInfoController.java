package com.jeecg.controller;
import com.jeecg.entity.QsyMerchBaseInfoEntity;
import com.jeecg.service.QsyMerchBaseInfoServiceI;
import com.jeecg.utils.DateUtils;
import com.jeecg.utils.RandomUtils;

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
 * @Description: 商户基本信息
 * @author fuwenjie
 * @date 2018-12-28 17:05:22
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/qsyMerchBaseInfoController")
public class QsyMerchBaseInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QsyMerchBaseInfoController.class);

	@Autowired
	private QsyMerchBaseInfoServiceI qsyMerchBaseInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private JdbcDao jdbcDao;
	


	/**
	 * 商户基本信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/test");
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
	public void datagrid(QsyMerchBaseInfoEntity qsyMerchBaseInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(QsyMerchBaseInfoEntity.class, dataGrid);
		if(!StringUtil.isEmpty(qsyMerchBaseInfo.getMerchName())){
			qsyMerchBaseInfo.setMerchName("*" + qsyMerchBaseInfo.getMerchName() + "*");
		}
		if(!StringUtil.isEmpty(qsyMerchBaseInfo.getMerchCode())){
			qsyMerchBaseInfo.setMerchCode("*" + qsyMerchBaseInfo.getMerchCode() + "*");
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyMerchBaseInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.qsyMerchBaseInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除商户基本信息qsy_merch_base_info
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QsyMerchBaseInfoEntity qsyMerchBaseInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		qsyMerchBaseInfo = systemService.get(QsyMerchBaseInfoEntity.class, qsyMerchBaseInfo.getId());
		message = "商户基本信息删除成功";
		try{
			qsyMerchBaseInfoService.delete(qsyMerchBaseInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商户基本信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除商户基本信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商户基本信息删除成功";
		try{
			for(String id:ids.split(",")){
				QsyMerchBaseInfoEntity qsyMerchBaseInfo = systemService.getEntity(QsyMerchBaseInfoEntity.class,id);
				qsyMerchBaseInfoService.delete(qsyMerchBaseInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商户基本信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商户基本信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(QsyMerchBaseInfoEntity qsyMerchBaseInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String id = RandomUtils.getUUID();
		// 商户编号
		StringBuilder merchCode = new StringBuilder("SH").append(DateUtils.getCurrentDate("yyMMdd"));
		String sql = " select cast(SUBSTRING(MAX(merch_code),3,length(merch_code))+1 as char) as merchCode from qsy_merch_base_info where merch_code like '" + merchCode + "%' ";
		Map<String,Object> merchCodeMap = jdbcDao.queryForMap(sql);
		if(StringUtil.isEmpty((String)merchCodeMap.get("merchCode"))){
			merchCode.append("00001");
		}else{
			merchCode = new StringBuilder("SH").append((String)merchCodeMap.get("merchCode"));
		}
		message = "商户基本信息添加成功";
		try{
			qsyMerchBaseInfo.setId(id);
			qsyMerchBaseInfo.setMerchCode(merchCode.toString());
			qsyMerchBaseInfoService.save(qsyMerchBaseInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商户基本信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新商户基本信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(QsyMerchBaseInfoEntity qsyMerchBaseInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商户基本信息更新成功";
		QsyMerchBaseInfoEntity t = qsyMerchBaseInfoService.get(QsyMerchBaseInfoEntity.class, qsyMerchBaseInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(qsyMerchBaseInfo, t);
			systemService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "商户基本信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 商户基本信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(QsyMerchBaseInfoEntity qsyMerchBaseInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyMerchBaseInfo.getId())) {
			qsyMerchBaseInfo = qsyMerchBaseInfoService.getEntity(QsyMerchBaseInfoEntity.class, qsyMerchBaseInfo.getId());
			req.setAttribute("qsyMerchBaseInfo", qsyMerchBaseInfo);
		}
		return new ModelAndView("com/jeecg/merchbaseinfo/qsyMerchBaseInfo-add");
	}
	/**
	 * 商户基本信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QsyMerchBaseInfoEntity qsyMerchBaseInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyMerchBaseInfo.getId())) {
			qsyMerchBaseInfo = qsyMerchBaseInfoService.getEntity(QsyMerchBaseInfoEntity.class, qsyMerchBaseInfo.getId());
			req.setAttribute("qsyMerchBaseInfo", qsyMerchBaseInfo);
		}
		return new ModelAndView("com/jeecg/merchbaseinfo/qsyMerchBaseInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","qsyMerchBaseInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(QsyMerchBaseInfoEntity qsyMerchBaseInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(QsyMerchBaseInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyMerchBaseInfo, request.getParameterMap());
		List<QsyMerchBaseInfoEntity> qsyMerchBaseInfos = this.qsyMerchBaseInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"商户入网管理");
		modelMap.put(NormalExcelConstants.CLASS,QsyMerchBaseInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商户基本信息列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,qsyMerchBaseInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(QsyMerchBaseInfoEntity qsyMerchBaseInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"商户入网管理");
    	modelMap.put(NormalExcelConstants.CLASS,QsyMerchBaseInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商户基本信息列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<QsyMerchBaseInfoEntity> listQsyMerchBaseInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),QsyMerchBaseInfoEntity.class,params);
				for (QsyMerchBaseInfoEntity qsyMerchBaseInfo : listQsyMerchBaseInfoEntitys) {
					qsyMerchBaseInfoService.save(qsyMerchBaseInfo);
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

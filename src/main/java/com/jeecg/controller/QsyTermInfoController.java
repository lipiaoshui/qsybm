package com.jeecg.controller;
import com.jeecg.entity.QsyTermInfoEntity;
import com.jeecg.service.QsyTermInfoServiceI;
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
 * @Description: 终端信息表
 * @author onlineGenerator
 * @date 2019-01-03 19:28:18
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/qsyTermInfoController")
public class QsyTermInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QsyTermInfoController.class);

	@Autowired
	private QsyTermInfoServiceI qsyTermInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private JdbcDao jdbcDao;
	


	/**
	 * 终端信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/qsyterminfo/qsyTermInfoList");
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
	public void datagrid(QsyTermInfoEntity qsyTermInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(QsyTermInfoEntity.class, dataGrid);
		if(!StringUtil.isEmpty(qsyTermInfo.getTermCode())){
			qsyTermInfo.setTermCode("*" + qsyTermInfo.getTermCode() + "*");
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyTermInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.qsyTermInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
//		StringBuilder sql = new StringBuilder();
//		// 渠道代码、渠道名称、渠道方商户编号、系统商户编号、系统商户名称
//		sql.append(" select a.id as id,a.channel_code as channelCode,b.channel_name as channelName,a.merch_code as merchCode,c.merch_name as merchName, ");
//		sql.append(" a.channel_merch_code as channelMerchCode,a.channel_code as channelCodeApp,c.merch_name as merchNameApp");
//		sql.append(" from qsy_channel_merch_info a join qsy_channel_info b on a.channel_code=b.channel_code join qsy_merch_base_info c on a.merch_code=c.merch_code where 1=1 ");
//		if(!StringUtil.isEmpty(qsyTermInfo.getTermCode())){
//			sql.append(" and a.channel_code = '"+ qsyTermInfo.getTermCode() +"'");
//		}
//		if(!StringUtil.isEmpty(merchName)){
//			sql.append(" and c.merch_name = '"+ merchName +"'");
//		}
//		sql.append(" order by a.create_time desc limit ?,?");
//		List<Map<String, Object>> resultList = jdbcDao.queryForList(sql.toString(), (dataGrid.getPage()-1)*dataGrid.getRows(), dataGrid.getRows());
//		dataGrid.setTotal(resultList.size());
//		dataGrid.setResults(resultList);
//		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除终端信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QsyTermInfoEntity qsyTermInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		qsyTermInfo = systemService.getEntity(QsyTermInfoEntity.class, qsyTermInfo.getId());
		message = "终端信息表删除成功";
		try{
			qsyTermInfoService.delete(qsyTermInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "终端信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除终端信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "终端信息表删除成功";
		try{
			for(String id:ids.split(",")){
				QsyTermInfoEntity qsyTermInfo = systemService.getEntity(QsyTermInfoEntity.class, 
				id
				);
				qsyTermInfoService.delete(qsyTermInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "终端信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加终端信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(QsyTermInfoEntity qsyTermInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "终端信息表添加成功";
		// 密钥
		String secretKey = RandomUtils.getUUID();
		// 终端编号
		StringBuilder termCode = new StringBuilder("ZD").append(DateUtils.getCurrentDate("yyMMdd"));
		String sql = " select cast(SUBSTRING(MAX(term_code),3,length(term_code))+1 as char) as termCode from qsy_term_info where term_code like '" + termCode + "%' ";
		Map<String,Object> termCodeMap = jdbcDao.queryForMap(sql);
		if(StringUtil.isEmpty((String)termCodeMap.get("termCode"))){
			termCode.append("00001");
		}else{
			termCode = new StringBuilder("ZD").append((String)termCodeMap.get("termCode"));
		}
		try{
			qsyTermInfo.setTermCode(termCode.toString());
			qsyTermInfo.setSecretKey(secretKey);
			qsyTermInfoService.save(qsyTermInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "终端信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新终端信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(QsyTermInfoEntity qsyTermInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "终端信息表更新成功";
		QsyTermInfoEntity t = qsyTermInfoService.get(QsyTermInfoEntity.class, qsyTermInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(qsyTermInfo, t);
			qsyTermInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "终端信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 终端信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(QsyTermInfoEntity qsyTermInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyTermInfo.getId())) {
			qsyTermInfo = qsyTermInfoService.getEntity(QsyTermInfoEntity.class, qsyTermInfo.getId());
			req.setAttribute("qsyTermInfo", qsyTermInfo);
		}
		return new ModelAndView("com/jeecg/qsyterminfo/qsyTermInfo-add");
	}
	/**
	 * 终端信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QsyTermInfoEntity qsyTermInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyTermInfo.getId())) {
			qsyTermInfo = qsyTermInfoService.getEntity(QsyTermInfoEntity.class, qsyTermInfo.getId());
			req.setAttribute("qsyTermInfo", qsyTermInfo);
		}
		return new ModelAndView("com/jeecg/qsyterminfo/qsyTermInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","qsyTermInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(QsyTermInfoEntity qsyTermInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(QsyTermInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyTermInfo, request.getParameterMap());
		List<QsyTermInfoEntity> qsyTermInfos = this.qsyTermInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"终端信息表");
		modelMap.put(NormalExcelConstants.CLASS,QsyTermInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("终端信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,qsyTermInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(QsyTermInfoEntity qsyTermInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"终端信息表");
    	modelMap.put(NormalExcelConstants.CLASS,QsyTermInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("终端信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<QsyTermInfoEntity> listQsyTermInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),QsyTermInfoEntity.class,params);
				for (QsyTermInfoEntity qsyTermInfo : listQsyTermInfoEntitys) {
					qsyTermInfoService.save(qsyTermInfo);
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

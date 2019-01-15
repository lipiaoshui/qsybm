package com.jeecg.controller;
import com.jeecg.entity.QsyUserInfoEntity;
import com.jeecg.service.QsyUserInfoServiceI;

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
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;

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
import org.jeecgframework.core.util.IpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: 用户信息表
 * @author onlineGenerator
 * @date 2019-01-04 11:22:12
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/qsyUserInfoController")
public class QsyUserInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QsyUserInfoController.class);

	@Autowired
	private QsyUserInfoServiceI qsyUserInfoService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 用户信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/qsyuserinfo/qsyUserInfoList");
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
	public void datagrid(QsyUserInfoEntity qsyUserInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(QsyUserInfoEntity.class, dataGrid);
		if(!StringUtil.isEmpty(qsyUserInfo.getRealname())){
			qsyUserInfo.setRealname("*" + qsyUserInfo.getRealname() + "*");
		}
		if(!StringUtil.isEmpty(qsyUserInfo.getUsername())){
			qsyUserInfo.setUsername("*" + qsyUserInfo.getUsername() + "*");
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyUserInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.qsyUserInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除用户信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QsyUserInfoEntity qsyUserInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		qsyUserInfo = systemService.getEntity(QsyUserInfoEntity.class, qsyUserInfo.getId());
		message = "用户信息表删除成功";
		try{
			qsyUserInfoService.delete(qsyUserInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除用户信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户信息表删除成功";
		try{
			for(String id:ids.split(",")){
				QsyUserInfoEntity qsyUserInfo = systemService.getEntity(QsyUserInfoEntity.class, 
				id
				);
				qsyUserInfoService.delete(qsyUserInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "用户信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加用户信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(QsyUserInfoEntity qsyUserInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String password = qsyUserInfo.getPassword();
		String pString = PasswordUtil.encrypt(qsyUserInfo.getUsername(), password, PasswordUtil.getStaticSalt());
		message = "用户信息表添加成功";
		try{
			qsyUserInfo.setUserState("1");
			qsyUserInfo.setPassword(pString);
			qsyUserInfoService.save(qsyUserInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新用户信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(QsyUserInfoEntity qsyUserInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户信息表更新成功";
		QsyUserInfoEntity t = qsyUserInfoService.get(QsyUserInfoEntity.class, qsyUserInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(qsyUserInfo, t);
			qsyUserInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "用户信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 用户信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(QsyUserInfoEntity qsyUserInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyUserInfo.getId())) {
			qsyUserInfo = qsyUserInfoService.getEntity(QsyUserInfoEntity.class, qsyUserInfo.getId());
			req.setAttribute("qsyUserInfo", qsyUserInfo);
		}
		return new ModelAndView("com/jeecg/qsyuserinfo/qsyUserInfo-add");
	}
	/**
	 * 用户信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QsyUserInfoEntity qsyUserInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyUserInfo.getId())) {
			qsyUserInfo = qsyUserInfoService.getEntity(QsyUserInfoEntity.class, qsyUserInfo.getId());
			req.setAttribute("qsyUserInfo", qsyUserInfo);
		}
		return new ModelAndView("com/jeecg/qsyuserinfo/qsyUserInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","qsyUserInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(QsyUserInfoEntity qsyUserInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(QsyUserInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyUserInfo, request.getParameterMap());
		List<QsyUserInfoEntity> qsyUserInfos = this.qsyUserInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"用户信息表");
		modelMap.put(NormalExcelConstants.CLASS,QsyUserInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,qsyUserInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(QsyUserInfoEntity qsyUserInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"用户信息表");
    	modelMap.put(NormalExcelConstants.CLASS,QsyUserInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<QsyUserInfoEntity> listQsyUserInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),QsyUserInfoEntity.class,params);
				for (QsyUserInfoEntity qsyUserInfo : listQsyUserInfoEntitys) {
					qsyUserInfoService.save(qsyUserInfo);
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
	 * 
	 * 跳转重置用户密码页面
	 * @author Chj
	 */
	
	@RequestMapping(params = "changepasswordforuser")
	public ModelAndView changepasswordforuser(QsyUserInfoEntity qsyUserInfo, HttpServletRequest req) {
		logger.info("["+IpUtil.getIpAddr(req)+"][跳转重置用户密码页面]["+qsyUserInfo.getUsername()+"]");
		if (StringUtil.isNotEmpty(qsyUserInfo.getId())) {
			qsyUserInfo = systemService.getEntity(QsyUserInfoEntity.class, qsyUserInfo.getId());
			req.setAttribute("qsyUserInfo", qsyUserInfo);
		}
		return new ModelAndView("com/jeecg/qsyuserinfo/changepwd");
	}
	
	/**
	 * 重置密码
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "savenewpwdforuser")
	@ResponseBody
	public AjaxJson savenewpwdforuser(HttpServletRequest req) {
		logger.info("["+IpUtil.getIpAddr(req)+"][重置密码] start");
		String message = null;
		AjaxJson j = new AjaxJson();
		String id = oConvertUtils.getString(req.getParameter("id"));
		String password = oConvertUtils.getString(req.getParameter("password"));
		
		if (StringUtil.isNotEmpty(id)) {
			QsyUserInfoEntity users = systemService.getEntity(QsyUserInfoEntity.class,id);
			users.setPassword(PasswordUtil.encrypt(users.getUsername(), password, PasswordUtil.getStaticSalt()));
			systemService.updateEntitie(users);	
			message = "用户: " + users.getUsername() + "密码重置成功";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			logger.info("["+IpUtil.getIpAddr(req)+"][重置密码]"+message);
		} else{
			message = "密码重置失败";
		}
		j.setMsg(message);
		return j;
	}
}

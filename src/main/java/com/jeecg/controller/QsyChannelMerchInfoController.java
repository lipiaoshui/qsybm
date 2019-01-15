package com.jeecg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jeecg.entity.QsyChannelInfoEntity;
import com.jeecg.entity.QsyChannelMerchInfoEntity;
import com.jeecg.entity.QsyMerchBaseInfoEntity;
import com.jeecg.entity.QsyUserInfoEntity;

/**   
 * @Title: Controller  
 * @Description: 渠道商户信息表
 * @author fuwenjie
 * @date 2019-01-07 14:22:12
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/qsyChannelMerchInfoController")
public class QsyChannelMerchInfoController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(QsyChannelMerchInfoController.class);
	@Autowired
	private SystemService systemService;
	@Autowired
	private JdbcDao jdbcDao;
	
	/**
	 * 渠道商户信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/qsychannelmerchinfo/qsyChannelMerchInfoList");
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
	public void datagrid(QsyChannelMerchInfoEntity qsyChannelMerchInfo,String merchName,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		StringBuilder sql = new StringBuilder();
		// 渠道代码、渠道名称、渠道方商户编号、系统商户编号、系统商户名称
		sql.append(" select a.id as id,a.channel_code as channelCode,b.channel_name as channelName,a.merch_code as merchCode,c.merch_name as merchName, ");
		sql.append(" a.channel_merch_code as channelMerchCode,a.channel_code as channelCodeApp,c.merch_name as merchNameApp");
		sql.append(" from qsy_channel_merch_info a join qsy_channel_info b on a.channel_code=b.channel_code join qsy_merch_base_info c on a.merch_code=c.merch_code where 1=1 ");
		if(!StringUtil.isEmpty(qsyChannelMerchInfo.getChannelCode())){
			sql.append(" and a.channel_code = '"+ qsyChannelMerchInfo.getChannelCode() +"'");
		}
		if(!StringUtil.isEmpty(merchName)){
			sql.append(" and c.merch_name = '"+ merchName +"'");
		}
		sql.append(" order by a.create_time desc limit ?,?");
//		CriteriaQuery cq = new CriteriaQuery(QsyUserInfoEntity.class, dataGrid);
//		//查询条件组装器
//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyChannelMerchInfo, request.getParameterMap());
//		try{
//		//自定义追加查询条件
//		
//		}catch (Exception e) {
//			throw new BusinessException(e.getMessage());
//		}
//		cq.add();
//		this.systemService.getDataGridReturn(cq, true);
		List<Map<String, Object>> resultList = jdbcDao.queryForList(sql.toString(), (dataGrid.getPage()-1)*dataGrid.getRows(), dataGrid.getRows());
		dataGrid.setTotal(resultList.size());
		dataGrid.setResults(resultList);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除用户信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QsyChannelMerchInfoEntity qsyChannelMerchInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		qsyChannelMerchInfo = systemService.getEntity(QsyChannelMerchInfoEntity.class, qsyChannelMerchInfo.getId());
		message = "用户信息表删除成功";
		try{
			systemService.delete(qsyChannelMerchInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			logger.error(e.getMessage());
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
				System.out.println(id);
				QsyChannelMerchInfoEntity qsyChannelMerchInfoEntity = systemService.getEntity(QsyChannelMerchInfoEntity.class, id);
				systemService.delete(qsyChannelMerchInfoEntity);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
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
	public AjaxJson doAdd(QsyChannelMerchInfoEntity qsyChannelMerchInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户信息表添加成功";
		try {
			systemService.save(qsyChannelMerchInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			logger.error(e.getMessage());
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
	public AjaxJson doUpdate(QsyChannelMerchInfoEntity qsyChannelMerchInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户信息表更新成功";
		QsyChannelMerchInfoEntity t = systemService.get(QsyChannelMerchInfoEntity.class, qsyChannelMerchInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(qsyChannelMerchInfo, t);
			systemService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			logger.error(e.getMessage());
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
	public ModelAndView goAdd(QsyChannelMerchInfoEntity qsyChannelMerchInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyChannelMerchInfo.getId())) {
			qsyChannelMerchInfo = systemService.getEntity(QsyUserInfoEntity.class, qsyChannelMerchInfo.getId());
			req.setAttribute("qsyChannelMerchInfo", qsyChannelMerchInfo);
		}
		return new ModelAndView("com/jeecg/qsychannelmerchinfo/qsyChannelMerchInfo-add");
	}
	
	/**
	 * 用户信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QsyChannelMerchInfoEntity qsyChannelMerchInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyChannelMerchInfo.getId())) {
			qsyChannelMerchInfo = systemService.getEntity(QsyChannelMerchInfoEntity.class, qsyChannelMerchInfo.getId());
			req.setAttribute("qsyChannelMerchInfo", qsyChannelMerchInfo);
			String sql = " select merch_name as merchName from qsy_merch_base_info where merch_code = '"+ qsyChannelMerchInfo.getMerchCode() +"'";
			req.setAttribute("merchName", jdbcDao.queryForObject(sql, String.class));
		}
		return new ModelAndView("com/jeecg/qsychannelmerchinfo/qsyChannelMerchInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","qsyChannelMerchInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(QsyChannelMerchInfoEntity qsyChannelMerchInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(QsyUserInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyChannelMerchInfo, request.getParameterMap());
		List<QsyUserInfoEntity> qsyChannelMerchInfos = this.systemService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"渠道商户信息表");
		modelMap.put(NormalExcelConstants.CLASS,QsyChannelMerchInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("渠道商户信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,qsyChannelMerchInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(QsyChannelMerchInfoEntity qsyChannelMerchInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"渠道商户信息表");
    	modelMap.put(NormalExcelConstants.CLASS,QsyChannelMerchInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("渠道商户信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	/**
	 * 查询条码编号是否重复
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "verifyChannelCode")
	@ResponseBody
	public Map<String,Object> verifyChannelCode(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		String merchCode = request.getParameter("merchCode");
		String channelCode = request.getParameter("channelCode");
		// 根据机构编号和商户编号查询是否录入过
		String sql = " select * from qsy_channel_merch_info where merch_code='"+merchCode+"' and channel_code='"+channelCode+"'";
		List<Map<String,Object>> resultList = jdbcDao.queryForList(sql);
		if(resultList.size()==0 || resultList==null){
			// 尚未录入
			retMap.put("success", true);
		}else{
			// 已录入
			retMap.put("success", false);
		}
		return retMap;
	}
}

package com.jeecg.controller;
import com.jeecg.entity.QsyMerchBaseInfoEntity;
import com.jeecg.entity.QsyMerchSettleAccountEntity;
import com.jeecg.entity.QsyRegionInfoEntity;
import com.jeecg.service.QsyMerchSettleAccountServiceI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.util.OrgConstants;
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
 * @Description: 商户结算信息表
 * @author onlineGenerator
 * @date 2018-12-29 10:29:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/qsyMerchSettleAccountController")
public class QsyMerchSettleAccountController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QsyMerchSettleAccountController.class);

	@Autowired
	private QsyMerchSettleAccountServiceI qsyMerchSettleAccountService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private JdbcDao jdbcDao;
	


	/**
	 * 商户结算信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/merchsettleaccount/qsyMerchSettleAccountList");
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
	public void datagrid(QsyMerchSettleAccountEntity qsyMerchSettleAccount,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		CriteriaQuery cq = new CriteriaQuery(QsyMerchSettleAccountEntity.class, dataGrid);
//		//查询条件组装器
//		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyMerchSettleAccount, request.getParameterMap());
//		try{
//		//自定义追加查询条件
//		
//		}catch (Exception e) {
//			throw new BusinessException(e.getMessage());
//		}
//		cq.add();
//		this.qsyMerchSettleAccountService.getDataGridReturn(cq, true);
//		TagUtil.datagrid(response, dataGrid);
		String merchName = request.getParameter("merchName");
		String accountNo = qsyMerchSettleAccount.getAccountNo();
		String accountType = qsyMerchSettleAccount.getAccountName();
		StringBuilder countSql = new StringBuilder("select count(1) from (");
		StringBuilder sb = new StringBuilder("select a.id,a.merch_code as merchCode,b.merch_name as merchName,a.account_no as accountNo,a.account_name as accountName, ");
		sb.append(" open_bank_name as openBankName,open_bank_city as openBankCity,open_bank_branch as openBankBranch,account_type as accountType");
		sb.append(" from qsy_merch_settle_account a join qsy_merch_base_info b on a.merch_code=b.merch_code ");
		sb.append(" where 1=1 ");
		if(!StringUtil.isEmpty(accountNo)){
			sb.append(" and a.account_no like '%" + accountNo + "%' ");
		}
		if(!StringUtil.isEmpty(accountType)){
			sb.append(" and a.account_name like '%" + accountType + "%' ");
		}
		if(!StringUtil.isEmpty(merchName)){
			sb.append(" and b.merch_name like '%" + merchName + "%' ");
		}
		sb.append(" order by a.create_time desc ");
		if(!StringUtil.isEmpty(dataGrid.getSort())){
			sb.append(" ," + dataGrid.getSort() + " " + dataGrid.getOrder());
		}
		countSql.append(sb.toString()).append(") as t ");
		String total = systemService.getCountForJdbcParam(countSql.toString()) + "";
		sb.append(" limit ?,?");
		List<Map<String,Object>> resultList = jdbcDao.queryForList(sb.toString(), (dataGrid.getPage()-1)*dataGrid.getRows(), dataGrid.getRows());
		dataGrid.setTotal(Integer.valueOf(total));
		dataGrid.setResults(resultList);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除商户结算信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QsyMerchSettleAccountEntity qsyMerchSettleAccount, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		qsyMerchSettleAccount = systemService.getEntity(QsyMerchSettleAccountEntity.class, qsyMerchSettleAccount.getId());
		message = "商户结算信息表删除成功";
		try{
			qsyMerchSettleAccountService.delete(qsyMerchSettleAccount);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商户结算信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除商户结算信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商户结算信息表删除成功";
		try{
			for(String id:ids.split(",")){
				QsyMerchSettleAccountEntity qsyMerchSettleAccount = systemService.getEntity(QsyMerchSettleAccountEntity.class, 
				id
				);
				qsyMerchSettleAccountService.delete(qsyMerchSettleAccount);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商户结算信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商户结算信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(QsyMerchSettleAccountEntity qsyMerchSettleAccount, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商户结算信息表添加成功";
		try{
			System.out.println(qsyMerchSettleAccount.toString());
			qsyMerchSettleAccountService.save(qsyMerchSettleAccount);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商户结算信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新商户结算信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(QsyMerchSettleAccountEntity qsyMerchSettleAccount, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商户结算信息表更新成功";
		QsyMerchSettleAccountEntity t = qsyMerchSettleAccountService.get(QsyMerchSettleAccountEntity.class, qsyMerchSettleAccount.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(qsyMerchSettleAccount, t);
			qsyMerchSettleAccountService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "商户结算信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 商户结算信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(QsyMerchSettleAccountEntity qsyMerchSettleAccount, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyMerchSettleAccount.getId())) {
			qsyMerchSettleAccount = qsyMerchSettleAccountService.getEntity(QsyMerchSettleAccountEntity.class, qsyMerchSettleAccount.getId());
			req.setAttribute("qsyMerchSettleAccount", qsyMerchSettleAccount);
		}
		return new ModelAndView("com/jeecg/merchsettleaccount/qsyMerchSettleAccount-add");
	}
	/**
	 * 商户结算信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QsyMerchSettleAccountEntity qsyMerchSettleAccount, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyMerchSettleAccount.getId())) {
			qsyMerchSettleAccount = qsyMerchSettleAccountService.getEntity(QsyMerchSettleAccountEntity.class, qsyMerchSettleAccount.getId());
			req.setAttribute("qsyMerchSettleAccount", qsyMerchSettleAccount);
			req.setAttribute("openBankCityName", systemService.get(QsyRegionInfoEntity.class, qsyMerchSettleAccount.getOpenBankCity()).getName());
		}
		return new ModelAndView("com/jeecg/merchsettleaccount/qsyMerchSettleAccount-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","qsyMerchSettleAccountController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(QsyMerchSettleAccountEntity qsyMerchSettleAccount,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(QsyMerchSettleAccountEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyMerchSettleAccount, request.getParameterMap());
		List<QsyMerchSettleAccountEntity> qsyMerchSettleAccounts = this.qsyMerchSettleAccountService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"商户结算信息表");
		modelMap.put(NormalExcelConstants.CLASS,QsyMerchSettleAccountEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商户结算信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,qsyMerchSettleAccounts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(QsyMerchSettleAccountEntity qsyMerchSettleAccount,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"商户结算信息表");
    	modelMap.put(NormalExcelConstants.CLASS,QsyMerchSettleAccountEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商户结算信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<QsyMerchSettleAccountEntity> listQsyMerchSettleAccountEntitys = ExcelImportUtil.importExcel(file.getInputStream(),QsyMerchSettleAccountEntity.class,params);
				for (QsyMerchSettleAccountEntity qsyMerchSettleAccount : listQsyMerchSettleAccountEntitys) {
					qsyMerchSettleAccountService.save(qsyMerchSettleAccount);
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
	
	
//	public void getOrgInfos(HttpServletRequest req, QsyMerchSettleAccountEntity qsyMerchSettleAccountEntity) {
//		List<QsyMerchBaseInfoEntity> tSUserOrgs = systemService.findByProperty(QsyMerchBaseInfoEntity.class, "tsUser.id", qsyMerchSettleAccountEntity.getId());
//		String orgIds = "";
//		String departname = "";
//		if (tSUserOrgs.size() > 0) {
//			for (TSUserOrg tSUserOrg : tSUserOrgs) {
//				orgIds += tSUserOrg.getTsDepart().getId() + ",";
//				departname += tSUserOrg.getTsDepart().getDepartname() + ",";
//			}
//		}
//		req.setAttribute("orgIds", orgIds);
//		req.setAttribute("departname", departname);
//
//	}
	/**
	 * 角色显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridMerch")
	public void datagridMerch(QsyMerchBaseInfoEntity qsyMerchBaseInfo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
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
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 城市显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridCity")
	public void datagridCity(String pid,String city,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		StringBuilder countSql = new StringBuilder("select count(1) from (");
		StringBuilder sql = new StringBuilder(" select id,pid,name as openBankCityName from qsy_region_info where pid!='1' and pid != '0' ");
		if(!StringUtil.isEmpty(pid)){
			sql.append(" and pid = '" + pid + "'");
		}
		if(!StringUtil.isEmpty(city)){
			sql.append(" and name like '%" + city + "%'");
		}
		countSql.append(sql.toString()).append(") as t");
		sql.append(" limit ?,? ");
		List<Map<String,Object>> resultList = jdbcDao.queryForList(sql.toString(), (dataGrid.getPage()-1)*dataGrid.getRows(), dataGrid.getRows());
		String total = systemService.getCountForJdbcParam(countSql.toString()) + "";
		dataGrid.setTotal(Integer.valueOf(total));
		dataGrid.setResults(resultList);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 用户选择商户跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "merches")
	public ModelAndView merches(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("com/jeecg/merchsettleaccount/merches");
		String ids = oConvertUtils.getString(request.getParameter("ids"));
		mv.addObject("ids", ids);
		return mv;
	}
	/**
	 * 用户选择开户银行城市跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "cities")
	public ModelAndView cities(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("com/jeecg/merchsettleaccount/cities");
		String ids = oConvertUtils.getString(request.getParameter("ids"));
		mv.addObject("ids", ids);
		return mv;
	}
	
	/**
     * 用户选择机构列表跳转页面
     *
     * @return
     */
	@RequestMapping(params = "departSelect")
    public String departSelect(HttpServletRequest req) {
    	
    	req.setAttribute("orgIds", req.getParameter("orgIds"));
    	
        return "com/jeecg/merchsettleaccount/citySelect";
    }
	
	/**
	 * 查询银行账号是否重复
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "verifyAccountNo")
	@ResponseBody
	public Map<String,Object> verifyChannelCode(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		String merchCode = request.getParameter("merchCode");
		String accountNo = request.getParameter("accountNo");
		// 根据机构编号和商户编号查询是否录入过
		String sql = " select * from qsy_merch_settle_account where merch_code='"+merchCode+"' and account_no='"+accountNo+"'";
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

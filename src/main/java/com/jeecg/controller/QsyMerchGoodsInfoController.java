package com.jeecg.controller;
import com.jeecg.entity.QsyMerchGoodsInfoEntity;
import com.jeecg.service.QsyMerchGoodsInfoServiceI;

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
 * @Description: 商户商品信息表
 * @author onlineGenerator
 * @date 2019-01-08 10:47:54
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/qsyMerchGoodsInfoController")
public class QsyMerchGoodsInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QsyMerchGoodsInfoController.class);

	@Autowired
	private QsyMerchGoodsInfoServiceI qsyMerchGoodsInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private JdbcDao jdbcDao;


	/**
	 * 商户商品信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/qsymerchgoodsinfo/qsyMerchGoodsInfoList");
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
	public void datagrid(QsyMerchGoodsInfoEntity qsyMerchGoodsInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(QsyMerchGoodsInfoEntity.class, dataGrid);
		if(!StringUtil.isEmpty(qsyMerchGoodsInfo.getGoodsBarcode())){
			qsyMerchGoodsInfo.setGoodsBarcode("*" + qsyMerchGoodsInfo.getGoodsBarcode() + "*");
		}
		if(!StringUtil.isEmpty(qsyMerchGoodsInfo.getGoodsName())){
			qsyMerchGoodsInfo.setGoodsName("*" + qsyMerchGoodsInfo.getGoodsName() + "*");
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyMerchGoodsInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.qsyMerchGoodsInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
		
//		String goodsName = qsyMerchGoodsInfo.getGoodsName();
//		String goodsBarcode = qsyMerchGoodsInfo.getGoodsBarcode();
//		String goodsType = qsyMerchGoodsInfo.getGoodsType();
//		String merchCode = qsyMerchGoodsInfo.getMerchCode();
//		StringBuilder countSql = new StringBuilder("select count(1) from (");
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select id,merch_code as merchCode,goods_barcode as goodsBarcode,goods_name as goodsName,goods_spec as goodsSpec,goods_type as goodsType,");
//		sql.append(" goods_refer_price/100 as goodsReferPrice,brand_name as brandName,sell_short_name as sellShortName,");
//		sql.append(" create_time as createTime,create_by as createBy,update_time as updateTime,update_by as updateBy");
//		sql.append(" from qsy_merch_goods_info where 1=1 ");
//		if(!StringUtil.isEmpty(goodsName)){
//			sql.append(" and goods_name like '%" + goodsName +"%'");
//		}
//		if(!StringUtil.isEmpty(goodsBarcode)){
//			sql.append(" and goods_barcode like '%" + goodsBarcode +"%'");
//		}
//		if(!StringUtil.isEmpty(goodsType)){
//			sql.append(" and goods_type = '" + goodsType +"'");
//		}
//		if(!StringUtil.isEmpty(merchCode)){
//			sql.append(" and merch_code = '"+ merchCode +"'");
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
	 * 删除商户商品信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(QsyMerchGoodsInfoEntity qsyMerchGoodsInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		qsyMerchGoodsInfo = systemService.getEntity(QsyMerchGoodsInfoEntity.class, qsyMerchGoodsInfo.getId());
		message = "商户商品信息表删除成功";
		try{
			qsyMerchGoodsInfoService.delete(qsyMerchGoodsInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商户商品信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除商户商品信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商户商品信息表删除成功";
		try{
			for(String id:ids.split(",")){
				QsyMerchGoodsInfoEntity qsyMerchGoodsInfo = systemService.getEntity(QsyMerchGoodsInfoEntity.class, 
				id
				);
				qsyMerchGoodsInfoService.delete(qsyMerchGoodsInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "商户商品信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商户商品信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(QsyMerchGoodsInfoEntity qsyMerchGoodsInfo, String goodPrice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商户商品信息表添加成功";
		try{
			qsyMerchGoodsInfo.setSellShortName(qsyMerchGoodsInfo.getGoodsName()+" "+qsyMerchGoodsInfo.getGoodsSpec());
			Number num = Float.parseFloat(goodPrice)*100;
	        int oamount = num.intValue();
	        long goodsPrice = Long.valueOf(oamount);
	        qsyMerchGoodsInfo.setGoodsPrice(goodsPrice);
			qsyMerchGoodsInfoService.save(qsyMerchGoodsInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "商户商品信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新商户商品信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(QsyMerchGoodsInfoEntity qsyMerchGoodsInfo, String goodPrice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "商户商品信息表更新成功";
		QsyMerchGoodsInfoEntity t = qsyMerchGoodsInfoService.get(QsyMerchGoodsInfoEntity.class, qsyMerchGoodsInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(qsyMerchGoodsInfo, t);
			Number num = Float.parseFloat(goodPrice)*100;
	        int oamount = num.intValue();
	        long goodsPrice = Long.valueOf(oamount);
	        t.setGoodsPrice(goodsPrice);
			t.setSellShortName(t.getGoodsName()+" "+t.getGoodsSpec());
			qsyMerchGoodsInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "商户商品信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 商户商品信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(QsyMerchGoodsInfoEntity qsyMerchGoodsInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyMerchGoodsInfo.getId())) {
			qsyMerchGoodsInfo = qsyMerchGoodsInfoService.getEntity(QsyMerchGoodsInfoEntity.class, qsyMerchGoodsInfo.getId());
			req.setAttribute("qsyMerchGoodsInfo", qsyMerchGoodsInfo);
		}
		return new ModelAndView("com/jeecg/qsymerchgoodsinfo/qsyMerchGoodsInfo-add");
	}
	/**
	 * 商户商品信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(QsyMerchGoodsInfoEntity qsyMerchGoodsInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(qsyMerchGoodsInfo.getId())) {
			qsyMerchGoodsInfo = qsyMerchGoodsInfoService.getEntity(QsyMerchGoodsInfoEntity.class, qsyMerchGoodsInfo.getId());
			qsyMerchGoodsInfo.setGoodsPrice(qsyMerchGoodsInfo.getGoodsPrice()/100);
			req.setAttribute("qsyMerchGoodsInfo", qsyMerchGoodsInfo);
		}
		return new ModelAndView("com/jeecg/qsymerchgoodsinfo/qsyMerchGoodsInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","qsyMerchGoodsInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(QsyMerchGoodsInfoEntity qsyMerchGoodsInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(QsyMerchGoodsInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, qsyMerchGoodsInfo, request.getParameterMap());
		List<QsyMerchGoodsInfoEntity> qsyMerchGoodsInfos = this.qsyMerchGoodsInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"商户商品信息表");
		modelMap.put(NormalExcelConstants.CLASS,QsyMerchGoodsInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商户商品信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,qsyMerchGoodsInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(QsyMerchGoodsInfoEntity qsyMerchGoodsInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"商户商品信息表");
    	modelMap.put(NormalExcelConstants.CLASS,QsyMerchGoodsInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("商户商品信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<QsyMerchGoodsInfoEntity> listQsyMerchGoodsInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),QsyMerchGoodsInfoEntity.class,params);
				for (QsyMerchGoodsInfoEntity qsyMerchGoodsInfo : listQsyMerchGoodsInfoEntitys) {
					qsyMerchGoodsInfoService.save(qsyMerchGoodsInfo);
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
	 * 查询商品条形码是否重复
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "verifyGoodsBarcode")
	@ResponseBody
	public Map<String,Object> verifyChannelCode(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		String merchCode = request.getParameter("merchCode");
		String goodsBarcode = request.getParameter("goodsBarcode");
		// 根据机构编号和商户编号查询是否录入过
		String sql = " select * from qsy_merch_goods_info where merch_code='"+merchCode+"' and goods_barcode='"+goodsBarcode+"'";
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

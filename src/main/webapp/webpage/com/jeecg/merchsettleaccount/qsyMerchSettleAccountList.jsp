<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="qsyMerchSettleAccountList" checkbox="true" pagination="true" fitColumns="true" title="结算账户管理" actionUrl="qsyMerchSettleAccountController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商户名称"  field="merchCode"  dictionary="qsy_merch_base_info,merch_code,merch_name" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属商户"  field="merchName"  hidden="true" dictionary="selectMerch,merchName,merch_name" query="true" popup="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="银行账号"  field="accountNo"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开户名称"  field="accountName"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开户银行"  field="openBankName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开户银行城市"  field="openBankCity"  dictionary="t_s_region,id,name" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="开户支行"  field="openBankBranch"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="账户类型"  field="accountType" dictionary="account_type" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改时间"  field="updateTime"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="qsyMerchSettleAccountController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="qsyMerchSettleAccountController.do?goAdd" funname="add"  width="600" height="330"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="qsyMerchSettleAccountController.do?goUpdate" funname="update"  width="600" height="330"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="qsyMerchSettleAccountController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="qsyMerchSettleAccountController.do?goUpdate" funname="detail"  width="600" height="330"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'qsyMerchSettleAccountController.do?upload', "qsyMerchSettleAccountList");
}

//导出
function ExportXls() {
	JeecgExcelExport("qsyMerchSettleAccountController.do?exportXls","qsyMerchSettleAccountList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("qsyMerchSettleAccountController.do?exportXlsByT","qsyMerchSettleAccountList");
}

//bootstrap列表图片格式化
function btListImgFormatter(value,row,index){
	return listFileImgFormat(value,"image");
}
//bootstrap列表文件格式化
function btListFileFormatter(value,row,index){
	return listFileImgFormat(value);
}

//列表文件图片 列格式化方法
function listFileImgFormat(value,type){
	var href='';
	if(value==null || value.length==0){
		return href;
	}
	var value1 = "img/server/"+value;
	if("image"==type){
 		href+="<img src='"+value1+"' width=30 height=30  onmouseover='tipImg(this)' onmouseout='moveTipImg()' style='vertical-align:middle'/>";
	}else{
 		if(value.indexOf(".jpg")>-1 || value.indexOf(".gif")>-1 || value.indexOf(".png")>-1){
 			href+="<img src='"+value1+"' onmouseover='tipImg(this)' onmouseout='moveTipImg()' width=30 height=30 style='vertical-align:middle'/>";
 		}else{
 			var value2 = "img/server/"+value+"?down=true";
 			href+="<a href='"+value2+"' class='ace_button' style='text-decoration:none;' target=_blank><u><i class='fa fa-download'></i>点击下载</u></a>";
 		}
	}
	return href;
}

</script>

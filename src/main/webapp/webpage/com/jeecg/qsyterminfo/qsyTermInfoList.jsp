<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="qsyTermInfoList" checkbox="true" pagination="true" fitColumns="true" title="商户机具管理" actionUrl="qsyTermInfoController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属商户"  field="merchName"  hidden="true" dictionary="selectMerch,merchName@merchCode,merch_name@merch_code" query="true" popup="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商户名称"  field="merchCode"  dictionary="qsy_merch_base_info,merch_code,merch_name" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="终端编号"  field="termCode"   query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="终端产商"  field="termManu"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="终端类型"  field="termType"  dictionary="term_type" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="终端型号"  field="termModel"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="终端密钥"  field="secretKey"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改时间"  field="updateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="qsyTermInfoController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="qsyTermInfoController.do?goAdd" funname="add"  width="600" height="180"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="qsyTermInfoController.do?goUpdate" funname="update"  width="600" height="220"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="qsyTermInfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="qsyTermInfoController.do?goUpdate" funname="detail"  width="600" height="220"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  <input type="hidden" id="merchCode" name="merchCode">
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'qsyTermInfoController.do?upload', "qsyTermInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("qsyTermInfoController.do?exportXls","qsyTermInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("qsyTermInfoController.do?exportXlsByT","qsyTermInfoList");
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

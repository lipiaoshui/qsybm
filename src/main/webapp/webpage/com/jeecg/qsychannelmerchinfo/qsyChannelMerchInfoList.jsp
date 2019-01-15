<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="qsyChannelMerchInfoList" checkbox="true" pagination="true" fitColumns="true" title="渠道商户管理" actionUrl="qsyChannelMerchInfoController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属渠道"  field="channelCode" hidden="true" dictionary="qsy_channel_info,channel_code,channel_name" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属商户"  field="merchName"  hidden="true" dictionary="selectMerch,merchName,merch_name" query="true" popup="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="渠道代码"  field="channelCodeApp" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="渠道名称"  field="channelName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="渠道方商户编号"  field="channelMerchCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="系统商户编号"  field="merchCode"  dictionary="qsyuserinfo,merchCode,merch_code" popup="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="系统商户名称"  field="merchNameApp"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改时间"  field="updateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="qsyChannelMerchInfoController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="qsyChannelMerchInfoController.do?goAdd" funname="add"  width="600" height="100" ></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="qsyChannelMerchInfoController.do?goUpdate" funname="update"  width="600" height="100"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="qsyChannelMerchInfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="qsyChannelMerchInfoController.do?goUpdate" funname="detail"  width="600" height="100"></t:dgToolBar>
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
	openuploadwin('Excel导入', 'qsyChannelMerchInfoController.do?upload', "qsyChannelMerchInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("qsyChannelMerchInfoController.do?exportXls","qsyChannelMerchInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("qsyChannelMerchInfoController.do?exportXlsByT","qsyChannelMerchInfoList");
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

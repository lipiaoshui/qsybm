<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="qsyUserInfoList" checkbox="true" pagination="true" fitColumns="true" title="商户用户管理" actionUrl="qsyUserInfoController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属商户"  field="merchCode" dictionary="selectMerch,merchCode,merch_code" query="true" popup="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户账号"  field="username"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户名称"  field="realname"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="登录密码"  field="password"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机号"  field="telephone"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户类型"  field="userType"  dictionary="user_type"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户状态"  field="userState" dictionary="user_state" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="微信用户标识"  field="openid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改时间"  field="updateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="qsyUserInfoController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="qsyUserInfoController.do?goAdd" funname="add"  width="700" height="300"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="qsyUserInfoController.do?goUpdate" funname="update"  width="600" height="250"></t:dgToolBar>
   <t:dgToolBar title="common.password.reset" icon="icon-edit" url="qsyUserInfoController.do?changepasswordforuser" funname="update"  width="550" height="100"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="qsyUserInfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="qsyUserInfoController.do?goUpdate" funname="detail"  width="600"></t:dgToolBar>
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
	openuploadwin('Excel导入', 'qsyUserInfoController.do?upload', "qsyUserInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("qsyUserInfoController.do?exportXls","qsyUserInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("qsyUserInfoController.do?exportXlsByT","qsyUserInfoList");
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

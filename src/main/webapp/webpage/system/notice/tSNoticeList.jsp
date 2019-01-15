<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_depart_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tSNoticeList" checkbox="true" fitColumns="true" title="common.notice" actionUrl="noticeController.do?datagrid2" idField="id" fit="true" queryMode="group">
   <t:dgCol title="ID"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="公告标题"  field="noticeTitle"  hidden="false"  query="true"   width="60"></t:dgCol>
   <t:dgCol title="公告内容"  field="noticeContent"  hidden="false"  queryMode="group"  width="120"></t:dgCol> 
   <t:dgCol title="发布者"  field="createUser"  hidden="false"  query="true"   width="60" ></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  hidden="false" formatter="yyyy-MM-dd"  queryMode="group"  width="60" ></t:dgCol>
<%--    <t:dgCol title="阅读期限"  field="noticeTerm" formatter="yyyy-MM-dd" hidden="false"  queryMode="group"  width="120"></t:dgCol>
 --%>   <t:dgCol title="common.operation" field="opt" width="60"></t:dgCol>
   <t:dgDelOpt title="common.delete" url="noticeController.do?doDel&id={id}&pageInstCode={instCode}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="common.add" icon="icon-add" url="noticeController.do?goAdd" funname="add" width="500" height="250"></t:dgToolBar>
   <t:dgToolBar title="common.edit" icon="icon-edit" url="noticeController.do?goAdd" funname="jsUpdate" width="500" height="250"></t:dgToolBar>
<%--       <t:dgToolBar title="查看" icon="icon-search" url="noticeController.do?goDetail" funname="detail" width="500" height="250"></t:dgToolBar>
 --%><%--    <t:dgToolBar title="common.batchDelete"  icon="icon-remove" url="noticeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
 --%>  <%-- <t:dgFunOpt exp="noticeLevel#eq#2" funname="queryRoles(id)" title="common.authority" urlclass="ace_button"  urlfont="fa-toggle-on"></t:dgFunOpt>
   <t:dgFunOpt exp="noticeLevel#eq#3" funname="queryUsers(id)" title="common.authority" urlclass="ace_button"  urlfont="fa-toggle-on"></t:dgFunOpt>--%>
  </t:datagrid>
  <input type="hidden" id="newInstCode" value="${newInstCode}"/>
  </div>
 </div>
<!--  <div data-options="region:'east',
	title:'通知公告授权管理',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
	style="width: 500px; overflow: hidden;">
<div class="easyui-panel" style="padding: 1px;" fit="true" border="false" id="subListpanel"></div>
</div> -->

 <script type="text/javascript">
	 function jsUpdate(title,url,id){
		 var rowData = $('#'+id).datagrid('getSelected');
			 if (!rowData) {
				 tip('请选择编辑项目');
				 return;
			 }
		 	url += '&id='+rowData.id;
		 	var nowInstCode = $("#newInstCode").val();
		 	if(nowInstCode != rowData.instCode){
		 		
				 tip('只能编辑自己创建的');
				 return;
		 	}
			add(title,url,id,500,250);

	 }
 function queryRoles(id){
		if(li_east == 0){
			   $('#main_depart_list').layout('expand','east'); 
			}
			$('#subListpanel').panel("refresh", "noticeAuthorityRoleController.do?noticeAuthorityRole&noticeId=" + id);
	}
 function queryUsers(id){
		if(li_east == 0){
			   $('#main_depart_list').layout('expand','east'); 
			}
			$('#subListpanel').panel("refresh", "noticeAuthorityUserController.do?noticeAuthorityUser&noticeId=" + id);
	}
 </script>

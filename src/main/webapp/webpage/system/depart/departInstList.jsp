<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="departUserList" title="common.operation"
            actionUrl="departController.do?instDatagrid&orgCode=${orgCode}" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="机构名称" sortable="false" width="100" field="instName" dictionary="ms_institution,INST_CODE,INST_NAME"  ></t:dgCol>
<%-- 	<t:dgCol title="所属机构编号" field="orgCode" width="100" query="true"></t:dgCol>
 --%>	<t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
	<t:dgDelOpt title="解除关系" url="departController.do?delOrg&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
	<t:dgToolBar title="添加下属机构" icon="icon-add" url="departController.do?goAddInstToOrg&orgCode=${orgCode}" funname="add" width="600"></t:dgToolBar>
</t:datagrid>

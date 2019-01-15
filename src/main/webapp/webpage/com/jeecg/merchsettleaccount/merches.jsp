<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>商户列表</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:datagrid  pagination="false"  name="merchList" title="商户选择"  actionUrl="qsyMerchSettleAccountController.do?datagridMerch" idField="id" checkbox="false" showRefresh="false"  fit="true"  queryMode="group" onLoadSuccess="initCheck">
	<t:dgCol title="id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="商户编号" field="merchCode" width="50" query="true" ></t:dgCol>
	<t:dgCol title="商户名称" field="merchName" width="50" query="true" ></t:dgCol>
</t:datagrid>
</body>
<script type="text/javascript">
function initCheck(data){
	var ids = "${ids}";
	var idArr = ids.split(",");
	for(var i=0;i<idArr.length;i++){
		if(idArr[i]!=""){
			$("#merchList").datagrid("selectRecord",idArr[i]);
		}
	}
}
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>商户列表</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:datagrid  pagination="true"  name="cityList" title="城市选择"  actionUrl="qsyMerchSettleAccountController.do?datagridCity" idField="id" checkbox="true" singleSelect="true" showRefresh="false"  fit="true"  queryMode="group" onLoadSuccess="initCheck">
	<t:dgCol title="id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="城市" field="openBankCityName" width="50" ></t:dgCol>
	<t:dgCol title="省份" field="pid" width="50" hidden="true" dictionary="qsy_region_info,id,name" dictCondition="where pid ='1'" query="true" ></t:dgCol>
	<t:dgCol title="市/县/区" field="city" width="50" hidden="true" query="true" ></t:dgCol> 
</t:datagrid>
</body>
<script type="text/javascript">
function initCheck(data){
	var ids = "${ids}";
	var idArr = ids.split(",");
	for(var i=0;i<idArr.length;i++){
		if(idArr[i]!=""){
			$("#cityList").datagrid("selectRecord",idArr[i]);
		}
	}
}
</script>
</html>
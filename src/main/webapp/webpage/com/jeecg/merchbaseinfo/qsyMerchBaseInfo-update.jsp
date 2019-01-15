<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商户入网管理</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<script type="text/javascript">
$(document).ready(function(){
	 $("input[name='credExpireDate']").addClass("Wdate").click(function(){WdatePicker({dateFmt:'yyyyMMdd'});});
})
</script>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="qsyMerchBaseInfoController.do?doUpdate">
	<input id="id" name="id" type="hidden" value="${qsyMerchBaseInfo.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 商户编号: </label></td>
			<td class="value">
                <input class="inputxt" name="merchCode" value="${qsyMerchBaseInfo.merchCode}" readonly="readonly"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 商户名称: </label></td>
			<td class="value" width="10%">
                <input id="merchName" class="inputxt" name="merchName" value="${qsyMerchBaseInfo.merchName}" maxlength="25" datatype="*"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 营业执照号: </label></td>
			<td class="value" width="10%">
                <input id="businessLicenseNo" class="inputxt" name="businessLicenseNo" value="${qsyMerchBaseInfo.businessLicenseNo}" maxlength="25" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 法人姓名: </label></td>
			<td class="value">
                <input class="inputxt" name="legalRepreName" value="${qsyMerchBaseInfo.legalRepreName}" datatype="*"  ignore="ignore"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 法人身份证号: </label></td>
			<td class="value">
			<input id="legalRepreNum" class="inputxt" name="legalRepreNum" value="${qsyMerchBaseInfo.legalRepreNum}" maxlength="50" datatype="/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/" 
                sucmsg="通过信息验证！" errormsg="请输入有效身份证号" ignore="ignore"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 证件有效期: </label></td>
			<td class="value">
                <input class="inputxt" name="credExpireDate" value="${qsyMerchBaseInfo.credExpireDate}" readonly="readonly"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 商户地址: </label></td>
			<td class="value">
                <input class="inputxt" name="merchAddr" value="${qsyMerchBaseInfo.merchAddr}" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 商户状态: </label></td>
			<td class="value">
				<t:dictSelect field="merchState" type="select" typeGroupCode="merch_state" title="商户状态" defaultVal="${qsyMerchBaseInfo.merchState}" divClass="form-control input-sm" datatype="*"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 联系电话: </label></td>
			<td class="value">
                <input class="inputxt" name="telephone" value="${qsyMerchBaseInfo.telephone}" datatype="m"  ignore="ignore"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
	</table>
</t:formvalid>
</body>
</html>
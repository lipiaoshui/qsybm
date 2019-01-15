<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商户入网管理</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="qsyTermInfoController.do?doUpdate">
	<input id="id" name="id" type="hidden" value="${qsyTermInfo.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 所属商户：</label></td>
			<td class="value" width="10%">
				<t:dictSelect field="merchCode" defaultVal="${qsyTermInfo.merchCode}" dictTable="qsy_merch_base_info" dictField="merch_code" dictText="merch_name" readonly="readonly"></t:dictSelect>
                <%-- <input id="merchCode" class="inputxt" name="merchCode" value="${qsyTermInfo.merchCode}" readonly="true"/> --%>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 终端编号：</label></td>
			<td class="value" width="10%">
                <input id="termCode" class="inputxt" name="termCode" value="${qsyTermInfo.termCode}" readonly="true"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 终端产商：</label></td>
			<td class="value">
                <input id="termManu" class="inputxt" name="termManu" value="${qsyTermInfo.termManu}"  maxlength="64" datatype="*" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 终端类型：</label></td>
			<td class="value">
				<t:dictSelect field="termType" datatype="*" typeGroupCode="term_type" defaultVal="${qsyTermInfo.termType}"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 终端型号：</label></td>
			<td class="value">
                <input id="termModel" class="inputxt" name="termModel" value="${qsyTermInfo.termModel}" maxlength="50" datatype="*" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
	</table>
</t:formvalid>
</body>
</html>
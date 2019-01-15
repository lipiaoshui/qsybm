<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商户入网管理</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="qsyTermInfoController.do?doAdd">
	<input id="id" name="id" type="hidden" value="${qsyTermInfo.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 所属商户：</label></td>
			<td class="value" width="10%">
				<input id="merchCode" type="hidden" class="inputxt" name="merchCode" datatype="*"/>
                <input id="merchName" name="merchName" class="inputxt" readonly="readonly" />
                <t:choose hiddenName="merchCode" hiddenid="merchCode" textname="merchName" url="qsyMerchSettleAccountController.do?merches"  name="merchList" icon="icon-search" title="商户列表" isclear="true" isInit="true" width="600px" left="50%" top="50%"></t:choose>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 终端产商：</label></td>
			<td class="value">
                <input id="termManu" class="inputxt" name="termManu"   maxlength="64"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 终端类型：</label></td>
			<td class="value">
                <t:dictSelect field="termType" typeGroupCode="term_type"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 终端型号：</label></td>
			<td class="value">
                <input id="termModel" class="inputxt" name="termModel" maxlength="50" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
	</table>
</t:formvalid>
</body>
</html>
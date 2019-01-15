<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息表</title>
<t:base type="jquery,easyui,validform,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="qsyUserInfoController.do?doUpdate">
	<input id="id" name="id" type="hidden" value="${qsyUserInfo.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 所属商户：</label></td>
			<td class="value">
                <t:dictSelect field="merchCode" dictTable="qsy_merch_base_info"  readonly="readonly"  dictField="merch_code" dictText="merch_name" id="merchCode" defaultVal="${qsyUserInfo.merchCode}" ></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 用户账号：</label></td>
			<td class="value" width="10%">
                <input id="username" class="inputxt" name="username" value="${qsyUserInfo.username}" readonly="readonly" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 用户姓名：</label></td>
			<td class="value" width="10%">
                <input id="realname" class="inputxt" name="realname" value="${qsyUserInfo.realname}" maxlength="32" datatype="*"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 用户类型：</label></td>
			<td class="value">
                <t:dictSelect field="userType" typeGroupCode="user_type" id="userType" defaultVal="${qsyUserInfo.userType}" datatype="*" ></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 用户状态：</label></td>
			<td class="value">
				<t:dictSelect field="userState" defaultVal="${qsyUserInfo.userState}" typeGroupCode="user_state" datatype="*" ></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 手机号：</label></td>
			<td class="value">
                <input id="telephone" class="inputxt" name="telephone" value="${qsyUserInfo.telephone}"  maxlength="64" datatype="m"  ignore="ignore"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
	</table>
</t:formvalid>
</body>
</html>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商户入网管理</title>
<t:base type="jquery,easyui,validform,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="qsyUserInfoController.do?doAdd">
	<input id="id" name="id" type="hidden" value="${qsyUserInfo.id }"/>
	<table style="width: 700px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 所属商户：</label></td>
			<td class="value">
				<input id="merchCode" type="hidden" class="inputxt" name="merchCode" datatype="*"/>
                <input id="merchName" name="merchName" class="inputxt" readonly="readonly" />
                <t:choose hiddenName="merchCode" hiddenid="merchCode" textname="merchName" url="qsyMerchSettleAccountController.do?merches"  name="merchList" icon="icon-search" title="商户列表" isclear="true" isInit="true" width="600px" left="50%" top="50%"></t:choose>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 用户账号：</label></td>
			<td class="value" width="10%">
                <input id="username" class="inputxt" name="username" maxlength="32" validType="qsy_user_info,username,id" datatype="*" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
	<%-- 	<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label">登录密码：</label></td>
			<td class="value" width="10%">
                <input id="password" class="inputxt" type="password" name="password" value="${qsyUserInfo.password}" maxlength="32" datatype="*"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr> --%>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label">用户姓名：</label></td>
			<td class="value" width="10%">
                <input id="realname" class="inputxt" name="realname" maxlength="32" datatype="*"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.password"/>：</label></td>
			<td class="value">
                   <input type="password" class="inputxt" value="" name="password" plugin="passwordStrength" datatype="*6-18" errormsg="" />
                   <span class="passwordStrength" style="display: none;">
                       <span><t:mutiLang langKey="common.weak"/></span>
                       <span><t:mutiLang langKey="common.middle"/></span>
                       <span class="last"><t:mutiLang langKey="common.strong"/></span>
                   </span>
                   <span class="Validform_checktip"> <t:mutiLang langKey="password.rang6to18"/></span>
               </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="common.repeat.password"/>：</label></td>
			<td class="value">
                   <input id="repassword" class="inputxt" type="password" value="${user.password}" recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！"/>
                   <span class="Validform_checktip"><t:mutiLang langKey="common.repeat.password"/></span>
               </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 用户类型：</label></td>
			<td class="value">
				<t:dictSelect field="userType" typeGroupCode="user_type" id="userType" datatype="*" ></t:dictSelect>
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
                <input id="telephone" class="inputxt" name="telephone"  maxlength="64" datatype="m"  ignore="ignore"/>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
	</table>
</t:formvalid>
</body>
</html>
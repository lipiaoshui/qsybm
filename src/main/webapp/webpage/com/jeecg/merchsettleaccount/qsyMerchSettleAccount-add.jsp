<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商户结算信息表</title>
<t:base type="jquery,easyui,tools,validform"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" beforeSubmit="valid" action="qsyMerchSettleAccountController.do?doAdd">
	<input id="id" name="id" type="hidden" value="${qsyMerchSettleAccount.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right"><label class="Validform_label"> 所属商户: </label></td>
			<td class="value" nowrap>
                <input id="merchCode" type="hidden" class="inputxt" name="merchCode" datatype="*"/>
                <input id="merchName" name="merchName" class="inputxt" readonly="readonly" />
                <t:choose hiddenName="merchCode" hiddenid="merchCode" textname="merchName" url="qsyMerchSettleAccountController.do?merches"  name="merchList" icon="icon-search" title="商户列表" isclear="true" isInit="true" width="600px" left="50%" top="50%"></t:choose>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 开户银行城市: </label></td>
			<td class="value" nowrap>
                <input id="openBankCity" name="openBankCity" type="hidden" datatype="*" />
                <input id="openBankCityName" name="openBankCityName" class="inputxt" readonly="readonly" />
                <t:choose hiddenName="openBankCity" hiddenid="id" textname="openBankCityName" url="qsyMerchSettleAccountController.do?cities" name="cityList" icon="icon-search" title="城市列表" isclear="true" isInit="true" width="600px" left="50%" top="50%"></t:choose>
                <span class="Validform_checktip"></span>
            </td>
		</tr> 
		<tr>
			<td align="right" nowrap><label class="Validform_label">  银行账号: </label></td>
			<td class="value">
                <input class="inputxt" id="accountNo" name="accountNo" datatype="*"/>
                <span class="Validform_checktip" id="validAccount"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 开户名称: </label></td>
			<td class="value">
                <input class="inputxt" name="accountName" datatype="*" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 开户银行: </label></td>
			<td class="value">
                <input class="inputxt" name="openBankName" datatype="*" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 开户支行: </label></td>
			<td class="value">
                <input class="inputxt" name="openBankBranch" datatype="*" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right"><label class="Validform_label"> 账户类型: </label></td>
			<td class="value">
				<t:dictSelect field="accountType" datatype="*" typeGroupCode="account_type"></t:dictSelect>
                <!-- validType="t_s_user,email,id"  -->
                <span class="Validform_checktip"></span>
            </td>
		</tr>
	</table>
</t:formvalid>
</body>
<script type="text/javascript">
	function vaildMerch(){
		$.ajax({
			type:"POST",
			url:"qsyMerchSettleAccountController.do?verifyAccountNo&merchCode="+$("#merchCode").val()+"&accountNo="+$("#accountNo").val(),
			success:function(jsondata){
				jsondata=JSON.parse(jsondata);
				console.log(jsondata);
				data=jsondata.success;
				validPass(!data,"该银行账号已录入");
			}
		})
	}
	function validPass(bool,htmlText){
		if(bool){
			flag = false;
			$("#validAccount").removeClass("Validform_right");
			$("#accountNo").addClass("Validform_error");
			$("#validAccount").addClass("Validform_wrong");
			$("#validAccount").html(htmlText);
		}else{
			flag = true;
			$("#accountNo").removeClass("Validform_error");
			$("#validAccount").removeClass("Validform_wrong");
			$("#validAccount").addClass("Validform_right");
			$("#validAccount").html("通过信息验证！");
		}
	}
	
	function check(){
		if($("#accountNo").val()==null || $.trim($("#merchCode").val())==''){
			flag=false;
			validPass(true,"银行账号不能为空");
			return;
		}
		vaildMerch();
	}
	$("#accountNo").blur(function(){
		if($("#merchCode").val()!=null && $.trim($("#merchCode").val())!='' && $.trim($("#accountNo").val())!=''){
			vaildMerch();
		}
	})

	var flag = false;
	function valid() {
		check();
		return flag;
	}
</script>
</html>
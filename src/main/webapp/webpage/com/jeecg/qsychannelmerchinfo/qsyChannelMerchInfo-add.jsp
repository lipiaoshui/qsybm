<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>渠道商户信息表</title>
<t:base type="jquery,easyui,validform,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password"  layout="table" beforeSubmit="valid" action="qsyChannelMerchInfoController.do?doAdd">
	<input id="id" name="id" type="hidden" value="${qsyChannelMerchInfo.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 所属渠道：</label></td>
			<td class="value" width="10%">
                <t:dictSelect field="channelCode" dictTable="qsy_channel_info" dictField="channel_code" dictText="channel_name" id="channelCode" defaultVal="${qsyUserInfo.channelCode}" datatype="*"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 所属商户：</label></td>
			<td class="value" width="10%">
                <input id="merchCode" type="hidden" class="inputxt" name="merchCode" />
                <input id="merchName" name="merchName" class="inputxt" readonly="readonly" />
                <t:choose hiddenName="merchCode" hiddenid="merchCode" textname="merchName" url="qsyMerchSettleAccountController.do?merches"  name="merchList" icon="icon-search" title="商户列表" isclear="true" isInit="true" width="600px" left="50%" top="50%"></t:choose>
                <span class="Validform_checktip" id="vaildChannel"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 渠道方商户编号：</label></td>
			<td class="value">
                <input id="channelMerchCode" class="inputxt" name="channelMerchCode" value="${qsyChannelMerchInfo.channelMerchCode}"  maxlength="64" datatype="*" />
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
			url:"qsyChannelMerchInfoController.do?verifyChannelCode&merchCode="+$("#merchCode").val()+"&channelCode="+$("#channelCode").val(),
			async:false,
			success:function(jsondata){
				jsondata=JSON.parse(jsondata);
				console.log(jsondata);
				data=jsondata.success;
				validPass(data,"该商户已录入");
			}
		})
	}
	function validPass(bool,htmlText){
		if(bool){
			flag = true;
			$("#accountNo").removeClass("Validform_error");
			$("#vaildChannel").removeClass("Validform_wrong");
			$("#vaildChannel").addClass("Validform_right");
			$("#vaildChannel").html("通过信息验证！");
		}else{
			flag = false;
			$("#vaildChannel").removeClass("Validform_right");
			$("#accountNo").addClass("Validform_error");
			$("#vaildChannel").addClass("Validform_wrong");
			$("#vaildChannel").html(htmlText);
		}
	}
	$(function(){
		$("#channelMerchCode").live('change',function(){
			if($("#merchCode").val()!=null && $.trim($("#merchCode").val())!='' && $("#channelCode").val()!=null && $.trim($("#channelCode").val())!=''){
				vaildMerch();
			}
		})
		$("#merchCode").live('change',function(){
			if($("#merchCode").val()!=null && $.trim($("#merchCode").val())!='' && $("#channelCode").val()!=null && $.trim($("#channelCode").val())!=''){
				vaildMerch();
			}
		})
	})
	var flag = false;
	function valid(){
		if($("#merchCode").val()==null || $.trim($("#merchCode").val())==''){
			validPass(false,"所属商户不能为空");
		}
		vaildMerch();
		return flag;		
	}
</script>
</html>
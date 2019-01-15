<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商户商品信息表</title>
<t:base type="jquery,easyui,validform,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" beforeSubmit="valid" action="qsyMerchGoodsInfoController.do?doAdd">
	<input id="id" name="id" type="hidden" value="${qsyMerchGoodsInfo.id }"/>
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
			<td align="right" width="10%" nowrap><label class="Validform_label"> 商品条形码：</label></td>
			<td class="value" width="10%">
				<input id="goodsBarcode" class="inputxt" name="goodsBarcode" value="${qsyMerchGoodsInfo.goodsBarcode}" dataType="*" maxlength="64" />
                <span class="Validform_checktip" id="validBarcode"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 商品名称：</label></td>
			<td class="value">
                <input id="goodsName" class="inputxt" name="goodsName" value="${qsyMerchGoodsInfo.goodsName}"  maxlength="64" datatype="*"  />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 商品规格：</label></td>
			<td class="value">
                <input id="goodsSpec" class="inputxt" name="goodsSpec" value="${qsyMerchGoodsInfo.goodsSpec}"  maxlength="50" datatype="*"  />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 商品分类：</label></td>
			<td class="value">
				<t:dictSelect field="goodsType" id="goodsType" datatype="*" typeGroupCode="goods_type" defaultVal="${qsyMerchGoodsInfo.goodsType}"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<td align="right" nowrap><label class="Validform_label"> 商品参考价：</label></td>
			<td class="value">
                <input id="goodPrice" class="inputxt" name="goodPrice" value="${qsyMerchGoodsInfo.goodsReferPrice}"  maxlength="12" datatype="/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/" errorMsg="请输入正确的价格" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
			<td align="right" nowrap><label class="Validform_label"> 商标/品牌名称：</label></td>
			<td class="value">
                <input id="brandName" class="inputxt" name="brandName" value="${qsyMerchGoodsInfo.brandName}"  maxlength="64" datatype="*"  />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
	</table>
</t:formvalid>
</body>
<script type="text/javascript">
	function vaildMerch() {
		$.ajax({
			type : "POST",
			url : "qsyMerchGoodsInfoController.do?verifyGoodsBarcode&merchCode="+ $("#merchCode").val()+ "&goodsBarcode="+ $("#goodsBarcode").val(),
			success : function(jsondata) {
				jsondata = JSON.parse(jsondata);
				console.log(jsondata);
				data = jsondata.success;
				if (!data) {//true表示不重复，false重复
					goodsBarcode = $("#goodsBarcode").val();
					flag = false;
					$("#goodsBarcode").addClass("Validform_error");
					$("#validBarcode").addClass("Validform_wrong");
					$("#validBarcode").html("该条形码已录入");
				} else {
					goodsBarcode = $("#goodsBarcode").val();
					flag = true;
					$("#goodsBarcode").removeClass("Validform_error");
					$("#validBarcode").removeClass("Validform_wrong");
					$("#validBarcode").addClass("Validform_right");
					$("#validBarcode").html("通过信息验证！");
				}
			}
		})
	}
	function check() {
		vaildMerch();
	}

	$("#merchCode").change(function() {
		vaildMerch();
	})
	var goodsBarcode = $("#goodsBarcode").val();
	$("#goodsBarcode").blur(function() {
		if ($("#merchCode").val() != null && $.trim($("#merchCode").val()) != '' && $.trim($("#goodsBarcode").val()) != '' && goodsBarcode != $("#goodsBarcode").val()) {
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
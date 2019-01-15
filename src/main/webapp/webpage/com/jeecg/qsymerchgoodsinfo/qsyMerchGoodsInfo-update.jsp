<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商户商品信息表</title>
<t:base type="jquery,easyui,validform,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="qsyMerchGoodsInfoController.do?doUpdate">
	<input id="id" name="id" type="hidden" value="${qsyMerchGoodsInfo.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 所属商户：</label></td>
			<td class="value" width="10%">
                <t:dictSelect field="merchCode" dictTable="qsy_merch_base_info" dictField="merch_code" dictText="merch_name" id="merchCode" defaultVal="${qsyMerchGoodsInfo.merchCode}" readonly="readonly"></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 商品条形码：</label></td>
			<td class="value" width="10%">
				<input id="goodsBarcode" class="inputxt" name="goodsBarcode" value="${qsyMerchGoodsInfo.goodsBarcode}"  readonly="readonly"  />
                <span class="Validform_checktip"></span>
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
                <input id="goodPrice" class="inputxt" name="goodPrice" value="${qsyMerchGoodsInfo.goodsReferPrice/100}"  maxlength="12" datatype="/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/" errorMsg="请输入正确的价格" />
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
</html>
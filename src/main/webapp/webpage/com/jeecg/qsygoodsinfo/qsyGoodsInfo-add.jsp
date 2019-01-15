<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>商品信息表</title>
<t:base type="jquery,easyui,validform,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="qsyGoodsInfoController.do?doAdd">
	<input id="id" name="id" type="hidden" value="${qsyGoodsInfo.id }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="10%" nowrap><label class="Validform_label"> 商品条形码：</label></td>
			<td class="value" width="10%">
				<input id="goodsBarcode" class="inputxt" name="goodsBarcode" value="${qsyGoodsInfo.goodsBarcode}"  validType="qsy_goods_info,goods_barcode,id"  maxlength="64" datatype="*" ignore="checked"  />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 商品名称：</label></td>
			<td class="value">
                <input id="goodsName" class="inputxt" name="goodsName" value="${qsyGoodsInfo.goodsName}"  maxlength="64" datatype="*"  />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 商品规格：</label></td>
			<td class="value">
                <input id="goodsSpec" class="inputxt" name="goodsSpec" value="${qsyGoodsInfo.goodsSpec}"  maxlength="50" datatype="*"  />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label"> 商品分类：</label></td>
			<td class="value">
				<t:dictSelect field="goodsType" id="goodsType" datatype="*" typeGroupCode="goods_type" ></t:dictSelect>
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		<td align="right" nowrap><label class="Validform_label"> 商品参考价：</label></td>
			<td class="value">
                <input id="goodPrice" class="inputxt" name="goodPrice" value="${qsyGoodsInfo.goodsReferPrice}"  maxlength="12" datatype="/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/" errorMsg="请输入正确的价格" />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
			<td align="right" nowrap><label class="Validform_label"> 商标/品牌名称：</label></td>
			<td class="value">
                <input id="brandName" class="inputxt" name="brandName" value="${qsyGoodsInfo.brandName}"  maxlength="64" datatype="*"  />
                <span class="Validform_checktip"></span>
            </td>
		</tr>
	</table>
</t:formvalid>
</body>
</html>
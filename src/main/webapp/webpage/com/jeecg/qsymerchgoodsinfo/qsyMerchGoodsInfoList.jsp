<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="qsyMerchGoodsInfoList" checkbox="true" pagination="true" fitColumns="true" title="商户商品信息表" actionUrl="qsyMerchGoodsInfoController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属商户"  field="merchName"  hidden="true" dictionary="selectMerch,merchName@merchCode,merch_name@merch_code" query="true" popup="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商户名称"  field="merchCode"  dictionary="qsy_merch_base_info,merch_code,merch_name" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品条形码"  field="goodsBarcode"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品名称"  field="goodsName"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品规格"  field="goodsSpec"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品分类"  field="goodsType"  dictionary="goods_type" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品参考价"  field="goodsPrice" formatterjs="formatCurrency" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商标名称"  field="brandName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="售卖简称"  field="sellShortName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改时间"  field="updateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="qsyMerchGoodsInfoController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="qsyMerchGoodsInfoController.do?goAdd" funname="add"  width="600" height="310"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="qsyMerchGoodsInfoController.do?goUpdate" funname="update"  width="600" height="300"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="qsyMerchGoodsInfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="qsyMerchGoodsInfoController.do?goUpdate" funname="detail"  width="600" height="300"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  <input type="hidden" id="merchCode" name="merchCode">
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'qsyMerchGoodsInfoController.do?upload', "qsyMerchGoodsInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("qsyMerchGoodsInfoController.do?exportXls","qsyMerchGoodsInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("qsyMerchGoodsInfoController.do?exportXlsByT","qsyMerchGoodsInfoList");
}

//bootstrap列表图片格式化
function btListImgFormatter(value,row,index){
	return listFileImgFormat(value,"image");
}
//bootstrap列表文件格式化
function btListFileFormatter(value,row,index){
	return listFileImgFormat(value);
}

//列表文件图片 列格式化方法
function listFileImgFormat(value,type){
	var href='';
	if(value==null || value.length==0){
		return href;
	}
	var value1 = "img/server/"+value;
	if("image"==type){
 		href+="<img src='"+value1+"' width=30 height=30  onmouseover='tipImg(this)' onmouseout='moveTipImg()' style='vertical-align:middle'/>";
	}else{
 		if(value.indexOf(".jpg")>-1 || value.indexOf(".gif")>-1 || value.indexOf(".png")>-1){
 			href+="<img src='"+value1+"' onmouseover='tipImg(this)' onmouseout='moveTipImg()' width=30 height=30 style='vertical-align:middle'/>";
 		}else{
 			var value2 = "img/server/"+value+"?down=true";
 			href+="<a href='"+value2+"' class='ace_button' style='text-decoration:none;' target=_blank><u><i class='fa fa-download'></i>点击下载</u></a>";
 		}
	}
	return href;
}
// 金额格式化
function formatCurrency(value,row,index) {
	value = value/100;
	var b = false;
	if (value == null || value == "") return "0.00"; 
	value = value.toString();
	if(value.indexOf('-') != -1){
		b= true;
		value = value.substring(1,value.length);
	}
	if (/^\-?[0-9]+(.[0-9]+)?$/.test(value)){
		value = value.toString().replace(/^(\d*)$/, "$1."); 
		value = (value + "00").replace(/(\d*\.\d\d)\d*/, "$1"); 
		value = value.replace(".", ","); 
		var re = /(\d)(\d{3},)/; 
		while (re.test(value)) {
			value = value.replace(re, "$1,$2"); 
		}
		value = value.replace(/,(\d\d)$/, ".$1"); 
	}
	if(b){
		value = "-"+value;
	}
	return value;
}
    
</script>

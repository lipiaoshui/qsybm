<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="qsyOrderInfoList" extendParams="view: detailview,detailFormatter:detailFormatterFun,onExpandRow: onExpandRowFun" checkbox="true" pagination="true" fitColumns="true" title="订单信息表" actionUrl="qsyOrderInfoController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属商户"  field="merchName"  hidden="true" dictionary="selectMerch,merchName@merchCode,merch_name@merch_code" query="true" popup="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商户订单号"  field="orderNo"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商户名称"  field="merchCode"  dictionary="qsy_merch_base_info,merch_code,merch_name" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品简述"  field="goodsDesc"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="终端编号"  field="termCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付方式"  field="payType"  query="true"  dictionary="pay_type" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="渠道名称"  field="channelCode" dictionary="qsy_channel_info,channel_code,channel_name" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="渠道流水号"  field="channelSerialNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="授权码"  field="authorCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单金额"  field="orderAmt" formatterjs="formatCurrency" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单状态"  field="orderState" query="true"  queryMode="single" dictionary="order_state" width="120"></t:dgCol>
   <t:dgCol title="订单说明"  field="orderDesc"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单创建时间"  field="orderTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="交易完成时间"  field="tradeTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单时间" field="createTime"  formatter="yyyy-MM-dd"  hidden="true"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改时间"  field="updateTime"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="qsyOrderInfoController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="qsyOrderInfoController.do?goAdd" funname="add"  width="800" height="500"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="qsyOrderInfoController.do?goUpdate" funname="update"  width="800" height="500"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="qsyOrderInfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="qsyOrderInfoController.do?goUpdate" funname="detail"  width="800" height="500"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  <input type="hidden" id="merchCode" name="merchCode">
  </div>
 </div>
 <script src="plug-in/easyui/extends/datagrid-detailview.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
	 $("input[name='createTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
	 $("input[name='createTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});})
 });
 
//返回行明细内容的格式化函数。
 function detailFormatterFun() {
     var s = '<div class="orderInfoHidden" style="padding:2px;">' 
    		+ '		<div class="easyui-tabs" style="height:230px;width:800px;">' 
    		+ '			<div title="订单详情" style="padding:2px;">' 
    		+ '				<table class="jfrom_order_linetablelines" ></table>' 
    		+ '			</div>' 
    		+ '		</div>' 
    		+ '	</div>';
     return s;
 }
   
//当展开一行时触发 
 function onExpandRowFun(index, row) {
 	//把加上的子表tabs和datagrid初始化
     var tabs = $(this).datagrid('getRowDetail', index).find('div.easyui-tabs');
     tabs.tabs();
     var jfrom_order_linetablelines = $(this).datagrid('getRowDetail', index).find('table.jfrom_order_linetablelines');
     var jfrom_order_linedurl = 'qsyOrderInfoController.do?selectOrderDetailDatagrid&field=goodsName,goodsBarcode,goodPrice&orderNo=' + row.orderNo;
     jfrom_order_linetablelines.datagrid({
         singleSelect: true,
         loadMsg: '正在加载',
         fitColumns: true,
         height: '180',
         pageSize: 50,
         pageList: [50, 150, 200, 250, 300],
         border: false,
         url: jfrom_order_linedurl,
         idField: 'id',
         rownumbers: true,
         pagination: true,
         columns: [[
         {
             title: '商品条形码',
             field: 'goodsBarcode',
             align: 'left',
             width: 50
         },
         {
             title: '商品名称',
             field: 'goodsName',
             align: 'left',
             width: 50
         },
         {
             title: '商品价格',
             field: 'goodPrice',
             align: 'left',
             width: 50,
             formatter : function(value){
            	 return formatCurrency(value,0,0);
             }
         }
        /*  ,{
             title: '授权时间',
             field: 'createDate',
             align: 'left',
             width: 50,
             formatter : function(value){
                 var date = new Date(value);
                 var y = date.getFullYear();
                 var m = date.getMonth() + 1;
                 var d = date.getDate();
                 return y + '-' +m + '-' + d;
            	 }
             } */
         ]]
     });
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'qsyOrderInfoController.do?upload', "qsyOrderInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("qsyOrderInfoController.do?exportXls","qsyOrderInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("qsyOrderInfoController.do?exportXlsByT","qsyOrderInfoList");
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

//金额格式化
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

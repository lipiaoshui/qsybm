<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#getGoods").click(function(){
			getGoodsInfo();
		})
		function getGoodsInfo(){
		$.ajax({
			url : 'api/getGoodsInfo.do',
			type : 'POST',
			data : '{"termCode":"ZD19010300001","randomStr":"17c67bf88f074dd7b6d18f127f38cdb6","sign":"6B97C352B2CE27A0E4CEAF29C1BF04F5","goodsBarcode":"10086"}',
			contentType:'application/json;charset=utf-8',
			dataType : 'json',
			success : function(data){
				console.log(data);
				var result = JSON.stringify(data);
				$('#result').text(result);
			}
		})
		}
	$("#uploadOrder").click(function(){
		uploadOrderInfo();
	})
	function uploadOrderInfo(){
		$.ajax({
			url : 'api/uploadOrderInfo.do',
			type : 'POST',
			data : '{"termCode":"ZD19010300001","randomStr":"17c67bf88f074dd7b6d18f127f38cdb6","sign":"062CE2D7A3CF5069B688F198AC1E9293","payCode":"00001","payType":"01","orderAmt":16000,"goodsList":[{"goodsBarcode":"101","goodsName":"可口可乐","goodsPrice":1000},{"goodsBarcode":"","goodsName":"其他商品","goodsPrice":15000}]}',
			contentType:'application/json;charset=utf-8',
			dataType : 'json',
			success : function(data){
				console.log(data);
				var uploadOrderInfo = JSON.stringify(data);
				$('#uploadOrderInfo').text(uploadOrderInfo);
			}
		})
	}
	$("#uploadRefund").click(function(){
		uploadRefundInfo();
	})
	function uploadRefundInfo(){
		$.ajax({
			url : 'api/uploadRefundInfo.do',
			type : 'POST',
			data : '{"termCode":"ZD19010300001","randomStr":"17c67bf88f074dd7b6d18f127f38cdb6","sign":"89C4BB8417E6D65A848C436484160CE0","orderNo":"20190110172841644W8Hxsp"}',
			contentType:'application/json;charset=utf-8',
			dataType : 'json',
			cache : false,
			success : function(data){
				console.log(data);
				var uploadRefundInfo = JSON.stringify(data);
				$('#uploadRefundInfo').text(uploadRefundInfo);
			}
		})
	}
 });
</script>
</head>
<body>
<input type="button" id="getGoods" value="getGoodsInfo按钮">
<div>返回结果</div>
<p id="result" ></p>
<input type="button" id="uploadOrder" value="uploadOrderInfo按钮">
<div>返回结果</div>
<p id="uploadOrderInfo"></p>
<input type="button" id="uploadRefund" value="uploadRefundInfo按钮">
<div>返回结果</div>
<p id="uploadRefundInfo"></p>
</body>
</html>
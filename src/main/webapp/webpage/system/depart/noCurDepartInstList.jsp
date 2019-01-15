<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<%--非当前组织机构的用户列表--%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_depart_list" class="easyui-layout" fit="true">
    <div region="center"  style="padding:0px;border:0px">
        <t:datagrid name="noCurDepartUserList" title="common.operation" singleSelect="true"
                    actionUrl="departController.do?noCurDepartInstDatagrid&orgCode=${orgCode}" fit="true" fitColumns="true"
                    idField="id" checkbox="true" queryMode="group">
            <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
    <t:dgCol title="是否挂属" field="type"   dictionary="ascription"   defaultVal="0"  hidden="true"   width="100" query="true"></t:dgCol>
	<t:dgCol title="机构名称" sortable="false" width="100" field="instName" query="true"></t:dgCol>
<%-- 	<t:dgCol title="所属机构编号" field="orgCode" width="100" query="true"></t:dgCol>
 --%>        </t:datagrid>
    </div>
</div>

<div style="display: none">
    <t:formvalid formid="formobj" layout="div" dialog="true" action="departController.do?doAddOrgToOrg&orgCode=${orgCode}" beforeSubmit="setUserIds">
        <input id="instId" name="instId">
        <input id="orgCode" name="orgCode" value="${orgCode}">
    </t:formvalid>
</div>
<script>
var isConfirm = true;
var count = 0;
    function setUserIds() {
    	count++;
    	//如果是奇数并且是已经通过一次了。重置弹窗出来
    	if(count%2 == 1 && !isConfirm){
    		isConfirm = true
    		}
        $("#instId").val(getUserListSelections('id'));
        var instId = $("#instId").val();
        var orgCode = $("#orgCode").val();
		var offset = ['60px','0px'];
    	$.ajax({
    		async : false,
    		cache : false,
    		type : 'POST',
    		data : {orgCode:orgCode,instId,instId},
    		url : "departController.do?isAssgin",
    		error : function() {
    		},
    		success : function(data) {
    			var d = $.parseJSON(data);
    			if (d.success) {
    				isConfirm =false;
    			} else {
    				if(d.attributes){
						//无效的点击
						count--;
    					tip(d.attributes.beyond);
    				}else{
	     				if(isConfirm){
		    				layer.open({
		    					title:"提醒",
		    					content:d.msg,
		    					icon:7,
		    					shade: 0.3,
		    					offset:offset,
		    					yes:function(index){
		    	    				pass = true;
		    	    				isConfirm =false;
		    						$('#btn_sub').click();
		    						layer.close(index);
		    					},
		    					btn:[$.i18n.prop('common.ok'),$.i18n.prop('common.cancel')],
		    					btn2:function(index){
		    						//点取消按钮。这次提交事件不计数
		    						count--;
		    						layer.close(index);
		    					}
		    				});	
	    				} 
    				}
    			}
    		}
    	});
        return !isConfirm;
    }

    function getUserListSelections(field) {
        var ids = [];
        var rows = $('#noCurDepartUserList').datagrid('getSelections');
        for (var i = 0; i < rows.length; i++) {
            ids.push(rows[i][field]);
        }
        ids.join(',');
        return ids
    }
</script>

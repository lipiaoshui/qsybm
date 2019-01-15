<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>common.notice</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="plug-in/ueditor/ueditor.all.js"> </script>
  <script type="text/javascript">
  //编写自定义JS代码
/*   function setContent(){
	    if(editor.queryCommandState( 'source' ))
	    	editor.execCommand('source');//切换到编辑模式才提交，否则有bug
	            
	    if(editor.hasContents()){
	    	editor.sync();
		    $("#noticeContent").val(editor.getContent());
		}
	}
  
  function dataytpeSelect(name) {
      $("#roleName").removeAttr('datatype');
      $("#roleName_span").hide()
      $("#userName").removeAttr('datatype');
      $("#userName_span").hide()
      if (name){
          $("#"+name).attr('datatype','*');
          $("#"+name+"_span").show()
      }
  }
 */
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="noticeController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${tSNoticePage.id}">
		<table style="width:100%" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="noticeTitle" name="noticeTitle" type="text" value="${tSNoticePage.noticeTitle}" style="width:95%" class="inputxt"  >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">通知标题</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容:
						</label>
					</td>
					<td class="value">
						  	 <textarea id="noticeContent" name="noticeContent"  rows="35" cols="100%">
						  	 
						  	 ${tSNoticePage.noticeContent}
						  	 </textarea> 

						</td>
			</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发布者:
						</label>
					</td>
					<td class="value">

							<span class="Validform_checktip"></span>
							<label class="Validform_label" > ${tSNoticePage.createUser}</label>
						</td>
				</tr>
				<tr> 
					<td align="right">
						<label class="Validform_label" style="white-space:nowrap;">
							创建时间:
						</label>
					</td>
					<td class="value">
							<input id="noticeTerm" name="noticeTerm" type="text" style="width: 150px" value="${tSNoticePage.createTime}" class="Wdate" onClick="WdatePicker()" >    
						</td>
				</tr>
				<tr> 
					<td align="right">
						<label class="Validform_label" style="white-space:nowrap;">
							修改时间:
						</label>
					</td>
					<td class="value">
							<input id="noticeTerm" name="noticeTerm" type="text" style="width: 150px" value="${tSNoticePage.updateTime}" class="Wdate" onClick="WdatePicker()" >    
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
		
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jb.model.TjbSafetime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String url = request.getContextPath()+"/jbSafetimeController/add";
%>
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	$(function() {
	 	parent.$.messager.progress('close');
		$('#safeadd_Form').form({
			url : '<%=url%>',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				$("#safeadd_result").text(result);
			}
		});
	});
</script>

	<div class="easyui-layout" data-options="fit:true">
		
		<div data-options="region:'center'">
			<form id="safeadd_Form" action="">
				<table align="center" width="90%" class="tablex">
					<tr>
						<td align="right" style="width: 80px;"><label>url：</label></td>
						<td><%=url%></td>
					</tr>
					<tr>
						<td align="right" style="width: 180px;"><label>sessionId(sessionId)*：</label></td>
						<td><input name="sessionId" type="text" class="span2" value=""/></td>
					</tr>
					<tr>	
												
					<th>startTime(<%=TjbSafetime.ALIAS_START_TIME%>)</th>	
					<td>
					<input class="span2" name="startTime" type="text" onclick="WdatePicker({dateFmt:'<%=TjbSafetime.FORMAT_START_TIME%>'})"   maxlength="0" value=""/>
					</td>	
					<th>endTime(<%=TjbSafetime.ALIAS_END_TIME%>)</th>	
					<td>
					<input class="span2" name="endTime" type="text" onclick="WdatePicker({dateFmt:'<%=TjbSafetime.FORMAT_END_TIME%>'})"   maxlength="0" value=""/>
					</td>						
			</tr>	
			<tr>	
					<th>uid(测点)</th>	
					<td>
											<input class="span2" name="uid" type="text" value=""/>
					</td>								
					<th>status(<%=TjbSafetime.ALIAS_STATUS%>，SS01启用.SS02不启用)</th>	
					<td>
											<jb:select dataType="SS" name="status" value=""></jb:select>	
					</td>							
			</tr>	
					<tr>
						<td colspan="2" align="center">
						<input type="button"
							value="提交" onclick="javascript:$('#safeadd_Form').submit();" /></td>
					</tr>
				</table>
			</form>
			<label>结果：</label>
				<div id="safeadd_result">
				</div>
			<div>
				结果说明：1、json格式<br/>
					2、success:true 成功<br/>
			</div>
		</div>
	</div>
</body>
</html>
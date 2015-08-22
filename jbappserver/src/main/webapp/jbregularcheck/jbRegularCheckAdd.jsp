<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TjbRegularCheck" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/jbRegularCheckController/add',
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
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">	
		<form id="form" method="post">		
				<input type="hidden" name="id"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TjbRegularCheck.ALIAS_MAINTAIN_ID%></th>	
					<td>
											<input class="span2" name="maintainId" type="text"/>
					</td>							
					<th><%=TjbRegularCheck.ALIAS_CHECK_DATE%></th>	
					<td>
					<input class="span2" name="checkDate" type="text" onclick="WdatePicker({dateFmt:'<%=TjbRegularCheck.FORMAT_CHECK_DATE%>'})"  maxlength="0" class="" />
					</td>							
				</tr>	
				<tr>	
					<th><%=TjbRegularCheck.ALIAS_CHECK_ORDER%></th>	
					<td>
											<input class="span2" name="checkOrder" type="text"/>
					</td>							
					<th><%=TjbRegularCheck.ALIAS_CHECK_PEOPLE%></th>	
					<td>
											<input class="span2" name="checkPeople" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TjbRegularCheck.ALIAS_CHECK_PHONE%></th>	
					<td>
											<input class="span2" name="checkPhone" type="text"/>
					</td>							
					<th><%=TjbRegularCheck.ALIAS_SUMMARY%></th>	
					<td>
											<input class="span2" name="summary" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TjbRegularCheck.ALIAS_SCAN_FILE%></th>	
					<td>
											<input class="span2" name="scanFile" type="text"/>
					</td>							
					<th><%=TjbRegularCheck.ALIAS_REMARK%></th>	
					<td>
											<input class="span2" name="remark" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TjbRegularCheck.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TjbRegularCheck.FORMAT_ADDTIME%>'})"  maxlength="0" class="" />
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>
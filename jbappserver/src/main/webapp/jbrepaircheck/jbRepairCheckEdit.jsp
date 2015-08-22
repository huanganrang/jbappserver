<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TjbRepairCheck" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/jbRepairCheckController/edit',
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
				<input type="hidden" name="id" value = "${jbRepairCheck.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TjbRepairCheck.ALIAS_MAINTAIN_ID%></th>	
					<td>
											<input class="span2" name="maintainId" type="text" value="${jbRepairCheck.maintainId}"/>
					</td>							
					<th><%=TjbRepairCheck.ALIAS_REPAIR_DATE%></th>	
					<td>
					<input class="span2" name="repairDate" type="text" onclick="WdatePicker({dateFmt:'<%=TjbRepairCheck.FORMAT_REPAIR_DATE%>'})"   maxlength="0" value="${jbRepairCheck.repairDate}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbRepairCheck.ALIAS_REPAIR_ORDER%></th>	
					<td>
											<input class="span2" name="repairOrder" type="text" value="${jbRepairCheck.repairOrder}"/>
					</td>							
					<th><%=TjbRepairCheck.ALIAS_REPAIR_PEOPLE%></th>	
					<td>
											<input class="span2" name="repairPeople" type="text" value="${jbRepairCheck.repairPeople}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbRepairCheck.ALIAS_REPAIR_PHONE%></th>	
					<td>
											<input class="span2" name="repairPhone" type="text" value="${jbRepairCheck.repairPhone}"/>
					</td>							
					<th><%=TjbRepairCheck.ALIAS_SUMMARY%></th>	
					<td>
											<input class="span2" name="summary" type="text" value="${jbRepairCheck.summary}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbRepairCheck.ALIAS_SCAN_FILE%></th>	
					<td>
											<input class="span2" name="scanFile" type="text" value="${jbRepairCheck.scanFile}"/>
					</td>							
					<th><%=TjbRepairCheck.ALIAS_REMARK%></th>	
					<td>
											<input class="span2" name="remark" type="text" value="${jbRepairCheck.remark}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbRepairCheck.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TjbRepairCheck.FORMAT_ADDTIME%>'})"   maxlength="0" value="${jbRepairCheck.addtime}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>
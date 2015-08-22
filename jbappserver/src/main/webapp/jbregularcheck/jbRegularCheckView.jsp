<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TjbRegularCheck" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TjbRegularCheck.ALIAS_MAINTAIN_ID%></th>	
					<td>
						${jbRegularCheck.maintainId}							
					</td>							
					<th><%=TjbRegularCheck.ALIAS_CHECK_DATE%></th>	
					<td>
						${jbRegularCheck.checkDate}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbRegularCheck.ALIAS_CHECK_ORDER%></th>	
					<td>
						${jbRegularCheck.checkOrder}							
					</td>							
					<th><%=TjbRegularCheck.ALIAS_CHECK_PEOPLE%></th>	
					<td>
						${jbRegularCheck.checkPeople}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbRegularCheck.ALIAS_CHECK_PHONE%></th>	
					<td>
						${jbRegularCheck.checkPhone}							
					</td>							
					<th><%=TjbRegularCheck.ALIAS_SUMMARY%></th>	
					<td>
						${jbRegularCheck.summary}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbRegularCheck.ALIAS_SCAN_FILE%></th>	
					<td>
						${jbRegularCheck.scanFile}							
					</td>							
					<th><%=TjbRegularCheck.ALIAS_REMARK%></th>	
					<td>
						${jbRegularCheck.remark}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbRegularCheck.ALIAS_ADDTIME%></th>	
					<td>
						${jbRegularCheck.addtime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>
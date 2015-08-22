<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TjbRepairCheck" %>
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
					<th><%=TjbRepairCheck.ALIAS_MAINTAIN_ID%></th>	
					<td>
						${jbRepairCheck.maintainId}							
					</td>							
					<th><%=TjbRepairCheck.ALIAS_REPAIR_DATE%></th>	
					<td>
						${jbRepairCheck.repairDate}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbRepairCheck.ALIAS_REPAIR_ORDER%></th>	
					<td>
						${jbRepairCheck.repairOrder}							
					</td>							
					<th><%=TjbRepairCheck.ALIAS_REPAIR_PEOPLE%></th>	
					<td>
						${jbRepairCheck.repairPeople}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbRepairCheck.ALIAS_REPAIR_PHONE%></th>	
					<td>
						${jbRepairCheck.repairPhone}							
					</td>							
					<th><%=TjbRepairCheck.ALIAS_SUMMARY%></th>	
					<td>
						${jbRepairCheck.summary}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbRepairCheck.ALIAS_SCAN_FILE%></th>	
					<td>
						${jbRepairCheck.scanFile}							
					</td>							
					<th><%=TjbRepairCheck.ALIAS_REMARK%></th>	
					<td>
						${jbRepairCheck.remark}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbRepairCheck.ALIAS_ADDTIME%></th>	
					<td>
						${jbRepairCheck.addtime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TjbMaintainBase" %>
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
					<th><%=TjbMaintainBase.ALIAS_ASSET_ID%></th>	
					<td>
						${jbMaintainBase.assetId}							
					</td>							
					<th><%=TjbMaintainBase.ALIAS_MAINTAIN_UNIT%></th>	
					<td>
						${jbMaintainBase.maintainUnit}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbMaintainBase.ALIAS_TELPHONE%></th>	
					<td>
						${jbMaintainBase.telphone}							
					</td>							
					<th><%=TjbMaintainBase.ALIAS_END_DATE%></th>	
					<td>
						${jbMaintainBase.endDate}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbMaintainBase.ALIAS_CHECK_CYCLE%></th>	
					<td>
						${jbMaintainBase.checkCycle}							
					</td>							
					<th><%=TjbMaintainBase.ALIAS_CHECK_MODE%></th>	
					<td>
						${jbMaintainBase.checkMode}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbMaintainBase.ALIAS_NEXT_CHECK_DATE%></th>	
					<td>
						${jbMaintainBase.nextCheckDate}							
					</td>							
					<th><%=TjbMaintainBase.ALIAS_REMARK%></th>	
					<td>
						${jbMaintainBase.remark}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TjbMaintainBase.ALIAS_ADDTIME%></th>	
					<td>
						${jbMaintainBase.addtime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>
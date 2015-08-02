<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.Tappuser" %>
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
					<th><%=Tappuser.ALIAS_USERNAME%></th>	
					<td>
						${appuser.username}							
					</td>							
					<th><%=Tappuser.ALIAS_GROUP%></th>	
					<td>
						${appuser.group}							
					</td>							
				</tr>		
				<tr>	
					<th><%=Tappuser.ALIAS_FROM_IP%></th>	
					<td>
						${appuser.fromIp}							
					</td>							
				</tr>		
		</table>
	</div>
</div>
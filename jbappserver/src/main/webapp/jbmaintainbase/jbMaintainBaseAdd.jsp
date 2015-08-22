<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TjbMaintainBase" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/jbMaintainBaseController/add',
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
					<th><%=TjbMaintainBase.ALIAS_ASSET_ID%></th>	
					<td>
											<input class="span2" name="assetId" type="text"/>
					</td>							
					<th><%=TjbMaintainBase.ALIAS_MAINTAIN_UNIT%></th>	
					<td>
											<input class="span2" name="maintainUnit" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TjbMaintainBase.ALIAS_TELPHONE%></th>	
					<td>
											<input class="span2" name="telphone" type="text"/>
					</td>							
					<th><%=TjbMaintainBase.ALIAS_END_DATE%></th>	
					<td>
					<input class="span2" name="endDate" type="text" onclick="WdatePicker({dateFmt:'<%=TjbMaintainBase.FORMAT_END_DATE%>'})"  maxlength="0" class="" />
					</td>							
				</tr>	
				<tr>	
					<th><%=TjbMaintainBase.ALIAS_CHECK_CYCLE%></th>	
					<td>
											<input class="span2" name="checkCycle" type="text"/>
					</td>							
					<th><%=TjbMaintainBase.ALIAS_CHECK_MODE%></th>	
					<td>
											<input class="span2" name="checkMode" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TjbMaintainBase.ALIAS_NEXT_CHECK_DATE%></th>	
					<td>
					<input class="span2" name="nextCheckDate" type="text" onclick="WdatePicker({dateFmt:'<%=TjbMaintainBase.FORMAT_NEXT_CHECK_DATE%>'})"  maxlength="0" class="" />
					</td>							
					<th><%=TjbMaintainBase.ALIAS_REMARK%></th>	
					<td>
											<input class="span2" name="remark" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TjbMaintainBase.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TjbMaintainBase.FORMAT_ADDTIME%>'})"  maxlength="0" class="" />
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>
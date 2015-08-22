<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TjbAssets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/jbAssetsController/edit',
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
				<input type="hidden" name="id" value = "${jbAssets.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TjbAssets.ALIAS_ASSET_NUMBER%></th>	
					<td>
											<input class="span2" name="assetNumber" type="text" value="${jbAssets.assetNumber}"/>
					</td>							
					<th><%=TjbAssets.ALIAS_FIRST_CATEGORY%></th>	
					<td>
											<jb:select dataType="AT" name="firstCategory" value="${jbAssets.firstCategory}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_SECOND_CATEGORY%></th>	
					<td>
											<jb:select dataType="SC" name="secondCategory" value="${jbAssets.secondCategory}"></jb:select>	
					</td>							
					<th><%=TjbAssets.ALIAS_DESCRIPTION%></th>	
					<td>
											<input class="span2" name="description" type="text" value="${jbAssets.description}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_FACTORY%></th>	
					<td>
											<jb:select dataType="FY" name="factory" value="${jbAssets.factory}"></jb:select>	
					</td>							
					<th><%=TjbAssets.ALIAS_ASSET_TYPE%></th>	
					<td>
											<jb:select dataType="TN" name="assetType" value="${jbAssets.assetType}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_SERIAL_NUMBER%></th>	
					<td>
											<input class="span2" name="serialNumber" type="text" value="${jbAssets.serialNumber}"/>
					</td>							
					<th><%=TjbAssets.ALIAS_LOCATION%></th>	
					<td>
											<input class="span2" name="location" type="text" value="${jbAssets.location}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_DEPT_ID%></th>	
					<td>
											<jb:select dataType="SL02" name="deptId" value="${jbAssets.deptId}"></jb:select>	
					</td>							
					<th><%=TjbAssets.ALIAS_PRINCIPAL%></th>	
					<td>
											<input class="span2" name="principal" type="text" value="${jbAssets.principal}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_BUY_DATE%></th>	
					<td>
					<input class="span2" name="buyDate" type="text" onclick="WdatePicker({dateFmt:'<%=TjbAssets.FORMAT_BUY_DATE%>'})"   maxlength="0" value="${jbAssets.buyDate}"/>
					</td>							
					<th><%=TjbAssets.ALIAS_MAKE_DATE%></th>	
					<td>
					<input class="span2" name="makeDate" type="text" onclick="WdatePicker({dateFmt:'<%=TjbAssets.FORMAT_MAKE_DATE%>'})"   maxlength="0" value="${jbAssets.makeDate}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_SUPPLIER%></th>	
					<td>
											<input class="span2" name="supplier" type="text" value="${jbAssets.supplier}"/>
					</td>							
					<th><%=TjbAssets.ALIAS_SUPPLIER_PHONE%></th>	
					<td>
											<input class="span2" name="supplierPhone" type="text" value="${jbAssets.supplierPhone}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_MEASURE%></th>	
					<td>
											<input class="span2" name="measure" type="text" value="${jbAssets.measure}"/>
					</td>							
					<th><%=TjbAssets.ALIAS_WEIGHT%></th>	
					<td>
											<input class="span2" name="weight" type="text" value="${jbAssets.weight}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_STANDARD_POWER%></th>	
					<td>
											<input class="span2" name="standardPower" type="text" value="${jbAssets.standardPower}"/>
					</td>							
					<th><%=TjbAssets.ALIAS_ICON%></th>	
					<td>
											<input class="span2" name="icon" type="text" value="${jbAssets.icon}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_PARENT_ID%></th>	
					<td>
											<jb:select dataType="SL03" name="parentId" value="${jbAssets.parentId}"></jb:select>	
					</td>							
					<th><%=TjbAssets.ALIAS_ROOM_AREA_ID%></th>	
					<td>
											<jb:select dataType="SL04" name="roomAreaId" value="${jbAssets.roomAreaId}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_SCOPE%></th>	
					<td>
											<input class="span2" name="scope" type="text" value="${jbAssets.scope}"/>
					</td>							
					<th><%=TjbAssets.ALIAS_UPLACE%></th>	
					<td>
											<input class="span2" name="uplace" type="text" value="${jbAssets.uplace}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TjbAssets.ALIAS_UID%></th>	
					<td>
											<input class="span2" name="uid" type="text" value="${jbAssets.uid}"/>
					</td>							
					<th><%=TjbAssets.ALIAS_ADDTIME%></th>	
					<td>
					<input class="span2" name="addtime" type="text" onclick="WdatePicker({dateFmt:'<%=TjbAssets.FORMAT_ADDTIME%>'})"   maxlength="0" value="${jbAssets.addtime}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>
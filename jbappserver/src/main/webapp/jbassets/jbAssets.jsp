<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.TjbAssets" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>JbAssets管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/jbAssetsController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/jbAssetsController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/jbAssetsController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/jbAssetsController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'id',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
				}, {
				field : 'assetNumber',
				title : '<%=TjbAssets.ALIAS_ASSET_NUMBER%>',
				width : 50		
				}, {
				field : 'firstCategory',
				title : '<%=TjbAssets.ALIAS_FIRST_CATEGORY%>',
				width : 50		
				}, {
				field : 'secondCategory',
				title : '<%=TjbAssets.ALIAS_SECOND_CATEGORY%>',
				width : 50		
				}, {
				field : 'description',
				title : '<%=TjbAssets.ALIAS_DESCRIPTION%>',
				width : 50		
				}, {
				field : 'factory',
				title : '<%=TjbAssets.ALIAS_FACTORY%>',
				width : 50		
				}, {
				field : 'assetType',
				title : '<%=TjbAssets.ALIAS_ASSET_TYPE%>',
				width : 50		
				}, {
				field : 'serialNumber',
				title : '<%=TjbAssets.ALIAS_SERIAL_NUMBER%>',
				width : 50		
				}, {
				field : 'location',
				title : '<%=TjbAssets.ALIAS_LOCATION%>',
				width : 50		
				}, {
				field : 'deptId',
				title : '<%=TjbAssets.ALIAS_DEPT_ID%>',
				width : 50		
				}, {
				field : 'principal',
				title : '<%=TjbAssets.ALIAS_PRINCIPAL%>',
				width : 50		
				}, {
				field : 'buyDate',
				title : '<%=TjbAssets.ALIAS_BUY_DATE%>',
				width : 50		
				}, {
				field : 'makeDate',
				title : '<%=TjbAssets.ALIAS_MAKE_DATE%>',
				width : 50		
				}, {
				field : 'supplier',
				title : '<%=TjbAssets.ALIAS_SUPPLIER%>',
				width : 50		
				}, {
				field : 'supplierPhone',
				title : '<%=TjbAssets.ALIAS_SUPPLIER_PHONE%>',
				width : 50		
				}, {
				field : 'measure',
				title : '<%=TjbAssets.ALIAS_MEASURE%>',
				width : 50		
				}, {
				field : 'weight',
				title : '<%=TjbAssets.ALIAS_WEIGHT%>',
				width : 50		
				}, {
				field : 'standardPower',
				title : '<%=TjbAssets.ALIAS_STANDARD_POWER%>',
				width : 50		
				}, {
				field : 'icon',
				title : '<%=TjbAssets.ALIAS_ICON%>',
				width : 50		
				}, {
				field : 'parentId',
				title : '<%=TjbAssets.ALIAS_PARENT_ID%>',
				width : 50		
				}, {
				field : 'roomAreaId',
				title : '<%=TjbAssets.ALIAS_ROOM_AREA_ID%>',
				width : 50		
				}, {
				field : 'scope',
				title : '<%=TjbAssets.ALIAS_SCOPE%>',
				width : 50		
				}, {
				field : 'uplace',
				title : '<%=TjbAssets.ALIAS_UPLACE%>',
				width : 50		
				}, {
				field : 'uid',
				title : '<%=TjbAssets.ALIAS_UID%>',
				width : 50		
				}, {
				field : 'addtime',
				title : '<%=TjbAssets.ALIAS_ADDTIME%>',
				width : 50		
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_edit.png');
					}
					str += '&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_delete.png');
					}
					str += '&nbsp;';
					if ($.canView) {
						str += $.formatString('<img onclick="viewFun(\'{0}\');" src="{1}" title="查看"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_link.png');
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			}
		});
	});

	function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除当前数据？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/jbAssetsController/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
	}

	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '编辑数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/jbAssetsController/editPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function viewFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '查看数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/jbAssetsController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/jbAssetsController/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	function downloadTable(){
		var options = dataGrid.datagrid("options");
		var $colums = [];		
		$.merge($colums, options.columns); 
		$.merge($colums, options.frozenColumns);
		var columsStr = JSON.stringify($colums);
	    $('#downloadTable').form('submit', {
	        url:'${pageContext.request.contextPath}/jbAssetsController/download',
	        onSubmit: function(param){
	        	$.extend(param, $.serializeObject($('#searchForm')));
	        	param.downloadFields = columsStr;
	        	param.page = options.pageNumber;
	        	param.rows = options.pageSize;
	        	
       	 }
        }); 
	}
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 160px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;">
						<tr>	
							<th><%=TjbAssets.ALIAS_ASSET_NUMBER%></th>	
							<td>
											<input type="text" name="assetNumber" maxlength="32" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_FIRST_CATEGORY%></th>	
							<td>
											<jb:select dataType="AT" name="firstCategory"></jb:select>	
							</td>
							<th><%=TjbAssets.ALIAS_SECOND_CATEGORY%></th>	
							<td>
											<jb:select dataType="SC" name="secondCategory"></jb:select>	
							</td>
							<th><%=TjbAssets.ALIAS_DESCRIPTION%></th>	
							<td>
											<input type="text" name="description" maxlength="512" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TjbAssets.ALIAS_FACTORY%></th>	
							<td>
											<jb:select dataType="FY" name="factory"></jb:select>	
							</td>
							<th><%=TjbAssets.ALIAS_ASSET_TYPE%></th>	
							<td>
											<jb:select dataType="TN" name="assetType"></jb:select>	
							</td>
							<th><%=TjbAssets.ALIAS_SERIAL_NUMBER%></th>	
							<td>
											<input type="text" name="serialNumber" maxlength="64" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_LOCATION%></th>	
							<td>
											<input type="text" name="location" maxlength="128" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TjbAssets.ALIAS_DEPT_ID%></th>	
							<td>
											<jb:select dataType="SL02" name="deptId"></jb:select>	
							</td>
							<th><%=TjbAssets.ALIAS_PRINCIPAL%></th>	
							<td>
											<input type="text" name="principal" maxlength="36" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_BUY_DATE%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TjbAssets.FORMAT_BUY_DATE%>'})" id="buyDateBegin" name="buyDateBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TjbAssets.FORMAT_BUY_DATE%>'})" id="buyDateEnd" name="buyDateEnd"/>
							</td>
							<th><%=TjbAssets.ALIAS_MAKE_DATE%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TjbAssets.FORMAT_MAKE_DATE%>'})" id="makeDateBegin" name="makeDateBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TjbAssets.FORMAT_MAKE_DATE%>'})" id="makeDateEnd" name="makeDateEnd"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TjbAssets.ALIAS_SUPPLIER%></th>	
							<td>
											<input type="text" name="supplier" maxlength="128" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_SUPPLIER_PHONE%></th>	
							<td>
											<input type="text" name="supplierPhone" maxlength="32" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_MEASURE%></th>	
							<td>
											<input type="text" name="measure" maxlength="32" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_WEIGHT%></th>	
							<td>
											<input type="text" name="weight" maxlength="12" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TjbAssets.ALIAS_STANDARD_POWER%></th>	
							<td>
											<input type="text" name="standardPower" maxlength="12" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_ICON%></th>	
							<td>
											<input type="text" name="icon" maxlength="128" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_PARENT_ID%></th>	
							<td>
											<jb:select dataType="SL03" name="parentId"></jb:select>	
							</td>
							<th><%=TjbAssets.ALIAS_ROOM_AREA_ID%></th>	
							<td>
											<jb:select dataType="SL04" name="roomAreaId"></jb:select>	
							</td>
						</tr>	
						<tr>	
							<th><%=TjbAssets.ALIAS_SCOPE%></th>	
							<td>
											<input type="text" name="scope" maxlength="128" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_UPLACE%></th>	
							<td>
											<input type="text" name="uplace" maxlength="10" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_UID%></th>	
							<td>
											<input type="text" name="uid" maxlength="32" class="span2"/>
							</td>
							<th><%=TjbAssets.ALIAS_ADDTIME%></th>	
							<td>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TjbAssets.FORMAT_ADDTIME%>'})" id="addtimeBegin" name="addtimeBegin"/>
								<input type="text" class="span2" onclick="WdatePicker({dateFmt:'<%=TjbAssets.FORMAT_ADDTIME%>'})" id="addtimeEnd" name="addtimeEnd"/>
							</td>
						</tr>	
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/jbAssetsController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'bug_add'">添加</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/jbAssetsController/download')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'server_go',plain:true" onclick="downloadTable();">导出</a>		
			<form id="downloadTable" target="downloadIframe" method="post" style="display: none;">
			</form>
			<iframe id="downloadIframe" name="downloadIframe" style="display: none;"></iframe>
		</c:if>
	</div>	
</body>
</html>
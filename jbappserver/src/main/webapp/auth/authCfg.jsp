<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.model.Tappuser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>权限管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.3/datagrid-filter.js"></script>
<script type="text/javascript">
var dataGrid;
var resourceTree;
var _form;
function isCedian(node){
	if(node.attributes){
		if(node.attributes.spotlist){
			return true;
		}
	}
	return false;
}
$(function() {
	parent.$.messager.progress('close');
	dataGrid = $('#dataGridGroup').datagrid({
		url : '${pageContext.request.contextPath}/authCfgController/dataGridGroup',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'nGroupNo',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		frozenColumns : [ [ {
			field : 'nGroupNo',
			title : '编号',
			width : 150,
			hidden : true
		}] ],
		columns : [ [ {
			field : 'sGroupName',
			title : '用户组',
			width : 200
		} ] ],
		toolbar : '#toolbar',
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
			$(this).datagrid('tooltip');
			/* $(this).datagrid('enableFilter',[{
				field:'sGroupName',
				type:'textbox'
				}]); */
		},
		onDblClickRow :function(index,row){
			if(dataGrid.selectedRow == row.nGroupNo)
				return;
			dataGrid.selectedRow = row.nGroupNo;
			resourceTree.tree({url:'${pageContext.request.contextPath}/authCfgController/allTree?nGroupNo='+row.nGroupNo})
			$("#cfgtable tr").remove();
			//resourceTree.tree('reload');
		}
	});
	
	resourceTree = $('#resourceTree').tree({
		//url : '${pageContext.request.contextPath}/authCfgController/allTree',
		parentField : 'pid',
		//lines : true,
		checkbox : true,
		onClick : function(node) {
			$("#cfgtable tr").remove();
			if(isCedian(node)){
				var sons = node.attributes.spotlist;
				var rowno = Math.ceil(sons.length/4);
				for(var i = 0;i<rowno;i++){
					appandrow();
				}
				var tds = $('.right td.drop');
				for(var i = 0 ;i<sons.length;i++){
					var item = sons[i];
					var checkcalss = "";
					if(item.checked)
						checkcalss = " cdchecked";
					tds.eq(i).append('<div id="'+item.id+'" class="item assigned'+checkcalss+'" >'+item.text+'</div>');
				}
				dropEvent();
			}
		},
		onLoadSuccess : function(node, data) {			
			//$('#roleGrantLayout').layout('panel', 'west').panel('setTitle', $.formatString('[{0}]角色可以访问的资源', '${role.name}'));
			parent.$.messager.progress('close');
		},
		cascadeCheck : false
	});
	
	_form = $('#form').form({
		url : '${pageContext.request.contextPath}/authCfgController/grant',
		onSubmit : function() {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			var isValid = $(this).form('validate');
			if (!isValid) {
				parent.$.messager.progress('close');
			}
			var checknodes = resourceTree.tree('getChecked');
			var ids = [];
			var uids = [];
			if (checknodes && checknodes.length > 0) {
				for ( var i = 0; i < checknodes.length; i++) {
					var checkNode = checknodes[i];
					if(checkNode.attributes&&checkNode.attributes.kw){
						uids.push(checkNode.id);
					}else{
						ids.push(checkNode.id);
					}
					
					if(checkNode.attributes&&checkNode.attributes.spotlist){
						var sons = checkNode.attributes.spotlist;
						for(var mm = 0 ;mm<sons.length;mm++){
							var item = sons[mm];
							if(item.checked)
							uids.push(item.id);
						}
					}
				}
			}			
			// var datarow = dataGrid.datagrid('getSelected');
			$('#nGroupNo').val(dataGrid.selectedRow);
			$('#resourceIds').val(ids);
			$('#uidIds').val(uids);
			return isValid;
		},
		success : function(result) {
			parent.$.messager.progress('close');
			result = $.parseJSON(result);
			if (result.success) {
				if (result.success) {
					parent.$.messager.alert('提示', result.msg, 'info');
				}
			}
		}
	});
});

</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'west'" style="width: 150px; overflow: hidden;">
			<table id="dataGridGroup" title="用户组列表"></table>
			
		</div>	
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'west'" title="" style="width: 300px; overflow: hidden;">
					
					<div class="easyui-accordion" data-options="fit:true,border:false">
						<div title="资源树" style="padding: 5px;" data-options="border:false,tools : [ {
									iconCls : 'icon-save',
									handler : function() {
										_form.submit();
									}
								},{
									iconCls : 'resultset_next',
									handler : function() {
										var node = $('#resourceTree').tree('getSelected');
										if (node) {
											$('#resourceTree').tree('expandAll', node.target);
										} else {
											$('#resourceTree').tree('expandAll');
										}
									}
								}, {
									iconCls : 'resultset_previous',
									handler : function() {
										var node = $('#resourceTree').tree('getSelected');
										if (node) {
											$('#resourceTree').tree('collapseAll', node.target);
										} else {
											$('#resourceTree').tree('collapseAll');
										}
									}
								} ]">
							<div class="well well-small">
								<ul id="resourceTree"></ul>
							</div>
						</div>
					</div>	
					<div class="well well-small">
							<ul id="resourceTree"></ul>
					</div>
				</div>
			
				<div data-options="region:'center',border:false,fit:true" title="" style="overflow: hidden;">
					<div class="easyui-panel" title="测点" style="width:100%;height:900px;padding:10px;" data-options="tools:'#ptools',fit:true">						
						<div class="easyui-panel" style="padding:5px;">
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1'" onclick="javascript:checkAllCd(true)">全选</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="toggle:true,group:'g1'" onclick="javascript:checkAllCd(false)">反选</a>
						
						</div>
						<div style="width:300px;">
						
						<div class="right">
							<table id="cfgtable" border="1">
							
							</table>
						</div>
						</div>
						<form id="form" method="post">
							<input id="nGroupNo" name="nGroupNo" type="hidden">
							<input id="resourceIds" name="resourceIds" type="hidden"/>
							<input id="uidIds" name="uidIds" type="hidden"/>	
						</form>
					</div>

<style type="text/css">
.left{
width:120px;
float:left;
}
.left table{
background:#E0ECFF;
}
.left td{
background:#eee;
}
.right{
float:left;
width:570px;
}
.right table{
background:#E0ECFF;
width:100%;
}
.right td{
background:#fafafa;
color:#444;
text-align:center;
padding:2px;
}
.right td{
background:#E0ECFF;
}
.right td.drop{
background:#fafafa;
width:100px;
}
.right td.over{
background:#FBEC88;
}
.item{
text-align:center;
border:1px solid #499B33;
background:#fafafa;
color:#444;
width:100px;
}
.assigned{

}
.cdchecked{
ackground: none repeat scroll 0 0 yellow;
border:1px solid #BC2A4D;
}
.trash{
background-color:red;
}
</style>
<script>
function sortItem(){
	var nlist = [];
	var node = resourceTree.tree('getSelected');	
	 $('.right .item').each(function(i){
		   var id = $(this).attr("id");
		   var o = getItem(id,node);
		   if(o)
			   nlist.push(o);
	 });	
	 node.attributes.spotlist = nlist;
	 resourceTree.tree('update',{target:node.target,
		 attributes: node.attributes});	 
}

function getItem(id,node){
	 var spotlist = node.attributes.spotlist;
	 for(var i = 0 ;i<spotlist.length;i++){
		var item = spotlist[i];				
		if(item.id==id){
			return item;
		}
	}
}

function dropEvent(){
	$('.right td.drop').droppable({
		onDragEnter:function(){
			$(this).addClass('over');
		},
		onDragLeave:function(){
			$(this).removeClass('over');			
		},
		onDrop:function(e,source){
				var _this= $(this);
				setTimeout(function(){
					_this.removeClass('over');
					if ($(source).hasClass('assigned')){
						_this.append(source);
						setTimeout(function(){sortItem();},500);					
					} else {
						var c = $(source).clone().addClass('assigned');
						_this.empty().append(c);
						c.draggable({
							revert:true
						});
				    } 
				},50);
		}
		}); 
	 $('.right .item').draggable({
		revert:true
		}); 
	 $('.right .item').dblclick( function () {
		 var $this = $(this);
		 $this.toggleClass("cdchecked"); 
		 var flag = false;
		 if($this.hasClass("cdchecked")){
			 flag = true;
		 }
		 var node = resourceTree.tree('getSelected');
		 var id = $this.attr("id");
		 var spotlist = node.attributes.spotlist;
		 for(var i = 0 ;i<spotlist.length;i++){
			var item = spotlist[i];				
			if(item.id==id){
				item.checked = flag;
				break;
			}
		}
		 
	  });
	 //("p").addClass("selected");
}

function checkAllCd(flag){
	var node = resourceTree.tree('getSelected');	
	var spotlist = node.attributes.spotlist;
	$('.right .item').each(function(){
		var $this = $(this);
		if(flag){
			if(!$this.hasClass("cdchecked")){
				$this.addClass("cdchecked");
			}		 	
		}else{
			if($this.hasClass("cdchecked")){
				$this.removeClass("cdchecked");	
			}
		}
		var id = $this.attr("id");
		for(var i = 0 ;i<spotlist.length;i++){
			var item = spotlist[i];				
			if(item.id==id){
				item.checked = flag;
				break;
			}
		}
	});
}


function appandrow(){
	$("#cfgtable").append('<tr><td class="drop"></td><td class="drop"></td><td class="drop"></td><td class="drop"></td></tr>');
}
</script>
				</div>
			</div>	
		</div>
	</div>	
</body>
</html>
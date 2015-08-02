<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {			
		parent.$.messager.progress('close');		
		$('#form').form({
			url : '${pageContext.request.contextPath}/appResourceController/editMap',
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
					parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
					parent.layout_west_tree.tree('reload');
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});
		setTimeout(initMap, 500);
	});
	function initMap(){
		var p = $("#position").val();
		if(p){
			var ps = p.split(",");
			if(ps.length==3){
				//$("#city").val(ps[2]);
				createMap(ps[0],ps[1]);
			}
		}
	}
	function createMap(x,y){
		
		//  百度地图API功能
		var map = new BMap.Map("allmap");  // 创建Map实例
		map.enableScrollWheelZoom(true);
		var marker;
		map.addEventListener("click",function(e){
			//marker.setPosition
			var p = marker.getPosition();
			p.lng = e.point.lng;
			p.lat = e.point.lat;
			marker.setPosition(p);
			geoc.getLocation(p, function(rs){
				var addComp = rs.addressComponents;
				var addr = addComp.city+"|"+addComp.district;
				$("#position").val(p.lng+","+p.lat+","+addr);
			});    
		});
		var cityName = $("#city").val();
		var point;
		var geoc = new BMap.Geocoder(); 
		if(x&&y){
			point = new BMap.Point(x,y);
		}else if(cityName){
			var myGeo = new BMap.Geocoder();
			// 将地址解析结果显示在地图上,并调整地图视野
			myGeo.getPoint(cityName, function(point){
				if (point) {
					
					map.centerAndZoom(point, 13);
					marker = new BMap.Marker(point);
					map.addOverlay(marker);
					marker.enableDragging();
					geoc.getLocation(point, function(rs){
						var addComp = rs.addressComponents;
						var addr = addComp.city+"|"+addComp.district;
						$("#position").val(point.lng+","+point.lat+","+addr);
					});     
				}else{
					isR = true;
					alert("您选择地址没有解析到结果!");
				}
			});
			return;
		}else{
			var p = $("#position").val();
			var flg = false;
			if(p){
				var ps = p.split(",");
				if(ps.length==3){
					point = new BMap.Point(ps[0],ps[1]);
					flg = true;
				}
			}
			if(!flg)
			point = new BMap.Point(116.400244,39.92556);
		}
		
		marker = new BMap.Marker(point);// 创建标注
		map.addOverlay(marker);  
		map.centerAndZoom(point, 13);
		marker.enableDragging();
		
		
		/*   
		
		console.info(marker);
		console.info(marker.getPosition()); */
	}

</script>
<style type="text/css">
		#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
		    <input name="id" type="hidden" class="span2" value="${resource.id}" readonly="readonly">
		    <input name="position" id="position" value="${resource.position}" type="hidden" class="span2">
			<table class="table table-hover table-condensed">			
				<tr>
					<th>地址</th>
					<td colspan="3">
						<input name="city" id="city" type="text"  class="easyui-validatebox span2" style="width:80%;">
						<input type="button" value="定位" onclick="createMap()">
					</td>
				</tr>
			</table>
		</form>
		<div id="allmap"></div>
	</div>
</div>

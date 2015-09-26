<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout">

	<div data-options="region:'center',border:false">
		<div id="index_api_tabs" class="easyui-tabs" data-options="fit:true">
			<div title="登录" data-options="href:'1/login.jsp'"
				style="padding: 1px"></div>
			<div title="获取权限接口" data-options="href:'1/refreshPerm.jsp'"
				style="padding: 1px"></div>
			<div title="获取测点值" data-options="href:'1/queryuid.jsp'"
				style="padding: 1px"></div>
			<div title="设置测点属性" data-options="href:'1/setuid.jsp'"
				style="padding: 1px"></div>					
			<div title="曲线图" data-options="href:'1/seqchart.jsp'"
				style="padding: 1px"></div>
			<div title="确认事件" data-options="href:'1/sureevent.jsp'"
				style="padding: 1px"></div>
			<div title="历史事件" data-options="href:'1/historyevent.jsp'"
				style="padding: 1px"></div>		
			<div title="用户组" data-options="href:'1/allgroup.jsp'"
				style="padding: 1px"></div>
			<div title="用户组权限" data-options="href:'1/groupperm.jsp'"
				style="padding: 1px"></div>
			<div title="推送接口" data-options="href:'1/tuisongapi.jsp'"
				style="padding: 1px"></div>
			<div title="注销" data-options="href:'1/logout.jsp'"
				style="padding: 1px"></div>
			<div title="修改密码" data-options="href:'1/updatepsd.jsp'"
				style="padding: 1px"></div>	
			<div title="设备属性" data-options="href:'1/equipuid.jsp'"
				style="padding: 1px"></div>
			<div title="运维属性" data-options="href:'1/repaireuid.jsp'"
				style="padding: 1px"></div>	
			<div title="安全时段（增）" data-options="href:'1/safeadd.jsp'"
				style="padding: 1px"></div>	
			<div title="安全时段（删）" data-options="href:'1/safedelete.jsp'"
				style="padding: 1px"></div>		
			<div title="安全时段（改）" data-options="href:'1/safeedit.jsp'"
				style="padding: 1px"></div>	
			<div title="安全时段（查）" data-options="href:'1/safelist.jsp'"
				style="padding: 1px"></div>	
			<div title="门禁数据" data-options="href:'1/getdoorlist.jsp'"
				style="padding: 1px"></div>							
				
		</div>
	</div>
</body>
</html>
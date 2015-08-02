<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jb.listener.Application" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

		parent.$.messager.progress('close');
</script>
</head>
<body>
 <div class="main">
        <div class="meun"></div>
        <div class="ul">
            <script type="text/javascript">
        function Click(action) {
            switch (action) {

                ///登录页面
                ///格式：text
                ///请求方式：post
                case "Login":
                    $.ajax({
                        type: "Post",
                        async: false,
                        cache: false,
                        url: "http://118.242.35.38:8282/api/User/Login/Checked",
                        data: { "UserName": "Admin", "UserPWD": "" },
                        dataType: 'text',
                        success: function (data) {
                            alert(data);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert("异常页面！");
                            //alert(XMLHttpRequest.status);
                            //alert(XMLHttpRequest.readyState);
                            //alert(textStatus);
                        }

                    });
                    break;


                    ///取出所有的组
                    ///格式：JSON
                    ///请求方式：get
                case "GetAllGroup":
                    $.ajax({
                        type: "get",
                        async: false,
                        cache: false,
                        url: "http://118.242.35.38:8282/api/UserGroup/GetAllGroup",
                        dataType: 'json',
                        success: function (data) {
                            alert(data);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert("异常页面！");
                            //alert(XMLHttpRequest.status);
                            //alert(XMLHttpRequest.readyState);
                            //alert(textStatus);
                        }
                    });
                    break;


                    ///根据用户名取出对应的用户组
                    ///格式：JSON
                    ///请求方式：get
                case "GetUserGroup":
                    $.ajax({
                        type: "get",
                        async: false,
                        cache: false,
                        url: "http://118.242.35.38:8282/api/UserGroup/GetUserGroup",
                        data:"UserName=Admin",
                        dataType: 'json',
                        success: function (data) {
                            alert(data);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert("异常页面！");
                            //alert(XMLHttpRequest.status);
                            //alert(XMLHttpRequest.readyState);
                            //alert(textStatus);
                        }

                    });
                    break;


                    ///取出所有的树
                    ///格式：JSON
                    ///请求方式：get
                case "GetTree":
                    $.ajax({
                        type: "get",
                        async: false,
                        cache: false,
                        url: "http://118.242.35.38:8282/api/Tree/GetTree",
                        dataType: 'json',
                        success: function (data) {
                            alert(data);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert("异常页面！");
                            //alert(XMLHttpRequest.status);
                            //alert(XMLHttpRequest.readyState);
                            //alert(textStatus);
                        }

                    });
                    break;

                    ///根据用户组取出对应的树
                    ///格式：JSON
                    ///请求方式：get
                case "GetGroupTree":
                    $.ajax({
                        type: "get",
                        async: false,
                        cache: false,
                        url: "http://118.242.35.38:8282/api/Tree/GetGroupTree",
                        data: "GroupNo=0",
                        dataType: 'json',
                        success: function (data) {
                            alert(data);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert("异常页面！");
                            //alert(XMLHttpRequest.status);
                            //alert(XMLHttpRequest.readyState);
                            //alert(textStatus);
                        }

                    });
                    break;
                    ///自由曲线数据
                    ///格式：JSON
                    ///请求方式：get



                    //ChartType:Line,Column,Pie
                    //DateType:ToDay,HistoricDay,Week,Month,Season,Year
                    //FromDateTime:开始时间（格式：yyyy-MM-dd HH:mm:ss）
                    //ToDateTime:结束时间（格式：yyyy-MM-dd HH:mm:ss）
                    //Uid：测点（多个用";"号分开）
                    //Name：测点名称（多个用";"号分开，测点名称跟UID数量一定要一至）
                    //MinWidth:画图的宽



                case "GetChart":
                    $.ajax({
                        type: "get",
                        async: false,
                        cache: false,
                        url: "http://118.242.35.38:8282/api/Chart/GetChart",
                        data: { "ChartType": "Line", "DateType": "", "FromDateTime": "2015-03-03", "ToDateTime": "", "Uid": "6.1.1.1.1.;6.1.1.1.1.1.;6.1.1.1.1.2.;", "Name": "机房.盛泽分公司.南麻机房.温湿度.1#温湿度;机房.盛泽分公司.南麻机房.温湿度.1#温湿度.温度;机房.盛泽分公司.南麻机房.温湿度.1#温湿度.湿度;", "MinWidth": "836" },
                        dataType: 'json',
                        success: function (data) {
                            alert(data);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert("异常页面！");
                        }

                    });
                    break;
                    ///事件确认
                    ///格式：JSON
                    ///请求方式：get



                case "Event":
                    $.ajax({
                        type: "post",
                        async: false,
                        cache: false,
                        url: "http://118.242.35.38:8282/api/Event/TimeEventConfirm/item",
                        data: { "UserName": "Admin", "Confirm": [{ "GUID": "91c889f8-1810-4842-8d7e-3ef2493bd815", "UID": "6.11.2.1.1.127.", "DateTime": "2015-03-04 19:13:37" }] },
                        dataType: 'json',
                        success: function (data) {
                            alert(data);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert("异常页面！");
                            //alert(XMLHttpRequest.status);
                            //alert(XMLHttpRequest.readyState);
                            //alert(textStatus);
                        }

                    });
                    break;
                case "Past":
                    $.ajax({
                        type: "post",
                        async: false,
                        cache: false,
                        url: "http://118.242.35.38:8282/api/Event/PastEvent/item",
                     //新数据
                     data: { "UserName": "Admin", "Past": { "UID": ["6.12.2.21.","6.1.1.1."], "DateTime": ["2015-05-01", "2015-05-16"], "Symbol": 0, "Level": "3" } },
                     //老数据
                       // data: { "UserName": "Admin", "Past": { "UID": ["6.11.2.1.3.81.", "6.11.2.1.3.82."], "DateTime": ["2015-02-20", "2015-02-27"], "Symbol": 0, "Level": "3" } },
                        dataType: 'json',
                        success: function (data) {
                            alert(data);
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert("异常页面！");
                            //alert(XMLHttpRequest.status);
                            //alert(XMLHttpRequest.readyState);
                            //alert(textStatus);
                        }
                    });
                    break;
                default:
            }
        }

        function Click2() {
            $(".meun").hide();
            $(".ul").css("margin-left", "0px");
        }
            </script>
            <input type="button" value="登录" onclick="Click('Login')" />
            <input type="button" value="取出所有用户组" onclick="Click('GetAllGroup')" />
            <input type="button" value="根据当前用户取出对应的用户组" onclick="Click('GetUserGroup')" />
            <input type="button" value="取出所有的测点" onclick="Click('GetTree')" />
            <input type="button" value="根据用户组取出测点" onclick="Click('GetGroupTree')" />
            <input type="button" value="自由曲线" onclick="Click('GetChart')" />
            <input type="button" value="事件确认" onclick="Click('Event')" />
            <input type="button" value="历史事件" onclick="Click('Past')" />
        </div>
        TCP测试数据
        </br>
        {"Action":"Q","Data":[{ "UID": "6.1.2.1.","Properties":"Value"},{ "UID": "6.1.2.1.","Properties":"Status"}]}
    </div>
</body>
</html>
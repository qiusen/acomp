<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><%=Property.SYSTEM_NAME %></title>
<%@ include file="/jsp/common/meta.jsp"%>
<script>
function changeCheckCode(){
	var checkCodeImg = document.getElementById("checkCodeImg");
	checkCodeImg.src="${base }/getcheckimage?"+Math.random();
}
$(function (){
	$("#loginname").focus();
});
</script>
<style type="text/css">
.header {
background: #000000 url(${base }/image/01.png) repeat-x 0 0;
height: 51px;
text-align: center;
overflow: hidden;
padding-top: 20px;
line-height: 150px;
}
body {
font: 12px/18px Arial, Simsun, Helvetica, sans-serif;
color: #616161;
text-align: left;
}
.main {
width: 300px;
margin: 70px auto 0;
height: 250px;
padding: 60px 0 0 90px;
}
input, label, select, option, textarea, button, fieldset, legend {
font: 12px/18px Arial, Simsun, Helvetica, sans-serif;
}
.btn_strong {
display: inline-block;
width: 90px;
height: 24px;
padding: 0px 20px;
font: bold 12px/24px Arial;
overflow: hidden;
zoom: 1;
cursor: pointer;
outline: none;
border: 0;
color: #ffffff;
border-radius: 3px;
background: -webkit-linear-gradient(top, #2372cf 0%,#3064af 100%);
margin-top:5px;
}
li{
margin-top:5px;
}
.input_mini{
width:62px;
}
</style>
</head>
<body style="padding:0px;">  
<div class="header"><h1 id=""><%=Property.SYSTEM_NAME %></h1></div>
<form action="${base }/login.${actionExt}" method="post">
<div class="main" id="">
	<ul class="login">
    	<li><span class="hd">用户名：</span><input id="loginname" type="text" value="" class="input_normal" name="username" id="username"></li>
        <li><span class="hd">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="password" value="" class="input_normal" id="password" name="password"></li>
        <li><span class="hd">验证码：</span><input type="text" value="1111" class="input_mini" id='checkCode' name='checkCode' value="1111" maxlength="4"><a href="#" onclick="changeCheckCode()"><img alt="点击刷新" src="${base }/getcheckimage" id="checkCodeImg" border="0" style="vertical-align: middle;margin-left:5px;"/></a></li>
        <li><input type="submit" class="btn_strong" value="&nbsp;登录&nbsp;"></li>
        <li>${errorStr}</li>
    </ul>
</div>


</form>
<div style="margin-top:160px;margin-left:550px;"><%@ include file="/jsp/common/bottom.jsp"%></div>

</body>

</html>
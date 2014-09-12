<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>模板标签 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='templeteTagAction.${actionExt}';    
}
</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
</head>
<body>
<form name="templeteTagForm" id="templeteTagForm" method="post" action="templeteTagAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="templeteTag.id" name="templeteTag.id" value="${requestScope.templeteTag.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">名称：</td>
        <td align="left" class="l-table-edit-td"><input name="templeteTag.name" type="text" id="templeteTag.name" ltype="text" value="${requestScope.templeteTag.name}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">编码：</td>
        <td align="left" class="l-table-edit-td"><input name="templeteTag.code" type="text" id="templeteTag.code" ltype="text" value="${requestScope.templeteTag.code}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">页面类型：</td>
        <td align="left" class="l-table-edit-td">
        <select name="templeteTag.type" id="templeteTag.type">
    	<option value="1" <c:if test="${requestScope.templeteTag.type==1}">selected="true"</c:if>>首页/频道页</option>
    	<option value="2" <c:if test="${requestScope.templeteTag.type==2}">selected="true"</c:if>>列表页</option>
    	<option value="3" <c:if test="${requestScope.templeteTag.type==3}">selected="true"</c:if>>文章页</option>
    	<option value="4" <c:if test="${requestScope.templeteTag.type==4}">selected="true"</c:if>>块</option>
    	<option value="5" <c:if test="${requestScope.templeteTag.type==5}">selected="true"</c:if>>友链</option>
    	</select>
        </td>
        <td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">数据类型：</td>
    	<td align="left" class="l-table-edit-td">
    	<select name="templeteTag.dataType" id="templeteTag.dataType">
    	<option value="1" <c:if test="${requestScope.templeteTag.dataType==1}">selected="true"</c:if>>文章</option>
    	</select>
    	</td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="center" colspan="3">
    	<input type="submit" value="保存" id="Button1" class="l-button l-button-submit" /> 
	<input type="button" value="取消" class="l-button l-button-reset" onclick="javascript:cancleClick();"/>
    	</td>
    </tr>
</table>
</form>
</body>
</html>
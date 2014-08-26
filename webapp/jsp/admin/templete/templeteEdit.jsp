<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>模板 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='templeteAction.${actionExt}';    
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
<form name="templeteForm" id="templeteForm" method="post" action="templeteAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="templete.id" name="templete.id" value="${requestScope.templete.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">名称：</td>
        <td align="left" class="l-table-edit-td"><input name="templete.name" type="text" id="templete.name" ltype="text" value="${requestScope.templete.name}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">类型：</td>
        <td align="left" class="l-table-edit-td">
        <select name="templete.type" id="templete.type">
    	<option value="1" <c:if test="${requestScope.templete.type==1}">selected="true"</c:if>>首页/频道页</option>
    	<option value="2" <c:if test="${requestScope.templete.type==2}">selected="true"</c:if>>列表页</option>
    	<option value="3" <c:if test="${requestScope.templete.type==3}">selected="true"</c:if>>文章页</option>
    	</select>
    	</td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">内容：</td>
        <td align="left" class="l-table-edit-td">
        <textarea name="templete.content" id="templete.content" cols="70" rows="20">${requestScope.templete.content}</textarea></td>
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
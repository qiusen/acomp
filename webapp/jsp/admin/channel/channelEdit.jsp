<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>频道 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='channelAction.${actionExt}';    
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
<form name="channelForm" id="channelForm" method="post" action="channelAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="channel.id" name="channel.id" value="${requestScope.channel.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">名称：</td>
        <td align="left" class="l-table-edit-td"><input name="channel.name" type="text" id="channel.name" ltype="text" value="${requestScope.channel.name}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">摘要：</td>
        <td align="left" class="l-table-edit-td"><input name="channel.brief" type="text" id="channel.brief" ltype="text" value="${requestScope.channel.brief}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">模板文件：</td>
        <td align="left" class="l-table-edit-td">
        <select name="channel.templeteId" id="channel.templeteId">
    	<c:forEach items="${templeteList }" var="templete">
        <option value="${templete.id }" <c:if test="${templete.id == requestScope.channel.templeteId}">selected="true"</c:if>>${templete.name }</option>
        </c:forEach>
    	</select>
    	</td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">状态：</td>
        <td align="left" class="l-table-edit-td">
        <select name="channel.status" id="channel.status" >
        <option value="1" <c:if test="${requestScope.channel.status==1}">selected="true"</c:if>>有效</option>
        <option value="0" <c:if test="${requestScope.channel.status==0}">selected="true"</c:if>>无效</option>
        </select>
        </td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">排序：</td>
        <td align="left" class="l-table-edit-td"><input name="channel.ordernum" type="text" id="channel.ordernum" ltype="text" value="${requestScope.channel.ordernum}"/></td>
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
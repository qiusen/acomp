<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>文章栏目 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='articleColumnAction.${actionExt}';    
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
<form name="articleColumnForm" id="articleColumnForm" method="post" action="articleColumnAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="articleColumn.id" name="articleColumn.id" value="${requestScope.articleColumn.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">编码：</td>
        <td align="left" class="l-table-edit-td"><input name="articleColumn.code" type="text" id="articleColumn.code" ltype="text" value="${requestScope.articleColumn.code}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">名称：</td>
        <td align="left" class="l-table-edit-td"><input name="articleColumn.name" type="text" id="articleColumn.name" ltype="text" value="${requestScope.articleColumn.name}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">频道ID：</td>
        <td align="left" class="l-table-edit-td">
        <select name="articleColumn.channelId" id="articleColumn.channelId">
    	<c:forEach items="${channelList }" var="channel">
        <option value="${channel.id }" <c:if test="${channel.id == requestScope.articleColumn.channelId}">selected="true"</c:if>>${channel.name }</option>
        </c:forEach>
    	</select>
    	</td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">状态：</td>
        <td align="left" class="l-table-edit-td">
        <select name="articleColumn.status" id="articleColumn.status" >
        <option value="1" <c:if test="${requestScope.articleColumn.status==1}">selected="true"</c:if>>有效</option>
        <option value="0" <c:if test="${requestScope.articleColumn.status==0}">selected="true"</c:if>>无效</option>
        </select>
        </td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">排序：</td>
        <td align="left" class="l-table-edit-td"><input name="articleColumn.ordernum" type="text" id="articleColumn.ordernum" ltype="text" value="${requestScope.articleColumn.ordernum}"/></td>
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
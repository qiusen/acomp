<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>块 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='blockAction.${actionExt}';    
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
<form name="blockForm" id="blockForm" method="post" action="blockAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="block.id" name="block.id" value="${requestScope.block.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">名称：</td>
        <td align="left" class="l-table-edit-td"><input name="block.name" type="text" id="block.name" ltype="text" value="${requestScope.block.name}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">文件名称：</td>
        <td align="left" class="l-table-edit-td"><input name="block.fileName" type="text" id="block.fileName" ltype="text" value="${requestScope.block.fileName}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">类型：1、自动块；2、手动块：</td>
        <td align="left" class="l-table-edit-td"><input name="block.type" type="text" id="block.type" ltype="text" value="${requestScope.block.type}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">描述：</td>
        <td align="left" class="l-table-edit-td"><input name="block.description" type="text" id="block.description" ltype="text" value="${requestScope.block.description}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">包含地址：</td>
        <td align="left" class="l-table-edit-td"><input name="block.includePath" type="text" id="block.includePath" ltype="text" value="${requestScope.block.includePath}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">数据来源类型：1、数据筛选；2、外部接口：</td>
        <td align="left" class="l-table-edit-td"><input name="block.sourceType" type="text" id="block.sourceType" ltype="text" value="${requestScope.block.sourceType}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">数据类型：1、文章：</td>
        <td align="left" class="l-table-edit-td"><input name="block.dataType" type="text" id="block.dataType" ltype="text" value="${requestScope.block.dataType}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">筛选条件：</td>
        <td align="left" class="l-table-edit-td"><input name="block.condition" type="text" id="block.condition" ltype="text" value="${requestScope.block.condition}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">模板ID：</td>
        <td align="left" class="l-table-edit-td"><input name="block.templeteId" type="text" id="block.templeteId" ltype="text" value="${requestScope.block.templeteId}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">数据条数：</td>
        <td align="left" class="l-table-edit-td"><input name="block.count" type="text" id="block.count" ltype="text" value="${requestScope.block.count}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">外部接口地址：</td>
        <td align="left" class="l-table-edit-td"><input name="block.interfaceUrl" type="text" id="block.interfaceUrl" ltype="text" value="${requestScope.block.interfaceUrl}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">块内容：</td>
        <td align="left" class="l-table-edit-td"><input name="block.content" type="text" id="block.content" ltype="text" value="${requestScope.block.content}"/></td>
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
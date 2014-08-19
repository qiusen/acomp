<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>目录 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='catalogAction.${actionExt}';    
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
<form name="catalogForm" id="catalogForm" method="post" action="catalogAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="catalog.id" name="catalog.id" value="${requestScope.catalog.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">目录名称：</td>
        <td align="left" class="l-table-edit-td"><input name="catalog.catalogname" type="text" id="catalog.catalogname" ltype="text" value="${requestScope.catalog.catalogname}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">状态：</td>
        <td align="left" class="l-table-edit-td"><input name="catalog.status" type="text" id="catalog.status" ltype="text" value="${requestScope.catalog.status}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">排序：</td>
        <td align="left" class="l-table-edit-td"><input name="catalog.ordernum" type="text" id="catalog.ordernum" ltype="text" value="${requestScope.catalog.ordernum}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">所属菜单ID：</td>
        <td align="left" class="l-table-edit-td"><input name="catalog.menuId" type="text" id="catalog.menuId" ltype="text" value="${requestScope.catalog.menuId}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">创建时间：</td>
        <td align="left" class="l-table-edit-td"><input name="catalog.createtime" type="text" id="catalog.createtime" ltype="text" value="${requestScope.catalog.createtime}"/></td>
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
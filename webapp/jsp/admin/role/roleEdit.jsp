<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='roleAction.${actionExt}';    
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
<form name="roleForm" id="roleForm" method="post" action="roleAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="role.id" name="role.id" value="${requestScope.role.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">角色名称：</td>
        <td align="left" class="l-table-edit-td"><input name="role.rolename" type="text" id="role.rolename" ltype="text" value="${requestScope.role.rolename}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">角色状态：</td>
        <td align="left" class="l-table-edit-td"><input name="role.status" type="text" id="role.status" ltype="text" value="${requestScope.role.status}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">创建时间：</td>
        <td align="left" class="l-table-edit-td"><input name="role.createtime" type="text" id="role.createtime" ltype="text" value="${requestScope.role.createtime}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">权限：</td>
        <td align="left" class="l-table-edit-td"><input name="role.rights" type="text" id="role.rights" ltype="text" value="${requestScope.role.rights}"/></td>
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
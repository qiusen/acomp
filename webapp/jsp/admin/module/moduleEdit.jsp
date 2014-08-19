<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>模块 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='moduleAction.${actionExt}';    
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
<form name="moduleForm" id="moduleForm" method="post" action="moduleAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="module.id" name="module.id" value="${requestScope.module.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">模块名称：</td>
        <td align="left" class="l-table-edit-td"><input name="module.modulename" type="text" id="module.modulename" ltype="text" value="${requestScope.module.modulename}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">模块URL：</td>
        <td align="left" class="l-table-edit-td"><input name="module.moduleurl" type="text" id="module.moduleurl" ltype="text" value="${requestScope.module.moduleurl}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">模块ACTION：</td>
        <td align="left" class="l-table-edit-td"><input name="module.moduleact" type="text" id="module.moduleact" ltype="text" value="${requestScope.module.moduleact}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">所属目录ID：</td>
        <td align="left" class="l-table-edit-td"><input name="module.catalogId" type="text" id="module.catalogId" ltype="text" value="${requestScope.module.catalogId}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">模块状态：</td>
        <td align="left" class="l-table-edit-td"><input name="module.status" type="text" id="module.status" ltype="text" value="${requestScope.module.status}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">创建时间：</td>
        <td align="left" class="l-table-edit-td"><input name="module.createtime" type="text" id="module.createtime" ltype="text" value="${requestScope.module.createtime}"/></td>
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
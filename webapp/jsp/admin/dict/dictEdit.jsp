<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>字典 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
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
<form name="dictForm" id="dictForm" method="post" action="dictAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="dict.id" name="dict.id" value="${requestScope.dict.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">编码：</td>
        <td align="left" class="l-table-edit-td"><input name="dict.code" type="text" id="dict.code" ltype="text" value="${requestScope.dict.code}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">名称：</td>
        <td align="left" class="l-table-edit-td"><input name="dict.name" type="text" id="dict.name" ltype="text" value="${requestScope.dict.name}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">数据组：</td>
        <td align="left" class="l-table-edit-td"><input name="dict.group" type="text" id="dict.group" ltype="text" value="${requestScope.dict.group}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">值：</td>
        <td align="left" class="l-table-edit-td"><input name="dict.value" type="text" id="dict.value" ltype="text" value="${requestScope.dict.value}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
    	<td align="center" colspan="3">
    	<input type="submit" value="保存" id="Button1" class="l-button l-button-submit" /> 
	<input type="button" value="取消" class="l-button l-button-reset" onclick="javascript: history.back(-1);"/>
    	</td>
    </tr>
</table>
</form>
</body>
</html>
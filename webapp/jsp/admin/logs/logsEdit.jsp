<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>日志 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='logsAction.${actionExt}';    
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
<form name="logsForm" id="logsForm" method="post" action="logsAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="logs.id" name="logs.id" value="${requestScope.logs.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">用户名：</td>
        <td align="left" class="l-table-edit-td"><input name="logs.username" type="text" id="logs.username" ltype="text" value="${requestScope.logs.username}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">昵称：</td>
        <td align="left" class="l-table-edit-td"><input name="logs.nickname" type="text" id="logs.nickname" ltype="text" value="${requestScope.logs.nickname}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">IP：</td>
        <td align="left" class="l-table-edit-td"><input name="logs.ip" type="text" id="logs.ip" ltype="text" value="${requestScope.logs.ip}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">操作标识：LOGIN、LOGOFF，……：</td>
        <td align="left" class="l-table-edit-td"><input name="logs.act" type="text" id="logs.act" ltype="text" value="${requestScope.logs.act}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">操作时间：</td>
        <td align="left" class="l-table-edit-td"><input name="logs.opttime" type="text" id="logs.opttime" ltype="text" value="${requestScope.logs.opttime}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">操作内容：</td>
        <td align="left" class="l-table-edit-td"><input name="logs.content" type="text" id="logs.content" ltype="text" value="${requestScope.logs.content}"/></td>
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
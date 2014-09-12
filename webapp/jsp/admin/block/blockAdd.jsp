<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>块 添加</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='blockAction.${actionExt}'; 
}

function showAutomaticDiv(){
	document.getElementById("automaticdiv").style.display="inline";
	document.getElementById("manualdiv").style.display="none";
}

function showManualDiv(){
	document.getElementById("automaticdiv").style.display="none";
	document.getElementById("manualdiv").style.display="inline";
}

function showConditionDiv(){
	document.getElementById("conditiondiv").style.display="inline";
	document.getElementById("interfacediv").style.display="none";
}

function showInterfaceDiv(){
	document.getElementById("conditiondiv").style.display="none";
	document.getElementById("interfacediv").style.display="inline";
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
<form name="blockForm" id="blockForm" method="post" action="blockAction!addSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <tr>
    	<td align="right" class="l-table-edit-td">名称：</td>
    	<td align="left" class="l-table-edit-td"><input name="block.name" type="text" id="block.name" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">文件名称：</td>
    	<td align="left" class="l-table-edit-td"><input name="block.fileName" type="text" id="block.fileName" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">描述：</td>
    	<td align="left" class="l-table-edit-td"><input name="block.description" type="text" id="block.description" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">类型：</td>
    	<td align="left" class="l-table-edit-td">
    	<input type="radio" name="block.type" id="block.type" value="1" checked="checked" onclick="showAutomaticDiv();"/>自动块
    	<input type="radio" name="block.type" id="block.type" value="2" onclick="showManualDiv();"/>手动块
    	</td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td"></td>
    	<td align="center" colspan="2">
    	<div style="width:500px;height:150px;border:1px solid #ccc;margin:10px;padding:20px;">
	    	<div id="automaticdiv" >
	    	<div>
	    	数据类型：<select name="block.dataType" id="block.dataType">
	    	<option value="1">文章</option>
	    	</select>
	    	</div>
	    	<div>
	    		数据来源类型：<input type="radio" name="block.sourceType" id="block.sourceType" value="1" checked="checked" onclick="showConditionDiv();"/>数据筛选
	    		<input type="radio" name="block.sourceType" id="block.sourceType" value="2" onclick="showInterfaceDiv();"/>外部接口
	    	</div>
	    	
	    	<div id="conditiondiv">
	    	筛选条件：<input name="block.condition" type="text" id="block.condition" ltype="text" />
	    	</div>
	    	<div id="interfacediv" style="display:none;">
	    	外部接口地址：<input name="block.interfaceUrl" type="text" id="block.interfaceUrl" ltype="text" style="width:300px;"/>
	    	</div>
	    	<div>
	    	数据条数：<input name="block.count" type="text" id="block.count" ltype="text" value="5"/>
	    	</div>	
	    	<div>
	    	模板：<select name="block.templeteId" id="block.templeteId" >
	    	<c:forEach items="${templeteList}" var="templete">
	    	<option value="${templete.id }">${templete.name }</option>
	    	</c:forEach>
	    	</select>
	    	</div>	
	    	</div>
	    	<div id="manualdiv" style="display:none;">
	    	手动块内容：<textarea name="block.content" id="block.content" rows="5" cols="80" style="width:380px;"></textarea>
	    	</div>
    	</div>
    	</td>
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
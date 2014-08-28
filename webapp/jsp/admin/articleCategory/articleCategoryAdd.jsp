<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>文章类别 添加</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script type='text/javascript'
	src='<%=Property.BASE %>/dwr/interface/articleColumnDwr.js'></script>
<script type='text/javascript' src='<%=Property.BASE %>/dwr/engine.js'></script>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='articleCategoryAction.${actionExt}';    
}

function changeColumnList(){
	var c = document.getElementById("channel").value;
	selectColumn(c);
}

function selectColumn(ci){
	articleColumnDwr.selectArticleColumnByChannelId(ci, callbackArticleColumn);
}

var callbackArticleColumn = function (articleColumnList){
	var ac= document.getElementById("articleCategory.columnId");
	clearSelect(ac);	//清空
	if(articleColumnList!= null && articleColumnList.length>0){
		for(var i=0;i<articleColumnList.length;i++){
			ac.options[i] = new Option(articleColumnList[i].name, articleColumnList[i].id);
		}
	}
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
<body onload="changeColumnList();">
<form name="articleCategoryForm" id="articleCategoryForm" method="post" action="articleCategoryAction!addSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <tr>
    	<td align="right" class="l-table-edit-td">编码：</td>
    	<td align="left" class="l-table-edit-td"><input name="articleCategory.code" type="text" id="articleCategory.code" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">名称：</td>
    	<td align="left" class="l-table-edit-td"><input name="articleCategory.name" type="text" id="articleCategory.name" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">频道：</td>
    	<td align="left" class="l-table-edit-td">
    	<select name="channel" id="channel"  onchange="selectColumn(this.value);">
    	<c:forEach items="${channelList}" var="channel">
    	<option value="${channel.id }">${channel.name }</option>
    	</c:forEach>
    	</select>
    	</td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">栏目：</td>
    	<td align="left" class="l-table-edit-td">
    	<select name="articleCategory.columnId" id="articleCategory.columnId">
    	</select>
    	</td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">状态：</td>
    	<td align="left" class="l-table-edit-td">
    	<select name="articleCategory.status" id="articleCategory.status" >
        <option value="1">有效</option>
        <option value="0">无效</option>
        </select>
        </td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">排序：</td>
    	<td align="left" class="l-table-edit-td"><input name="articleCategory.ordernum" type="text" id="articleCategory.ordernum" ltype="text" /></td>
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
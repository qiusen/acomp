<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>文章 添加</title>
<%@ include file="/jsp/common/meta.jsp"%>
<link rel="stylesheet" href="${base }/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${base }/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="${base }/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${base }/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${base }/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="${base}/js/jquery.upload.js" type="text/javascript" ></script>
<script type='text/javascript'
	src='<%=Property.BASE %>/dwr/interface/articleColumnDwr.js'></script>
<script type='text/javascript'
	src='<%=Property.BASE %>/dwr/interface/articleCategoryDwr.js'></script>
<script type='text/javascript' src='<%=Property.BASE %>/dwr/engine.js'></script>

<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='articleAction.${actionExt}';    
}

var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="article.content"]', {
		cssPath : '${base }/kindeditor/plugins/code/prettify.css',
		uploadJson : '${base }/uploadJson',
		fileManagerJson : '${base }/fileManagerJson',
		//fileManagerJson : '../jsp/file_manager_json.jsp',
		allowFileManager : true,
		afterCreate : function() {
			var self = this;
			K.ctrl(document, 13, function() {
				self.sync();
				document.forms['articleForm'].submit();
			});
			K.ctrl(self.edit.doc, 13, function() {
				self.sync();
				document.forms['articleForm'].submit();
			});
		}
	});
	prettyPrint();
});

function addSaveAndPublish(){
	var articleForm = document.getElementById("articleForm");
	articleForm.action="articleAction!addSaveAndPublish.${actionExt}";
	var content = document.getElementById("article.content");
	content.value=editor.html();
	
	articleForm.submit();
}

//图片上传
function doUpload(element) {
	// 上传方法
	$.upload({
			// 上传地址
			url: '${base}/fileUploadServlet', 
			// 文件域名字
			fileName: 'imageFile',
			// 其他表单数据
			params: {
				name: 'pxblog'
			},
			// 上传完成后, 返回json, text
			dataType: 'json',
			// 上传之前回调,return true表示可继续上传
			onSend: function() {
				return true;
			},
			// 上传之后回调
			onComplate: function(data) {
				if(data.message != ''){
					$.ligerDialog.warn(data.message);
					return;
				}
				if(element)
					element.src = data.filePath;
				$('.title_img_view').attr('src',data.filePath)
				//$("#titleImg").val(data.filePath);
				document.getElementById("article.articleImg").value=data.filePath;
				//alert(document.getElementById("article.articleImg").value);
			}
	});
}

function changeColumnList(){
	var c = document.getElementById("channel").value;
	selectColumn(c);
}

function selectColumn(ci){
	articleColumnDwr.selectArticleColumnByChannelId(ci, callbackArticleColumn);
}

var callbackArticleColumn = function (articleColumnList){
	var ac= document.getElementById("article.columnCode");
	clearSelect(ac);	//清空
	if(articleColumnList!= null && articleColumnList.length>0){
		for(var i=0;i<articleColumnList.length;i++){
			ac.options[i] = new Option(articleColumnList[i].name, articleColumnList[i].code);
			if(i==0){
				selectCategory(articleColumnList[i].code);
			}
		}
	}
}

function selectCategory(cc){
	articleCategoryDwr.selectArticleCategoryByColumnCode(cc, callbackArticleCategory);
}

var callbackArticleCategory = function (articleCategoryList){
	var acc = document.getElementById("article.categoryCode");
	clearSelect(acc);	//清空
	if(articleCategoryList!= null && articleCategoryList.length>0){
		for(var i=0;i<articleCategoryList.length;i++){
			acc.options[i] = new Option(articleCategoryList[i].name, articleCategoryList[i].code);
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
<form name="articleForm" id="articleForm" method="post" action="articleAction!addSave.${actionExt}" onsubmit="return checkForm();" enctype ="multipart/form-data">
<input name="article.articleImg" id="article.articleImg" type="hidden"/>
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <tr>
    	<td align="right" class="l-table-edit-td">标题：</td>
    	<td align="left" class="l-table-edit-td"><input name="article.title" type="text" id="article.title" ltype="text" style="width:300px;"/></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">短标题：</td>
    	<td align="left" class="l-table-edit-td"><input name="article.shortTitle" type="text" id="article.shortTitle" ltype="text" style="width:300px;" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">作者：</td>
    	<td align="left" class="l-table-edit-td"><input name="article.auth" type="text" id="article.auth" ltype="text" value="${sessionScope.manager.nickname }"/></td>
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
    	<select name="article.columnCode" id="article.columnCode" onchange="selectCategory(this.value);">
    	</select>
    	</td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">类别：</td>
    	<td align="left" class="l-table-edit-td">
    	<select name="article.categoryCode" id="article.categoryCode" >
    	</select>
    	</td>
    	<td align="left"></td>
    </tr>
    
    <tr>
    	<td align="right" class="l-table-edit-td">导读图：</td>
    	<td align="left" class="l-table-edit-td">
    	<img class="title_img_view" src="${base }/image/noimg.jpg" onclick="doUpload()" style="max-height:150px;height: expression(this.height > 150 ? 150: true);margin:9px;border:0px;cursor：hand;"/>
	    </td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">摘要：</td>
    	<td align="left" class="l-table-edit-td">
    	<textarea rows="5" cols="80" style="width:680px;" name="article.brief" id="article.brief"></textarea>
    	</td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">内容：</td>
    	<td align="left" class="l-table-edit-td">
    	<textarea name="article.content" id="article.content" cols="80" rows="8" 
    		style="width:680px;height:350px;visibility:hidden;"></textarea>
    	</td>
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
<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>友链网站 添加</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script charset="utf-8" src="${base}/js/jquery.upload.js" type="text/javascript" ></script>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='linkSiteAction.${actionExt}';    
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
				document.getElementById("linkSite.siteLogo").value=data.filePath;
			}
	});
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
<form name="linkSiteForm" id="linkSiteForm" method="post" action="linkSiteAction!addSave.${actionExt}" onsubmit="return checkForm();" enctype ="multipart/form-data">
<input type="hidden" id="linkSite.siteLogo" name="linkSite.siteLogo" />
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <tr>
    	<td align="right" class="l-table-edit-td">网站名称：</td>
    	<td align="left" class="l-table-edit-td"><input name="linkSite.siteName" type="text" id="linkSite.siteName" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">网站URL：</td>
    	<td align="left" class="l-table-edit-td"><input name="linkSite.siteUrl" type="text" id="linkSite.siteUrl" ltype="text" style="width:300px;"/></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">网站LOGO：</td>
    	<td align="left" class="l-table-edit-td">
    	<img class="title_img_view" src="${base }/image/noimg.jpg" onclick="doUpload()" style="max-height:150px;height: expression(this.height > 150 ? 150: true);margin:9px;border:0px;cursor：hand;"/>
    	</td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">网站简介：</td>
    	<td align="left" class="l-table-edit-td"><input name="linkSite.description" type="text" id="linkSite.description" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">联系人：</td>
    	<td align="left" class="l-table-edit-td"><input name="linkSite.contact" type="text" id="linkSite.contact" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">QQ：</td>
    	<td align="left" class="l-table-edit-td"><input name="linkSite.qq" type="text" id="linkSite.qq" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">座机：</td>
    	<td align="left" class="l-table-edit-td"><input name="linkSite.tel" type="text" id="linkSite.tel" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">手机：</td>
    	<td align="left" class="l-table-edit-td"><input name="linkSite.mobile" type="text" id="linkSite.mobile" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">EMAIL：</td>
    	<td align="left" class="l-table-edit-td"><input name="linkSite.email" type="text" id="linkSite.email" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    <tr>
    	<td align="right" class="l-table-edit-td">状态：</td>
    	<td align="left" class="l-table-edit-td">
    	<input name="linkSite.status" type="radio" id="linkSite.status" value="1" checked="checked"/> 有效
    	<input name="linkSite.status" type="radio" id="linkSite.status" value="0" /> 无效</td>
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
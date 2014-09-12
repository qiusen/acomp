<%@ page contentType="text/html;charset=UTF-8"%><%@ include
	file="/jsp/common/taglibs.jsp"%><!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>友链页面 添加</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script src="${base}/js/jquery.dragsort.js" type="text/javascript"></script>
<script language="javascript">
function checkForm(){
	return true;
}
function cancleClick(){
	self.location='linkPageAction.${actionExt}';    
}

$(document).ready(function(){
	$("#gridtbody").dragsort({ itemSelector: "tr", dragSelector: "tr", dragBetween: true, placeHolderTemplate: "<tr></tr>" });
	
	
	$("#listbox1,#listbox4").ligerListBox({
        isShowCheckBox: true,
        isMultiSelect: true,
        height: 300,
        width:200
    });
	$("#listbox2").ligerListBox({
        isShowCheckBox: true,
        isMultiSelect: true,
        height: 320,
        width:200
    });

    liger.get("listbox1").setData([{"text":"新浪教育","id":1},{"text":"搜狐教育","id":2},{"text":"新浪网","id":3},{"text":"腾讯网","id":4},{"text":"搜狐网","id":5},{"text":"豆丁网","id":10}]);
    
    //设置选中列表中的listbox元素可拖拽位置
    $("#listbox2").find('table').attr("id","listbox");
    
    $("#keyword").bind("input", function(){
    	var key = $(this).val();
    	var box1 = liger.get("listbox1");
    	var box4 = liger.get("listbox4");
    	var listdata1 = box1.data;
    	var listdata4 = box4.data;
    	if(listdata4 != null){
    		box1.addItems(listdata4);
    		box4.removeItems(listdata4);
		}
    	if(key != ''){
    		for(var i = 0 ; i < listdata1.length; i++){
        		var text = listdata1[i].text;
        		//alert(text);
        		var index = text.indexOf(key);
        		
        		if(index < 0){
        			box4.addItems(listdata1[i]);
        			box1.removeItems(listdata1[i]);
        			i--;
        		}else{
        			
        		}
        	}
    	}
    	box1.refresh();
    });
    
    //预览友链页面时，验证模板不能为空
    $("#preview").click(function(){
    	var templatesId = $("#templateId").val();
    	if(templatesId == ""){
    		$.ligerDialog.warn('请选择模板');
    		return false;
    	}
    	var links = $("#links").val();
    	$("#preLinks").val(links);
    	$("#preTemplatesId").val(templatesId);
    	$("#previewForm").submit();
    });
    initValidate();
});

//初始化验证
function initValidate(){
	var rules = {
			"linkPage.pageName": {
				required: true,
				min:false,
				max:false
			},
			"linkPage.templateId":{
				required: true,
				min:false,
				max:false
			}
	};
	var messages = {};
	validateForm("#linkPageForm", rules, messages);
}

function moveToLeft()
{
	var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
	var selecteds = box2.getSelectedItems();
	if (!selecteds || !selecteds.length) return;
	box2.removeItems(selecteds);
	box1.addItems(selecteds);
	//box3.addItems(box2.getSelectedItems());
	refreshListBox();
}
function moveToRight()
{
	var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
	var selecteds = box1.getSelectedItems();
	if (!selecteds || !selecteds.length) return;
	box1.removeItems(selecteds);
	box2.addItems(selecteds);
	refreshListBox();
}
function moveAllToLeft()
{ 
	var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
	var selecteds = box2.data;
	if (!selecteds || !selecteds.length) return;
	box1.addItems(selecteds);
	box2.removeItems(selecteds);
	refreshListBox();
}
function moveAllToRight()
{ 
	var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
	var selecteds = box1.data;
	if (!selecteds || !selecteds.length) return;
	box2.addItems(selecteds);
	box1.removeItems(selecteds);
	refreshListBox();
}

//刷新listbox
function refreshListBox(){
	var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
	setLinks();
	box1.refresh();
	box2.refresh();
	initDrag();
}

//按顺序取出选中友链的ID 逗号分隔
function setLinks(){
	$("#links").val(",");
	$("#listbox2").find("tr").each(
		function(){
			$("#links").val($("#links").val()+$(this).attr("value")+",");
		}
	);
}

//初始化可拖拽组件
function initDrag(){
	$("#listbox").dragsort({ itemSelector: "tr", dragSelector: "tr", dragBetween: true, dragEnd: setLinks, placeHolderTemplate: "<tr></tr>" });
}
</script>
<style type="text/css">
body {
	font-size: 12px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit, .l-button-reset {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
</head>
<body>
	<form name="linkPageForm" id="linkPageForm" method="post"
		action="linkPageAction!addSave.${actionExt}"
		onsubmit="return checkForm();">
		<table cellpadding="0" cellspacing="0" class="l-table-edit"
			style="margin-top: 50px; margin-left: 50px;">
			<tr>
				<td align="center" colspan="3">
					<div style="margin: 4px; float: left;">
						<div id="serchDiv">
							<input id="keyword" type="text"
								style="width: 196px; height: 20px;" placeholder="请输入关键字搜索" />
						</div>
						<div id="listbox1" class="gbin1-list"></div>
					</div>
					<div style="margin: 4px; float: left;" class="middle">
						<input type="button" onclick="moveToLeft()" value="删除" /><br />
						<input type="button" onclick="moveToRight()" value="添加" /><br />
						<input type="button" onclick="moveAllToLeft()" value="删除全部" /><br />
						<input type="button" onclick="moveAllToRight()" value="添加全部" />
					</div>
					<div style="margin: 4px; float: left;">
						<div id="listbox2"></div>
					</div>
					<div id="listbox3" style="display: none;"></div>
					<div id="listbox4" style="display: none;"></div>
				</td>
			</tr>

			<tr>
				<td align="center" colspan="3"><input type="submit" value="保存"
					id="Button1" class="l-button l-button-submit" /> <input
					type="button" value="取消" class="l-button l-button-reset"
					onclick="javascript:cancleClick();" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
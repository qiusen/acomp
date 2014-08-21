<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>区 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script type='text/javascript'
	src='<%=Property.BASE %>/dwr/interface/cityDwr.js'></script>
<script type='text/javascript' src='<%=Property.BASE %>/dwr/engine.js'></script>
<script language="javascript">
function checkForm(){
	return true;
}

function selectCity(pc){
	cityDwr.selectCityByProvinceCode(pc, callbackCity);
}

var callbackCity = function (cityList){
	var cc= document.getElementById("area.cityCode");
	clearSelect(cc);	//清空
	if(cityList!= null && cityList.length>0){
		for(var i=0;i<cityList.length;i++){
			cc.options[i] = new Option(cityList[i].name, cityList[i].code);
			if(cityList[i].code=='${requestScope.area.cityCode}'){
				cc.options[i].selected = "selected";
			}
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
<body onload="selectCity('${requestScope.city.provinceCode }');">
<form name="areaForm" id="areaForm" method="post" action="areaAction!editSave.${actionExt}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
    <input type="hidden" id="area.id" name="area.id" value="${requestScope.area.id}"/>
    <tr>
        <td align="right" class="l-table-edit-td">编号：</td>
        <td align="left" class="l-table-edit-td"><input name="area.code" type="text" id="area.code" ltype="text" value="${requestScope.area.code}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">名称：</td>
        <td align="left" class="l-table-edit-td"><input name="area.name" type="text" id="area.name" ltype="text" value="${requestScope.area.name}"/></td>
        <td align="left"></td>
    </tr>
    <tr>
        <td align="right" class="l-table-edit-td">所属市：</td>
        <td align="left" class="l-table-edit-td">
        <select name="provinceCode" id="provinceCode" onchange="selectCity(this.value);">
    	<c:forEach items="${provinceList}" var="province">
    	<option value="${province.code }" <c:if test="${province.code==requestScope.city.provinceCode }">selected="selected"</c:if>>${province.name }</option>
    	</c:forEach>
    	</select>
    	<select name="area.cityCode" id="area.cityCode" >
    	</select>
    	</td>
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
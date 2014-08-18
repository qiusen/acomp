<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/jsp/common/taglibs.jsp"%><div id="topmenu" class="l-topmenu">
    <div class="l-topmenu-logo"><%=Property.SYSTEM_NAME %></div>
    <div class="l-topmenu-welcome">
        <font class="l-link2">欢迎您，${sessionScope.manager.roleName }：${sessionScope.manager.realName }</font>
        <span class="space">|</span>
        <a href="${base }/login!logOff.${actionExt}" class="l-link2">退出</a> 
    </div> 
</div>
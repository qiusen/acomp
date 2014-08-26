/**
 * 
 */
package com.dihaitech.acomp.controller.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsSpringTestCase;

import com.dihaitech.acomp.common.Property;
import com.dihaitech.acomp.model.Manager;
import com.opensymphony.xwork2.ActionProxy;

/**
 * @author qiusen
 * 
 */
public abstract class CommonTestAction extends StrutsSpringTestCase {

	protected ActionProxy proxy;

	protected void setUp() throws Exception {
		super.setUp();

		if (null == proxy)
			proxy = getActionProxy(getNameSpace() + "." + Property.ACTION_EXT);

		
		Manager manager = new Manager();
		manager.setNickname("单元测试");
		manager.setId(-1);
		manager.setEmail("单元测试mail");
		manager.setUsername("单元测试username");

		request.getSession().setAttribute("manager", manager);
		proxy.getInvocation().getInvocationContext().getSession()
				.put("manager", manager);
		ServletActionContext.setContext(proxy.getInvocation()
				.getInvocationContext());

		ServletActionContext.setRequest(request);

		ServletActionContext.setResponse(response);
	}

	public abstract String getNameSpace();
}

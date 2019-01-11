package org.rapp.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.rapp.pojo.account.User;
import org.rapp.session.SystemSession;

/**
 * session监听器
 * @author 张芳
 *
 */
public class CustomHttpSessionAttributeListener implements HttpSessionAttributeListener {
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("user放入session");
		if ("backUser".equals(event.getName())) {  
            SystemSession.setUserSession((User)event.getValue());  
        }
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("user移除session");
		if ("backUser".equals(event.getName())) {  
            SystemSession.setUserSession(null);  
        }
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("user更新session");
		if ("backUser".equals(event.getName())) {  
            SystemSession.setUserSession((User)event.getValue());  
        }
	}

}

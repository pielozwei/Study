package com.thinkgem.jeesite.modules.quality.user;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
@Component
@Transactional(readOnly = true)
public class QczbryTaskListenerImpl implements TaskListener{
	@Override
	public void notify(DelegateTask delegateTask) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//拿到对应的组织机构
		String sszzjg = (String) request.getSession().getAttribute("sszzjg");
		//删除session作用域中的组织机构值
		request.getSession().removeAttribute("sszzjg");
		//从作用域中拿出查询出来的角色
		List<Role> roles = (List<Role>) request.getSession().getAttribute("roles");
		//删除session作用域中的角色列表
		request.getSession().removeAttribute("roles");
		if(null!=roles && roles.size()>0){
			for(Role role : roles){
				//得到该角色的用户
				List<User> users = role.getUserList();
				if(null!=users && users.size()>0){
					for(User user : users){
						if(user.getOffice().getId().equals(sszzjg)){
							delegateTask.addCandidateUser(user.getId());
						}
					}
				}
			}
		}
	}

}

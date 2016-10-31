/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ip.entity.Employee;
import com.thinkgem.jeesite.modules.ip.entity.Organization;
import com.thinkgem.jeesite.modules.ip.form.EmployeeInfForm;
import com.thinkgem.jeesite.modules.ip.service.EmployeeContactService;
import com.thinkgem.jeesite.modules.ip.service.EmployeeService;

/**
 * 个人信息Controller
 * @author cml
 * @version 2016-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employee")
public class EmployeeController extends BaseController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@ModelAttribute
	public Employee get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeeService.get(id);
		}else{
			return new Employee();
		}
	}
	
	@RequiresPermissions("ip:employee:view")
	@RequestMapping(value = {"list", ""})
	public String list(Employee employee, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employee.setCreateBy(user);
		}
		Page<Employee> page = employeeService.find(new Page<Employee>(request, response), employee); 
        model.addAttribute("page", page);
        return "modules/" + "ip/employeeList";
	}
        
//	@RequiresPermissions("ip:employee:view")
//	@RequestMapping(value = "form")
//	public String form(EmployeeInfForm employeeinfform, Model model) {//修改employee为employeeinfform
//
//		//1、根据form提交的ID获取emloyee以及emloyeecontact表内的信息
//		if(StringUtils.isNotEmpty(employeeinfform.getId())){
//			employeeinfform = employeeService.getEmployeeInfoById(employeeinfform.getId());
//		}
//		if(employeeinfform.getXssx()==null){
//			employeeinfform.xssx = employeeService.getNewxssx();
//		}
//		model.addAttribute("employeeinfform", employeeinfform);
//		return "modules/" + "ip/employeeForm";
//	}
//	@RequiresPermissions("ip:employee:edit")
//	@RequestMapping(value = "save")
//	public String savenew(EmployeeInfForm employeeinfform, Model model, RedirectAttributes redirectAttributes) {
//		
//		if (!beanValidator(model, employeeinfform)){
//			return form(employeeinfform, model);//修改employee为employeeinfform
//		}
//		
//
//			employeeService.saveEmployee(employeeinfform);//保存个人基本信息		
//			employeeService.saveEmployeeContact(employeeinfform);//保存个人联系信息
//			addMessage(redirectAttributes, "保存个人信息'" + employeeinfform.getXm() + "'成功");
//
//		return "redirect:"+Global.getAdminPath()+"/ip/employee/?repage";
//	}
	
	@RequiresPermissions("ip:employee:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeeService.delete(id);
		addMessage(redirectAttributes, "删除个人信息成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employee/?repage";
	}

	@RequiresUser
	@ResponseBody
	@RequestMapping("treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		
		List<Employee> list = employeeService.findAll();
		for (int i=0; i<list.size(); i++){
			Employee e = list.get(i);
		
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("name", e.getXm());
			mapList.add(map);
		}
		
		return mapList;
	}
	
	@RequiresPermissions("ip:employee:view")
	@RequestMapping(value = "form")
	public String form(Employee employee, Model model) {
		model.addAttribute("employee", employee);
		return "modules/" + "ip/employeeForm";
	}

	@RequiresPermissions("ip:employee:edit")
	@RequestMapping(value = "save")
	public String save(Employee employee, Model model, RedirectAttributes redirectAttributes) {
		
		if (!beanValidator(model, employee)){
			return form(employee, model);
		}
		int a = employeeService.getlist(employee);
		if (a==0){
			addMessage(redirectAttributes, "保存个人信息'" + employee.getXm() + "'失败，工号重复");

		}
		else if (a==2){
			addMessage(redirectAttributes, "保存个人信息'" + employee.getXm() + "'失败，证件号重复");
		}
		else if (a==3){
			addMessage(redirectAttributes, "保存个人信息'" + employee.getXm() + "'失败，序号重复");
		}
		else{	
			employeeService.save(employee);
			addMessage(redirectAttributes, "保存个人信息'" + employee.getXm() + "'成功");

		}
		return "redirect:"+Global.getAdminPath()+"/ip/employee/?repage";
	}
}

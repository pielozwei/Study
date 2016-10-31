/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.test.entity.TestRtable;
import com.thinkgem.jeesite.modules.test.service.TestRtableService;

/**
 * 库房管理Controller
 * @author LiuBJ
 * @version 2016-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testRtable")
public class TestRtableController extends BaseController {

	@Autowired
	private TestRtableService testRtableService;
	
	@ModelAttribute
	public TestRtable get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return testRtableService.get(id);
		}else{
			return new TestRtable();
		}
	}
	
	@RequiresPermissions("test:testRtable:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestRtable testRtable, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			testRtable.setCreateBy(user);
		}
        Page<TestRtable> page = testRtableService.find(new Page<TestRtable>(request, response), testRtable); 
        model.addAttribute("page", page);
		return "modules/" + "test/testRtableList";
	}

	@RequiresPermissions("test:testRtable:view")
	@RequestMapping(value = "form")
	public String form(TestRtable testRtable, Model model) {
		model.addAttribute("testRtable", testRtable);
		return "modules/" + "test/testRtableForm";
	}

	@RequiresPermissions("test:testRtable:edit")
	@RequestMapping(value = "save")
	public String save(TestRtable testRtable, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testRtable)){
			return form(testRtable, model);
		}
		testRtableService.save(testRtable);
		addMessage(redirectAttributes, "保存库房管理'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/test/testRtable/?repage";
	}
	
	@RequiresPermissions("test:testRtable:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		testRtableService.delete(id);
		addMessage(redirectAttributes, "删除库房管理成功");
		return "redirect:"+Global.getAdminPath()+"/test/testRtable/?repage";
	}

}

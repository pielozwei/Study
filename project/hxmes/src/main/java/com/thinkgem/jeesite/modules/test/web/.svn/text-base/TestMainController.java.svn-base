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
import com.thinkgem.jeesite.modules.test.entity.TestMain;
import com.thinkgem.jeesite.modules.test.service.TestMainService;

/**
 * 主子表Controller
 * @author LiuBJ
 * @version 2016-07-28
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testMain")
public class TestMainController extends BaseController {

	@Autowired
	private TestMainService testMainService;
	
	@ModelAttribute
	public TestMain get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return testMainService.get(id);
		}else{
			return new TestMain();
		}
	}
	
	@RequiresPermissions("test:testMain:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestMain testMain, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<TestMain> page = testMainService.find(new Page<TestMain>(request, response), testMain); 
        model.addAttribute("page", page);
		return "modules/" + "test/testMainList";
	}

	@RequiresPermissions("test:testMain:view")
	@RequestMapping(value = "form")
	public String form(TestMain testMain, Model model) {
		model.addAttribute("testMain", testMain);
		return "modules/" + "test/testMainForm";
	}

	@RequiresPermissions("test:testMain:edit")
	@RequestMapping(value = "save")
	public String save(TestMain testMain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testMain)){
			return form(testMain, model);
		}
		testMainService.save(testMain);
		addMessage(redirectAttributes, "保存主子表'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/test/testMain/?repage";
	}
	
	@RequiresPermissions("test:testMain:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		testMainService.delete(id);
		addMessage(redirectAttributes, "删除主子表成功");
		return "redirect:"+Global.getAdminPath()+"/test/testMain/?repage";
	}

}

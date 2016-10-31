/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.test.entity.TestLtree;
import com.thinkgem.jeesite.modules.test.service.TestLtreeService;

/**
 * 左树右表_同一表Controller
 * @author LiuBJ
 * @version 2016-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testLtree")
public class TestLtreeController extends BaseController {

	@Autowired
	private TestLtreeService testLtreeService;
	
	@RequiresPermissions("test:testLtree:view")
	@RequestMapping(value = "")
	public String index() {
		return "modules/test/testLtreeIndex";
	}
	
	@RequiresPermissions("test:testLtree:view")
	@RequestMapping(value = "tree")
	public String tree(Model model) {
		model.addAttribute("categoryList", testLtreeService.findAllTestLtree());
		return "modules/test/testLtreeTree";
	}
	
	@RequiresPermissions("test:testLtree:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/test/testLtreeNone";
	}
	
	@RequiresPermissions("test:testLtree:view")
	@RequestMapping(value = {"list"})
	public String list(TestLtree testLtree, String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		testLtree.setId(id);
        Page<TestLtree> page = testLtreeService.find(new Page<TestLtree>(request, response), testLtree); 
        model.addAttribute("page", page);
		return "modules/test/testLtreeList";
	}

	@RequiresPermissions("test:testLtree:view")
	@RequestMapping(value = "form")
	public String form(TestLtree testLtree, Model model) {
		if (testLtree.getParent()==null || StringUtils.isNotBlank(testLtree.getParent().getId())){
			testLtree.setParent(new TestLtree("1"));
		}
		testLtree.setParent(testLtreeService.getTestLtree(testLtree.getParent().getId()));
		model.addAttribute("testLtree", testLtree);
		return "modules/test/testLtreeForm";
	}

	@RequiresPermissions("test:testLtree:edit")
	@RequestMapping(value = "save")
	public String save(TestLtree testLtree, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testLtree)){
			return form(testLtree, model);
		}
		testLtreeService.saveTestLtree(testLtree);
		addMessage(redirectAttributes, "保存左树右表_同一表'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/test/testLtree/?repage";
	}
	
	@RequiresPermissions("test:testLtree:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		if (TestLtree.isRoot(id)){
			addMessage(redirectAttributes, "删除树节点失败, 不允许删除顶级树节点或编号为空");
		}else{
			testLtreeService.deleteTestLtree(id);
			addMessage(redirectAttributes, "删除树节点成功");
		}
		return "redirect:"+Global.getAdminPath()+"/test/testLtree/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<TestLtree> list = testLtreeService.findAllTestLtree();
		for (int i=0; i<list.size(); i++){
			TestLtree e = list.get(i);
			if (extId == null || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParent()!=null?e.getParent().getId():0);
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}	
}

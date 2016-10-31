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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.test.entity.TestTree;
import com.thinkgem.jeesite.modules.test.service.TestTreeService;

/**
 * 树结构生成Controller
 * @author Admin
 * @version 2016-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testTree")
public class TestTreeController extends BaseController {

	@Autowired
	private TestTreeService testTreeService;
	
	@ModelAttribute("testTree")
	public TestTree get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return testTreeService.getTestTree(id);
		}else{
			return new TestTree();
		}
	}
	
	@RequiresPermissions("test:testTree:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestTree testTree, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TestTree> list = Lists.newArrayList();
		List<TestTree> sourcelist = testTreeService.findAllTestTree();
		TestTree.sortList(list, sourcelist, "1");
        model.addAttribute("list", list);
		return "modules/test/testTreeList";
	}

	@RequiresPermissions("test:testTree:view")
	@RequestMapping(value = "form")
	public String form(TestTree testTree, Model model) {
		if (testTree.getParent()==null || testTree.getParent().getId()==null){
			testTree.setParent(new TestTree("1"));
		}
		testTree.setParent(testTreeService.getTestTree(testTree.getParent().getId()));
		model.addAttribute("testTree", testTree);
		return "modules/test/testTreeForm";
	}

	@RequiresPermissions("test:testTree:edit")
	@RequestMapping(value = "save")
	public String save(TestTree testTree, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testTree)){
			return form(testTree, model);
		}
		testTreeService.saveTestTree(testTree);
		addMessage(redirectAttributes, "保存树结构生成'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/test/testTree/?repage";
	}
	
	@RequiresPermissions("test:testTree:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		if (TestTree.isRoot(id)){
			addMessage(redirectAttributes, "删除树节点失败, 不允许删除顶级树节点或编号为空");
		}else{
			testTreeService.deleteTestTree(id);
			addMessage(redirectAttributes, "删除树节点成功");
		}
		return "redirect:"+Global.getAdminPath()+"/test/testTree/?repage";
	}
	
	@RequestMapping(value = "tree")
	public String tree() {
		return "modules/test/testTree";
	}
	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<TestTree> list = testTreeService.findAllTestTree();
		for (int i=0; i<list.size(); i++){
			TestTree e = list.get(i);
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

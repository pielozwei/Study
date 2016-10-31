/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.zsyb.web;

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
import com.thinkgem.jeesite.modules.zsyb.entity.ZsybTree;
import com.thinkgem.jeesite.modules.zsyb.service.ZsybTreeService;

/**
 * 左树右表结构Controller
 * @author LiuBJ
 * @version 2016-07-14
 */
@Controller
@RequestMapping(value = "${adminPath}/zsyb/zsybTree")
public class ZsybTreeController extends BaseController {

	@Autowired
	private ZsybTreeService zsybTreeService;
	

	@RequiresPermissions("zsyb:zsybTree:view")
	@RequestMapping(value = "")
	public String index() {
		return "modules/zsyb/zsybIndex";
	}
	
	@RequiresPermissions("zsyb:zsybTree:view")
	@RequestMapping(value = "tree")
	public String tree(Model model) {
		model.addAttribute("categoryList", zsybTreeService.findAllZsybTree());
		return "modules/zsyb/zsybTree";
	}
	
	@RequiresPermissions("zsyb:zsybTree:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/zsyb/zsybNone";
	}
	
	@RequiresPermissions("zsyb:zsybTree:view")
	@RequestMapping(value = {"list"})
	public String list(ZsybTree zsybTree, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ZsybTree> list = Lists.newArrayList();
		List<ZsybTree> sourcelist = zsybTreeService.findAllZsybTree();
		ZsybTree.sortList(list, sourcelist, "1");
        model.addAttribute("list", list);
		return "modules/zsyb/zsybTreeList";
	}

	@RequiresPermissions("zsyb:zsybTree:view")
	@RequestMapping(value = "form")
	public String form(ZsybTree zsybTree, Model model) {
		if (zsybTree.getParent()==null || StringUtils.isNotBlank(zsybTree.getParent().getId())){
			zsybTree.setParent(new ZsybTree("1"));
		}
		zsybTree.setParent(zsybTreeService.getZsybTree(zsybTree.getParent().getId()));
		model.addAttribute("zsybTree", zsybTree);
		return "modules/zsyb/zsybTreeForm";
	}

	@RequiresPermissions("zsyb:zsybTree:edit")
	@RequestMapping(value = "save")
	public String save(ZsybTree zsybTree, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zsybTree)){
			return form(zsybTree, model);
		}
		zsybTreeService.saveZsybTree(zsybTree);
		addMessage(redirectAttributes, "保存左树右表结构'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/zsyb/zsybTree/?repage";
	}
	
	@RequiresPermissions("zsyb:zsybTree:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		if (ZsybTree.isRoot(id)){
			addMessage(redirectAttributes, "删除树节点失败, 不允许删除顶级树节点或编号为空");
		}else{
			zsybTreeService.deleteZsybTree(id);
			addMessage(redirectAttributes, "删除树节点成功");
		}
		return "redirect:"+Global.getAdminPath()+"/zsyb/zsybTree/?repage";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ZsybTree> list = zsybTreeService.findAllZsybTree();
		for (int i=0; i<list.size(); i++){
			ZsybTree e = list.get(i);
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

/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.ArrayList;
import java.util.List;

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
import com.thinkgem.jeesite.modules.ip.entity.WorkCenter;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenterCapacity;
import com.thinkgem.jeesite.modules.ip.service.WorkCenterCapacityService;
import com.thinkgem.jeesite.modules.ip.service.WorkCenterService;

/**
 * 工作中心产能配置Controller
 * @author lucl
 * @version 2016-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/gzzx/gzzxcn")
public class WorkCenterCapacityController extends BaseController {

	@Autowired
	private WorkCenterCapacityService gzzxcnService;
	
	@Autowired
	private WorkCenterService gzzxwhService;
	
	@ModelAttribute
	public WorkCenterCapacity get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return gzzxcnService.get(id);
		}else{
			return new WorkCenterCapacity();
		}
	}
	
	@RequiresPermissions("gzzx:gzzxcn:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkCenterCapacity gzzxcn, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			gzzxcn.setCreateBy(user);
		}
        Page<WorkCenterCapacity> page = gzzxcnService.find(new Page<WorkCenterCapacity>(request, response), gzzxcn);
        /*if(null!=gzzxcn&&null!=gzzxcn.getIpGzzxId()&&null!=gzzxcn.getIpGzzxId().getId()&&!gzzxcn.getIpGzzxId().equals("")){
        	model.addAttribute("gzzxId", gzzxcn.getIpGzzxId().getId());
        }*/
        List<WorkCenterCapacity> capacitys = page.getList();
        List<WorkCenterCapacity> capacitys1 = new ArrayList<>();
        for(WorkCenterCapacity wcc : capacitys){
        	try{
        		String gzzxId = wcc.getIpGzzxId().getId();
        		capacitys1.add(wcc);
        	}catch(Exception exception){
        		System.out.println(wcc.getId()+"的所属工作中心已删除！");
        	}
        }
        page.setList(capacitys1);
        model.addAttribute("page", page);
        model.addAttribute("gzzxcn", gzzxcn);
		return "modules/" + "ip/workCenterCapacityList";
	}

	@RequiresPermissions("gzzx:gzzxcn:view")
	@RequestMapping(value = "form")
	public String form(WorkCenterCapacity gzzxcn, Model model) {
		model.addAttribute("gzzxcn", gzzxcn);
		return "modules/" + "ip/workCenterCapacityForm";
	}

	@RequiresPermissions("gzzx:gzzxcn:edit")
	@RequestMapping(value = "save")
	public String save(WorkCenterCapacity gzzxcn, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzzxcn)){
			return form(gzzxcn, model);
		}
		gzzxcnService.save(gzzxcn);
		addMessage(redirectAttributes, "保存工作中心产能配置成功");
		return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxcn/?&ipGzzxId.id="+gzzxcn.getIpGzzxId().getId();
	}
	
	@RequiresPermissions("gzzx:gzzxcn:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		gzzxcnService.delete(id);
		addMessage(redirectAttributes, "删除工作中心产能配置成功");
		return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxcn/?repage";
	}
	/**
	 * 方法功能：弹出工作中心班次配置主界面
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxcn:view")
	@RequestMapping(value = "start")
	public String index(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="gzzxcn";
		}
		model.addAttribute("module", module);
		return "modules/ip/workCenterCapacityIndex";
	}
	/**
	 * 方法功能：生成仓库树
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxcn:view")
	@RequestMapping(value = "tree")
	public String tree(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="gzzxcn";
		}
		model.addAttribute("module", module);
		model.addAttribute("gzzxList", gzzxwhService.findTree(true, null));
		List<WorkCenter> list=gzzxwhService.findTree(true, null);
		return "modules/ip/workCenterTree";
	}
	/**
	 * 批量删除
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cangku:ck:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		int j=0;
		for(String id :ids){
			gzzxcnService.delete(id);
			j++;
		}
		addMessage(redirectAttributes, "删除"+j+"个产能信息！");
		return "redirect:"+Global.getAdminPath()+"/cangku/ck/none";
	}
}

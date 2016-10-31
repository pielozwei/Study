/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

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
import com.thinkgem.jeesite.modules.ip.entity.StandardProduct;
import com.thinkgem.jeesite.modules.ip.service.StandardProductService;

/**
 * 标准产品Controller
 * @author lucl
 * @version 2016-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/standardProduct")
public class StandardProductController extends BaseController {

	@Autowired
	private StandardProductService standardProductService;
	
	@ModelAttribute
	public StandardProduct get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return standardProductService.get(id);
		}else{
			return new StandardProduct();
		}
	}
	
	@RequiresPermissions("ip:standardProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(StandardProduct standardProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			standardProduct.setCreateBy(user);
		}
        Page<StandardProduct> page = standardProductService.find(new Page<StandardProduct>(request, response), standardProduct); 
        model.addAttribute("page", page);
		return "modules/" + "ip/standardProductList";
	}

	@RequiresPermissions("ip:standardProduct:view")
	@RequestMapping(value = "form")
	public String form(StandardProduct standardProduct, Model model) {
		model.addAttribute("standardProduct", standardProduct);
		return "modules/" + "ip/standardProductForm";
	}

	@RequiresPermissions("ip:standardProduct:edit")
	@RequestMapping(value = "save")
	public String save(StandardProduct standardProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, standardProduct)){
			return form(standardProduct, model);
		}
		standardProductService.save(standardProduct);
		addMessage(redirectAttributes, "保存标准产品'" + standardProduct.getCpmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/standardProduct?repage";
	}
	
	@RequiresPermissions("ip:standardProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		standardProductService.delete(id);
		addMessage(redirectAttributes, "删除标准产品成功");
		return "redirect:"+Global.getAdminPath()+"/ip/standardProduct/?repage";
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
			standardProductService.delete(id);
			j++;
		}
		addMessage(redirectAttributes, "删除"+j+"个产品定义！");
		return "redirect:"+Global.getAdminPath()+"/ip/standardProduct/list";
	}

}

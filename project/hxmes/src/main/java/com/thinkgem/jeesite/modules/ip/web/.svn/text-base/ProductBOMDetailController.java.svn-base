/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMDetail;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMSheet;
import com.thinkgem.jeesite.modules.ip.service.ProductBOMDetailService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * BOM明细Controller
 * @author ZhangHD
 * @version 2016-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/productBOMDetail")
public class ProductBOMDetailController extends BaseController {
	@Autowired
	private ProductBOMDetailService productBOMDetailService;


	@ModelAttribute
	public ProductBOMDetail get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return productBOMDetailService.get(id);
		} else {
			return new ProductBOMDetail();
		}
	}


	@RequestMapping(value = { "list", "" })
	public String list(String type, ProductBOMDetail productBOMDetail, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()) {
			productBOMDetail.setCreateBy(user);
		}
		Page<ProductBOMDetail> page = productBOMDetailService.find(new Page<ProductBOMDetail>(request, response),
				productBOMDetail, type);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		return "modules/" + "ip/productBOMDetailList";
	}


	@RequestMapping(value = "form")
	public String form(ProductBOMDetail productBOMDetail, Model model) {
		model.addAttribute("productBOMDetail", productBOMDetail);
		return "modules/" + "ip/productBOMDetailForm";
	}


	@RequestMapping(value = "save")
	public String save(String type, ProductBOMDetail productBOMDetail, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productBOMDetail)) {
			return form(productBOMDetail, model);
		}
		productBOMDetailService.save(productBOMDetail);
		String productBOMSheetId = productBOMDetail.getProductBOMSheet().getId();
		addMessage(redirectAttributes, "保存BOM明细'" + productBOMDetail.getZwlbm().getWlmc() + "'成功");
		if ("add".equals(type)) {
			ProductBOMDetail productBOMDetail1 = new ProductBOMDetail();
			ProductBOMSheet productBOMSheet = new ProductBOMSheet();
			productBOMSheet.setId(productBOMSheetId);
			productBOMDetail1.setProductBOMSheet(productBOMSheet);
			model.addAttribute("productBOMDetail", productBOMDetail1);
			addMessage(model, "保存BOM明细'" + productBOMDetail.getZwlbm().getWlmc() + "'成功");
			return "modules/" + "ip/productBOMDetailForm";
		}
		return "redirect:" + Global.getAdminPath() + "/ip/productBOMDetail/?repage&productBOMSheet.id="
				+ productBOMSheetId;
	}


	@RequestMapping(value = "delete")
	public String delete(String[] ids, ProductBOMDetail productBOMDetail, RedirectAttributes redirectAttributes) {
		String proID = productBOMDetailService.get(ids[0]).getProductBOMSheet().getId();
		for (String id : ids) {
			productBOMDetailService.delete(id);
		}
		addMessage(redirectAttributes, "删除BOM明细成功");
		return "redirect:" + Global.getAdminPath() + "/ip/productBOMDetail/?productBOMSheet.id=" + proID;
	}
}

/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMDetail;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMSheet;
import com.thinkgem.jeesite.modules.ip.service.ProductBOMDetailService;
import com.thinkgem.jeesite.modules.ip.service.ProductBOMSheetService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 基本信息Controller
 * @author ZhangHD
 * @version 2016-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/productBOMSheet")
public class ProductBOMSheetController extends BaseController {
	@Autowired
	private ProductBOMSheetService productBOMSheetService;
	@Autowired
	private ProductBOMDetailService productBOMDetailService;


	@ModelAttribute
	public ProductBOMSheet get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return productBOMSheetService.get(id);
		} else {
			return new ProductBOMSheet();
		}
	}


	@RequiresPermissions("chanpin:productBOMSheet:view")
	@RequestMapping(value = { "list", "" })
	public String list(ProductBOMSheet productBOMSheet, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()) {
			productBOMSheet.setCreateBy(user);
		}
		Page<ProductBOMSheet> page = productBOMSheetService.find(new Page<ProductBOMSheet>(request, response),
				productBOMSheet);
		model.addAttribute("page", page);
		return "modules/" + "ip/productBOMSheetList";
	}


	@RequiresPermissions("chanpin:productBOMSheet:view")
	@RequestMapping(value = "form")
	public String form(ProductBOMSheet productBOMSheet, Model model, String type) {
		model.addAttribute("productBOMSheet", productBOMSheet);
		model.addAttribute("type", type);
		return "modules/" + "ip/productBOMSheetForm";
	}


	@RequiresPermissions("chanpin:productBOMSheet:edit")
	@RequestMapping(value = "save")
	public String save(String type, ProductBOMSheet productBOMSheet, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productBOMSheet)) {
			return form(productBOMSheet, model, type);
		}
		productBOMSheetService.save(productBOMSheet);
		addMessage(redirectAttributes, "保存1条数据成功");
		return "redirect:" + Global.getAdminPath() + "/ip/productBOMSheet/?repage";
	}


	@RequiresPermissions("chanpin:productBOMSheet:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		for (String id : ids) {
			List<ProductBOMDetail> lists = productBOMDetailService.findBomId(id);
			if (lists.size() > 0) {
				addMessage(redirectAttributes, "有子记录数据无法删除，请先删除子记录！");
				return "redirect:" + Global.getAdminPath() + "/ip/productBOMSheet/?repage";
			} else {
				productBOMSheetService.delete(id);
			}
		}
		addMessage(redirectAttributes, "删除" + ids.length + "条数据成功");
		return "redirect:" + Global.getAdminPath() + "/ip/productBOMSheet/?repage";
	}


	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(String module, HttpServletResponse response, String id) {
		response.setContentType("application/json; charset=UTF-8");
		List<Material> list = productBOMSheetService.findByUserWl(true, null);
		String wlbmStr = "";// 当前bom的物料编码
		String parwlbmStr = "";// 当前bom的上级bom的物料编码
		if (!StringUtils.isEmpty(id)) {
			// 获取当前bom的物料编码
			ProductBOMSheet productBOMSheet = productBOMSheetService.get(id);
			wlbmStr = productBOMSheet.getWlbm().getId();
			// 根据单签bom的物料编码查看是否存在bom明细，获取bom的ID ，根据ID获取物料编码
			ProductBOMDetail productBOMDetail = productBOMDetailService.findByIsBommx(wlbmStr);
			if (productBOMDetail != null) {
				ProductBOMSheet productBOMSheet1 = productBOMSheetService.get(id);
				parwlbmStr = productBOMSheet1.getWlbm().getId();
			}
		}
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Material wlbm : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", wlbm.getId());
			map.put("name", wlbm.getWlmc());
			map.put("module", module);
			if (!StringUtils.isEmpty(wlbmStr) && wlbm.getId().equals(wlbmStr) || !StringUtils.isEmpty(parwlbmStr)
					&& wlbm.getId().equals(parwlbmStr)) {
				// 去除bom本身物料编码和当前bom的上级BOM的物料编码
				continue;
			} else {
				listMap.add(map);
			}
		}
		return listMap;
	}


	@RequestMapping(value = "bomIndex")
	public String bomIndex(Model model, String module, ProductBOMSheet productBOMSheet) {
		model.addAttribute("module", "productBOMDetail");
		model.addAttribute("productBOMSheet.cpbomdbh", productBOMSheet);
		return "modules/ip/productIndex";
	}


	@RequestMapping(value = "tree")
	public String tree(Model model, ProductBOMSheet productBOMSheet) {
		model.addAttribute("detailList", productBOMDetailService.findNodes(productBOMSheet));
		return "modules/ip/productTree";
	}


	@RequestMapping(value = "none")
	public String none() {
		return "modules/ip/productNone";
	}


	// 通过bb, wlbm, cpbomdbh获取BOM单,唯一验证
	@RequestMapping("checkCpbomdbh")
	public void checkCpbomdbh(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String erroMsg = "";// 提示错误信息
		String bb = request.getParameter("bb");
		String cpbomdbh = request.getParameter("cpbomdbh");
		String wlbm = request.getParameter("wlbm");
		String sfqy = request.getParameter("sfqy");
		String id = request.getParameter("id");
		// 通过bb, wlbm, cpbomdbh获取BOM单
		ProductBOMSheet productBOMSheet = productBOMSheetService.findByOnlyCpbomdbh(bb, wlbm, cpbomdbh);
		if (productBOMSheet != null) {
			if (StringUtils.isEmpty(id)) {
				// 如果是新增，BOM已存在提示。
				erroMsg = "相同版本、物料、BOM单号已存在，请修改之后在进行保存。";
				response.getWriter().print(erroMsg);
			} else {
				if (!productBOMSheet.getId().equals(id)) {
					// 如果是修改，通过ID判断是不是本身，如果不是本身提示
					erroMsg = "相同版本、物料、BOM单号已存在，请修改之后在进行保存。";
					response.getWriter().print(erroMsg);
				}
			}
		}
		if ("".equals(erroMsg)) {
			soleStart(sfqy,id, wlbm, response);
		}
	}


	// 校验一个物料编码的BOM单只有-个是启用状态
	public void soleStart(String sfqy,String id, String wlbm, HttpServletResponse response) throws IOException {
		// 判断启用状态下
		String erroMsg = "";// 提示错误信息
		if (sfqy.equals("1")) {
			List<ProductBOMSheet> productBOMSheets = productBOMSheetService.findWlbmState(wlbm);
			
			if (productBOMSheets.size() > 0) {
				if(!productBOMSheets.get(0).getId().equals(id)){
					erroMsg = "此物料为启用状态的信息只能有一条，请修改之后在进行保存。";
					response.getWriter().print(erroMsg);
				}
			}
		}
	}
}

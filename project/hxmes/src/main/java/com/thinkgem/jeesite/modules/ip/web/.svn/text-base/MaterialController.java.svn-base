/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.service.MaterialService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 物料编码信息Controller
 * @author ZhangHD
 * @version 2016-06-03
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/wlbm")
public class MaterialController extends BaseController {
	@Autowired
	private MaterialService wlbmService;


	@ModelAttribute
	public Material get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return wlbmService.get(id);
		} else {
			return new Material();
		}
	}


	@RequiresPermissions("wuliao:wlbm:view")
	@RequestMapping(value = { "list", "" })
	public String list(Material wlbm, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()) {
			wlbm.setCreateBy(user);
		}
		Page<Material> page = wlbmService.find(new Page<Material>(request, response), wlbm);
		String _module = request.getParameter("_module");
		if (StringUtils.isEmpty(_module)) {
			_module = "wlbm";
		}
		model.addAttribute("page", page);
		model.addAttribute("_module", _module);
		model.addAttribute("wlbm", wlbm);
		return "modules/" + "ip/materialList";
	}


	@RequiresPermissions("wuliao:wlbm:view")
	@RequestMapping(value = "form")
	public String form(Material wlbm, Model model) {
		model.addAttribute("wlbm", wlbm);
		return "modules/" + "ip/materialForm";
	}


	@RequiresPermissions("wuliao:wlbm:edit")
	@RequestMapping(value = "save")
	public String save(String wllbId, Material wlbm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wlbm)) {
			return form(wlbm, model);
		}
		wlbmService.save(wlbm);
		addMessage(redirectAttributes, "保存物料编码信息'" + wlbm.getWlmc() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/ip/wlbm/?repage&_module=wlbm&wllb.id="
				+ (wllbId != null ? wllbId : "");
	}


	@RequiresPermissions("wuliao:wlbm:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String wllbId = wlbmService.get(ids[0]).getWllb().getId();
		for (String id : ids) {
			try {
				wlbmService.delete(id);
			} catch (Exception e) {
				String str = e.getCause().getCause().getMessage();
				String[] msg = str.split(":");
				if (msg[0].equals("ORA-02292")) {
					addMessage(redirectAttributes, "该基本信息下有子记录，无法删除！");
					return "redirect:" + Global.getAdminPath() + "/ip/wlbm/?repage&_module=wlbm&wllb.id="
					+ (wllbId != null ? wllbId : "");
				}
			}
		}
		addMessage(redirectAttributes, "删除物料编码信息成功");
		return "redirect:" + Global.getAdminPath() + "/ip/wlbm/?repage&_module=wlbm&wllb.id="
				+ (wllbId != null ? wllbId : "");
	}


	@ResponseBody
	@RequestMapping("checkWlbm")
	public String checkWlbm(String wlbm, String oldWlbm) {
		if (wlbm != null && oldWlbm.equals(wlbm)) {
			return "true";
		} else if (wlbm != null && wlbmService.findByWlbm(wlbm) == null) {
			return "true";
		}
		return "false";
	}


	@RequiresPermissions("wuliao:wlbm:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(Material wlbm, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "物料信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<Material> page = wlbmService.find(new Page<Material>(request, response), wlbm);
			new ExportExcel("物料信息", Material.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出物料信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/wlbm/?repage";
	}
}

/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentFault;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentFaultService;

/**
 * 故障树Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentFault")
public class EquipmentFaultController extends BaseController {
	@Autowired
	private EquipmentFaultService equipmentFaultService;
	@Autowired
	private EquipmentModelService equipmentModelService;


	@ModelAttribute
	public EquipmentFault get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return equipmentFaultService.get(id);
		} else {
			return new EquipmentFault();
		}
	}


	@RequiresPermissions("ip:equipmentFault:view")
	@RequestMapping(value = { "list", "" })
	public String list(EquipmentFault equipmentFault, Map<String, Object> displayColumnsMap,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()) {
			equipmentFault.setCreateBy(user);
		}
		String equipmentModelId = request.getParameter("equipmentModelId");
		model.addAttribute("equipmentModelId", equipmentModelId);
		Page<EquipmentFault> paramPage = new Page<EquipmentFault>(request, response);
		Page<EquipmentFault> page = equipmentFaultService.find(paramPage, equipmentFault, equipmentModelId);
		model.addAttribute("page", page);
		return "modules/" + "ip/equipmentFaultList";
	}


	@RequiresPermissions("ip:equipmentFault:view")
	@RequestMapping(value = "form")
	public String form(EquipmentFault equipmentFault, Model model) {
		String equipmentModelId = null;
		if (equipmentFault.getEquipmentModel() != null
				&& StringUtils.isNotBlank(equipmentFault.getEquipmentModel().getId())) {
			equipmentModelId = equipmentFault.getEquipmentModel().getId();
			equipmentFault.setEquipmentModel(equipmentModelService.get(equipmentModelId));
		}
		if (StringUtils.isBlank(equipmentFault.getId())) {// 添加给个编码
			List<EquipmentFault> list = equipmentFaultService.findAll();
			model.addAttribute("bm", list.size() + 1);
		}
		model.addAttribute("equipmentFault", equipmentFault);
		return "modules/" + "ip/equipmentFaultForm";
	}


	@RequiresPermissions("ip:equipmentFault:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentFault equipmentFault, Model model, RedirectAttributes redirectAttributes) {
		String equipmentModelId = equipmentFault.getEquipmentModel() != null ? equipmentFault
				.getEquipmentModel()
				.getId() : "";
		if (!beanValidator(model, equipmentFault)) {
			return form(equipmentFault, model);
		}
		equipmentFaultService.save(equipmentFault);
		addMessage(redirectAttributes, "保存故障树'" + equipmentFault.getGzbm() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentFault/?equipmentModelId=" + equipmentModelId;
	}


	@RequiresPermissions("ip:equipmentFault:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String equipmentModelId = equipmentFaultService.get(ids[0]).getEquipmentModel().getId();
		for (String id : ids) {
			try {
				equipmentFaultService.delete(id);
			} catch (DataIntegrityViolationException a) {
				// TODO: handle exception
			}
			
		}
		addMessage(redirectAttributes, "删除" + ids.length + "个故障树成功");
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentFault/?equipmentModelId="
				+ (equipmentModelId != null ? equipmentModelId : "");
	}


	@RequiresPermissions("ip:equipmentFault:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(EquipmentFault equipmentFault, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		String equipmentModelId = request.getParameter("equipmentModelId");
		try {
			String fileName = "故障树数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<EquipmentFault> page = equipmentFaultService.find(new Page<EquipmentFault>(request, response),
					equipmentFault, null);
			new ExportExcel("故障树数据", EquipmentFault.class)
					.setDataList(page.getList())
					.write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出故障树失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentFault/?equipmentModelId=" + equipmentModelId;
	}


	@RequiresPermissions("ip:equipmentFault:view")
	@RequestMapping(value = "downLoad", method = RequestMethod.POST)
	public String downLoadFile(EquipmentFault equipmentFault, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		String equipmentModelId = request.getParameter("equipmentModelId");
		try {
			// 获得请求文件名
			// String filename = request.getParameter("filename");
			String url = request.getParameter("url");
			// 文件后缀
			int i = url.lastIndexOf("/");
			String str = url.substring(i + 1, url.length());
			String houzhui = str.substring(str.indexOf("."), str.length());
			// 解决中文乱码问题
			String filename = new String(request.getParameter("filename").getBytes("utf-8"), "iso-8859-1");
			// 设置response的编码方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + filename + DateUtils.getDate("yyyyMMddHHmmss") + houzhui);
			// 读取目标文件，通过response将目标文件写到客户端
			// 获取目标文件的绝对路径
			String xmmc = request.getContextPath().replace("/", "") + "\\\\";
			@SuppressWarnings("deprecation")
			String fullFileName = request.getRealPath(url);
			fullFileName = fullFileName.replaceFirst(xmmc, "");
			// 读取文件
			InputStream in = new FileInputStream(fullFileName);
			OutputStream out = response.getOutputStream();
			// 写文件
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
			out.close();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "下载失败！失败信息：" + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentFault/?equipmentModelId=" + equipmentModelId;
	}


	@ResponseBody
	@RequestMapping("checkGzbm")
	public String checkGzbm(String gzbm, String oldGzbm) {
		if (gzbm != null && oldGzbm.equals(gzbm)) {
			return "true";
		} else if (gzbm != null && equipmentFaultService.findByGzbm(gzbm) == null) {
			return "true";
		}
		return "false";
	}
}

/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ip.dao.StoreDao;
import com.thinkgem.jeesite.modules.ip.entity.Store;
import com.thinkgem.jeesite.modules.ip.service.StoreService;

/**
 * 仓库Controller
 * @author Lucl
 * @version 2016-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/cangku/ck")
public class StoreController extends BaseController {

	@Autowired
	private StoreService ckService;
	
	@Autowired
	private StoreDao ckDao;
	
	@ModelAttribute
	public Store get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return ckService.get(id);
		}else{
			return new Store();
		}
	}
	
	@RequiresPermissions("cangku:ck:view")
	@RequestMapping(value = {"list"})
	public String list(Store ck,String name1, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			ck.setCreateBy(user);
		}
		if(null!=name1&&!name1.equals("")){
			model.addAttribute("name1", name1);
			//ck.setMc(name1);
		}else{
			model.addAttribute("name1","");
			//ck.setMc("");
		}
        Page<Store> page = ckService.find(new Page<Store>(request, response), ck,name1); 
        model.addAttribute("page", page);
        model.addAttribute("ck", ck);
		return "modules/" + "ip/storeList";
	}

	@RequiresPermissions("cangku:ck:view")
	@RequestMapping(value = "form")
	public String form(Store ck, Model model,String parentId) {
		model.addAttribute("ck", ck);
		model.addAttribute("id", ck.getId());
		/*if(null!=parentId&&!parentId.equals("")){
			model.addAttribute("parentId", parentId);
		}else{
			model.addAttribute("parentId", ck.getSjjd().getId());
		}*/
		return "modules/" + "ip/storeForm";
	}

	@RequiresPermissions("cangku:ck:edit")
	@RequestMapping(value = "save")
	public String save(Store ck, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ck)){
			return form(ck, model,ck.getSjjd().getId());
		}
		if(null!=ck.getId()&&null!=ck.getSjjd().getId()&&ck.getId().equals(ck.getSjjd().getId())){
			addMessage(redirectAttributes, "不能选自己作为父节点！");
			return "redirect:"+Global.getAdminPath()+"/cangku/ck/list";
		}else{
			if((null!=ck&&(null!=ck.getXssx()&&ck.getXssx().equals("")))||(null!=ck&&null==ck.getXssx())){
				ck.setXssx(100);
			}
			ckService.save(ck);
			/*if(ck.getXssx()==null||ck.getXssx().equals("")){
				ck.setXssx(100);
			}*/
			addMessage(redirectAttributes, "保存仓库'" + ck.getMc() + "'成功");
			return "redirect:"+Global.getAdminPath()+"/cangku/ck/list?id="+ck.getSjjd().getId();
		}	
	}
	
	@RequiresPermissions("cangku:ck:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes,String parentId) {
		String[] ids={id};
		Map<String,String> mapper=ckService.checkHasChild(ids);
		String flag=mapper.get("flag");
		if(flag=="1"){
			//ckService.delete(id);
			addMessage(redirectAttributes, "删除仓库失败，删除节点不能有子节点");
			return "redirect:"+Global.getAdminPath()+"/cangku/ck/list?id="+parentId;
		}else{
			ckService.delete(id);
			addMessage(redirectAttributes, "删除仓库成功");
			return "redirect:"+Global.getAdminPath()+"/cangku/ck/list?id="+parentId;
		}
	}
	
	/**
	 * 方法功能：点击仓库管理弹出主界面
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("cangku:ck:view")
	@RequestMapping(value = "")
	public String index(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="ck";
		}
		model.addAttribute("module", module);
		return "modules/ip/storeIndex";
	}
	
	/**
	 * 方法功能：生成仓库树
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("cangku:ck:view")
	@RequestMapping(value = "tree")
	public String tree(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="ck";
		}
		model.addAttribute("module", module);
		model.addAttribute("ckList", ckService.findTree(true, null));
		List<Store> list=ckService.findTree(true, null);
		return "modules/ip/storeTree";
	}
	
	/**
	 * 方法功能：显示提示信息
	 * @return
	 */
	@RequiresPermissions("cangku:ck:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/ip/storeNone";
	}
	/**
	 * 方法功能：查询当前仓库是否有子节点
	 * @return
	 */
	@RequiresPermissions("cangku:ck:view")
	@RequestMapping(value = "checkHasChild")
	public String checkHasChild(String[] ids,HttpServletResponse response) {
		Map<String,String> mapper=ckService.checkHasChild(ids);
		JSONObject map=new JSONObject();
		String flag=mapper.get("flag");
		if(flag=="1"){
			map.put("flag", "1");
			map.put("mc", mapper.get("mc"));
		}else{
			map.put("flag", "0");
		}
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(map.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
			ckService.delete(id);
			j++;
		}
		addMessage(redirectAttributes, "删除"+j+"个仓库！");
		return "redirect:"+Global.getAdminPath()+"/cangku/ck/none";
	}
	/**
	 * 导出仓库信息到excel电子表格中
	 * @param sbcc
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("shebei:sbcc:edit")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Store ck,String name1, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "仓库数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
			Page<Store> page = ckService.findSuns(new Page<Store>(request, response), ck,name1); 
    		new ExportExcel("仓库数据", Store.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出设备层次数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/cangku/ck/?id="+ck.getId();
    }
	/**
	 * 批量禁用启用
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cangku:ck:edit")
	@RequestMapping(value = "updateStateList")
	public String updateStateList(String[] ids,String state, RedirectAttributes redirectAttributes) {
		int j=0;
		String sjjdId=ckDao.get(ids[0]).getSjjd().getId();
		for(String id :ids){
			ckService.updateStateList(id,state);
			j++;
		}
		if(null!=state&&state.equals("1")){
			addMessage(redirectAttributes, "禁用"+j+"个仓库！");
		}else{
			addMessage(redirectAttributes, "启用"+j+"个仓库！");
		}
		return "redirect:"+Global.getAdminPath()+"/cangku/ck/list?id="+sjjdId;
	}
	/**
	 * 验证编码是否唯一
	 * @param val1
	 */
	@RequiresPermissions("cangku:ck:view")
	@RequestMapping(value = "validation")
	public void validation(String val1,HttpServletResponse response){
		//查询编码名称是否重复
		boolean flag=ckService.validation(val1);
		JSONObject map=new JSONObject();
		if(!flag){
			map.put("msg", "编码重复");
		}else{
			map.put("msg", "");
		}
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(map.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.gen.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.FreeMarkers;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.gen.entity.GenConfig;
import com.thinkgem.jeesite.modules.gen.entity.GenScheme;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.gen.entity.GenTableColumn;
import com.thinkgem.jeesite.modules.gen.entity.GenTemplate;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import com.thinkgem.jeesite.modules.gen.dao.GenSchemeDao;
import com.thinkgem.jeesite.modules.gen.dao.GenTableColumnDao;
import com.thinkgem.jeesite.modules.gen.dao.GenTableDao;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 生成方案Service
 * @author LiuBaoJ
 * @version 2016-06-21
 */
@Component
@Transactional(readOnly = true)
public class GenSchemeService extends BaseService {

	@Autowired
	private GenSchemeDao genSchemeDao;
	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	@Autowired
	private SystemService systemService;
	
	public GenScheme get(String id) {
		return genSchemeDao.get(id);
	}
	
	public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
		DetachedCriteria dc = genSchemeDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(genScheme.getName())){
			dc.add(Restrictions.like("name", "%"+genScheme.getName()+"%"));
		}
		dc.add(Restrictions.eq(GenScheme.FIELD_DEL_FLAG, GenScheme.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return genSchemeDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public String save(GenScheme genScheme) {
		// 生成代码
		String result = "";
		try {
			if ("1".equals(genScheme.getFlag()))
				result=generateCode(genScheme);
			//保存		
			if (StringUtils.isBlank(genScheme.getId())){
				genScheme.prePersist();
			}else{
				genScheme.preUpdate();
			}
			genSchemeDao.save(genScheme);				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		genSchemeDao.deleteById(id);
	}
	
	private String generateCode(GenScheme genScheme) throws IOException{

		StringBuilder result = new StringBuilder();
		
		// 查询主表及字段列
		GenTable genTable = genTableDao.get(genScheme.getGenTableId());
		GenTable genTable_child=null;
		List<GenTable> genTable_childs=genTableDao.findByParentTableName(genTable.getName().trim());
		if(!genTable_childs.isEmpty())
			genTable_child=genTable_childs.get(0);
		//List<GenTableColumn> columns= genTableColumnDao.findList(genScheme.getGenTableId());
		genTable.setColumnList(genTableColumnDao.findList(genScheme.getGenTableId()));
		
		// 生成主表模板代码
		//genScheme.setGenTable(genTable);
		Map<String, Object> model = GenUtils.getDataModel(genScheme,genTable.getClassName(),genTable_child);
		//model.put("tableName", genTable.getName());
		model.put("table", genTable);
		
		// 获取文件分隔符
		String separator = File.separator;
		
		// 获取工程路径
		//String projectPath="D:/eclipse/workspace/hxmes";
		String projectPath=Global.getProjectPath(); 
		// 模板文件路径
		String tplPath = StringUtils.replace(projectPath+"/src/main/java/com/thinkgem/jeesite/generate/template", "/", separator);
		// Java文件路径
		String javaPath = StringUtils.replaceEach(projectPath+"/src/main/java/"+StringUtils.lowerCase(genScheme.getPackageName()), 
				new String[]{"/", "."}, new String[]{separator, separator});
		logger.info("Java Path: {}", javaPath);
		
		// 视图文件路径
		String viewPath = StringUtils.replace(projectPath+"/src/main/webapp/WEB-INF/views", "/", separator);
		logger.info("View Path: {}", viewPath);
		
		// 代码模板配置
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setDirectoryForTemplateLoading(new File(tplPath));
		
		//模版分类
		String category=genScheme.getCategory().trim();
		String common_category=category.indexOf("_")>-1?category.split("_")[0]:category;
		Template template;
		String content,filePath;
		
		// 生成 Entity
		if("many".equals(category)){//主子表
			//主表_entity
			template = cfg.getTemplate("many_main_entity.ftl");
			content = FreeMarkers.renderTemplate(template, model);
			filePath = javaPath+separator+model.get("moduleName")+separator+"entity"
					+separator+StringUtils.lowerCase(genScheme.getSubModuleName())+separator+model.get("ClassName")+".java";
			writeFile(content, filePath);
			logger.info("Entity: {}", filePath);
			
			//子表_entity
			model.put("table_child", genTable_child);
			template = cfg.getTemplate("many_child_entity.ftl");
			content = FreeMarkers.renderTemplate(template, model);
			filePath = javaPath+separator+model.get("moduleName")+separator+"entity"
					+separator+StringUtils.lowerCase(genScheme.getSubModuleName())+separator+model.get("ClassName_child")+".java";
			writeFile(content, filePath);
			logger.info("Entity: {}", filePath);	
			
			// 主表 Dao
			template = cfg.getTemplate("many_main_dao.ftl");
			content = FreeMarkers.renderTemplate(template, model);
			filePath = javaPath+separator+model.get("moduleName")+separator+"dao"+separator
					+StringUtils.lowerCase(genScheme.getSubModuleName())+separator+model.get("ClassName")+"Dao.java";
			writeFile(content, filePath);
			logger.info("Dao: {}", filePath);
			
			// 子表Dao
			template = cfg.getTemplate("many_child_dao.ftl");
			content = FreeMarkers.renderTemplate(template, model);
			filePath = javaPath+separator+model.get("moduleName")+separator+"dao"+separator
					+StringUtils.lowerCase(genScheme.getSubModuleName())+separator+model.get("ClassName_child")+"Dao.java";
			writeFile(content, filePath);
			logger.info("Dao: {}", filePath);
			
		}else{
			template = cfg.getTemplate(common_category+"_entity.ftl");
			content = FreeMarkers.renderTemplate(template, model);
			filePath = javaPath+separator+model.get("moduleName")+separator+"entity"
					+separator+StringUtils.lowerCase(genScheme.getSubModuleName())+separator+model.get("ClassName")+".java";
			writeFile(content, filePath);
			logger.info("Entity: {}", filePath);
		
			// 生成 Dao
			template = cfg.getTemplate(common_category+"_dao.ftl");
			content = FreeMarkers.renderTemplate(template, model);
			filePath = javaPath+separator+model.get("moduleName")+separator+"dao"+separator
					+StringUtils.lowerCase(genScheme.getSubModuleName())+separator+model.get("ClassName")+"Dao.java";
			writeFile(content, filePath);
			logger.info("Dao: {}", filePath);
		}
		if("dao".equals(category))
			return result.toString();
		
		// 生成 Service
		template = cfg.getTemplate(common_category+"_service.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"service"+separator
				+StringUtils.lowerCase(genScheme.getSubModuleName())+separator+model.get("ClassName")+"Service.java";
		writeFile(content, filePath);
		logger.info("Service: {}", filePath);
		
		// 生成 Controller
		template = cfg.getTemplate(category+"_controller.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"web"+separator
				+StringUtils.lowerCase(genScheme.getSubModuleName())+separator+model.get("ClassName")+"Controller.java";
		writeFile(content, filePath);
		logger.info("Controller: {}", filePath);
		
		// 生成 ViewForm
		template = cfg.getTemplate(common_category+"_viewForm.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName").toString(),".")
				+separator+model.get("moduleName")+separator+StringUtils.lowerCase(genScheme.getSubModuleName())
				+separator+model.get("className")+"Form.jsp";
		writeFile(content, filePath);
		logger.info("ViewForm: {}", filePath);
		
		// 生成 ViewList
		template = cfg.getTemplate(category+"_viewList.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName").toString(),".")
				+separator+model.get("moduleName")+separator+StringUtils.lowerCase(genScheme.getSubModuleName())
				+separator+model.get("className")+"List.jsp";
		writeFile(content, filePath);
		logger.info("ViewList: {}", filePath);
		
		if("tree_outTable".equals(category)){//左树右表
			// 生成index.jsp
			template = cfg.getTemplate(category+"_index.ftl");
			content = FreeMarkers.renderTemplate(template, model);
			filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName").toString(),".")
					+separator+model.get("moduleName")+separator+StringUtils.lowerCase(genScheme.getSubModuleName())
					+separator+model.get("className")+"Index.jsp";
			writeFile(content, filePath);
			logger.info("ViewList: {}", filePath);
			
			// 生成none.jsp
			template = cfg.getTemplate(category+"_none.ftl");
			content = FreeMarkers.renderTemplate(template, model);
			filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName").toString(),".")
					+separator+model.get("moduleName")+separator+StringUtils.lowerCase(genScheme.getSubModuleName())
					+separator+model.get("className")+"None.jsp";
			writeFile(content, filePath);
			logger.info("ViewList: {}", filePath);
			
			// 生成 tree.jsp
			template = cfg.getTemplate(category+"_tree.ftl");
			content = FreeMarkers.renderTemplate(template, model);
			filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName").toString(),".")
					+separator+model.get("moduleName")+separator+StringUtils.lowerCase(genScheme.getSubModuleName())
					+separator+model.get("className")+"Tree.jsp";
			writeFile(content, filePath);
			logger.info("ViewList: {}", filePath);			
		}
		
		//生成菜单
		if("0".equals(genScheme.getMenuFlag().trim())){
			Menu menu=new Menu();
			menu.setParent(new Menu("202"));
			menu.setName(genScheme.getFunctionNameSimple());
			String href="/"+genScheme.getModuleName()+"/"+StringUtils.uncapitalize(genTable.getClassName());
			menu.setHref(href);		
			menu.setSort(30);
			menu.setIsShow("1");
			menu.setIsActiviti("0");
			systemService.saveMenu(menu);
			
			Menu menu1=new Menu();
			menu1.setParent(menu);
			menu1.setName("查看");
			menu1.setSort(30);
			menu1.setIsShow("0");
			menu1.setIsActiviti("0");
			menu1.setPermission(genScheme.getModuleName()+":"+StringUtils.uncapitalize(genTable.getClassName())+":view");
			systemService.saveMenu(menu1);		
	
			Menu menu2=new Menu();
			menu2.setParent(menu);
			menu2.setName("修改");
			menu2.setSort(30);
			menu2.setIsShow("0");
			menu2.setIsActiviti("0");
			menu2.setPermission(genScheme.getModuleName()+":"+StringUtils.uncapitalize(genTable.getClassName())+":edit");
			systemService.saveMenu(menu2);		
			
			genScheme.setMenuFlag("1");
		}
		return result.toString();
	}	
	/**
	 * 将内容写入文件
	 * @param content
	 * @param filePath
	 */
	private void writeFile(String content, String filePath) {
		try {
			if (FileUtils.createFile(filePath)){
				FileOutputStream fos = new FileOutputStream(filePath);
				Writer writer = new OutputStreamWriter(fos,"UTF-8");
				BufferedWriter bufferedWriter = new BufferedWriter(writer);
				bufferedWriter.write(content);
				bufferedWriter.close();
				writer.close();
			}else{
				logger.info("生成失败，文件已存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

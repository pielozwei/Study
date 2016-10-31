/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.FreeMarkers;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 代码生成器
 * @author ThinkGem
 * @version 2013-06-21
 */
public class Generate {
	
	private static Logger logger = LoggerFactory.getLogger(Generate.class);

	public static void main(String[] args) throws Exception {
		String packageName = "com.thinkgem.jeesite.modules";
		String moduleName = "ip";			// 模块名，例：sys
		String subModuleName = "";				// 子模块名（可选） 
		String className = "workCenter";			// 类名，例：user
		String className3 = "referenceMessage";			// 主表关联的子表
		String classAuthor = "LiHR";		// 类作者，例：ThinkGem
		String functionName = "参考消息";			// 功能名，例：用户
		String tableName="IP_CKXX";     //实体映射的数据库表名--一般不区分大小写
		String FKField="JSGZZX_ID";
		//commonGenerate();//在打开注释前，请修改该方法里的相关参数在执行-----通用简单生成代码
		createSubTable(packageName, moduleName, subModuleName, className, className3, classAuthor, functionName, tableName, FKField);//在打开注释前，请修改该方法里的相关参数在执行-----针对有主表还有主表的树 代码已完成可==生成代码
		//createTable_tree();//生成树
	}
	
	public static void commonGenerate() throws Exception{

		// ========== ↓↓↓↓↓↓ 执行前请修改参数，谨慎执行。↓↓↓↓↓↓ ====================

		// 主要提供基本功能模块代码生成。
		// 目录生成结构：{packageName}/{moduleName}/{dao,entity,service,web}/{subModuleName}/{className}
		
		// packageName 包名，这里如果更改包名，请在applicationContext.xml和srping-mvc.xml中配置base-package、packagesToScan属性，来指定多个（共4处需要修改）。
		String packageName = "com.thinkgem.jeesite.modules";
		
		String moduleName = "shebei";			// 模块名，例：sys
		String subModuleName = "tool";				// 子模块名（可选） 
		String className = "toolCategory";			// 类名，例：user
		String classAuthor = "LiHR";		// 类作者，例：ThinkGem
		String functionName = "工器具分类";			// 功能名，例：用户
		String tableName="IP_GZQJLX";

		// 是否启用生成工具
		//Boolean isEnable = false;
		Boolean isEnable = true;
		
		// ========== ↑↑↑↑↑↑ 执行前请修改参数，谨慎执行。↑↑↑↑↑↑ ====================
		
		if (!isEnable){
			logger.error("请启用代码生成工具，设置参数：isEnable = true");
			return;
		}
		
		if (StringUtils.isBlank(moduleName) || StringUtils.isBlank(moduleName) 
				|| StringUtils.isBlank(className) || StringUtils.isBlank(functionName)){
			logger.error("参数设置错误：包名、模块名、类名、功能名不能为空。");
			return;
		}
		
		// 获取文件分隔符
		String separator = File.separator;
		
		// 获取工程路径
		File projectPath = new DefaultResourceLoader().getResource("").getFile();
		while(!new File(projectPath.getPath()+separator+"src"+separator+"main").exists()){
			projectPath = projectPath.getParentFile();
		}
		logger.info("Project Path: {}", projectPath);
		
		// 模板文件路径
		String tplPath = StringUtils.replace(projectPath+"/src/main/java/com/thinkgem/jeesite/generate/template", "/", separator);
		logger.info("Template Path: {}", tplPath);
		
		// Java文件路径
		String javaPath = StringUtils.replaceEach(projectPath+"/src/main/java/"+StringUtils.lowerCase(packageName), 
				new String[]{"/", "."}, new String[]{separator, separator});
		logger.info("Java Path: {}", javaPath);
		
		// 视图文件路径
		String viewPath = StringUtils.replace(projectPath+"/src/main/webapp/WEB-INF/views", "/", separator);
		logger.info("View Path: {}", viewPath);
		
		// 代码模板配置
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setDirectoryForTemplateLoading(new File(tplPath));

		// 定义模板变量
		Map<String, String> model = Maps.newHashMap();
		model.put("packageName", StringUtils.lowerCase(packageName));
		model.put("moduleName", StringUtils.lowerCase(moduleName));
		model.put("subModuleName", StringUtils.isNotBlank(subModuleName)?"."+StringUtils.lowerCase(subModuleName):"");
		model.put("className", StringUtils.uncapitalize(className));
		model.put("ClassName", StringUtils.capitalize(className));
		model.put("classAuthor", StringUtils.isNotBlank(classAuthor)?classAuthor:"Generate Tools");
		model.put("classVersion", DateUtils.getDate());
		model.put("functionName", functionName);
		/*model.put("tableName", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
				?"_"+StringUtils.lowerCase(subModuleName):"")+"_"+model.get("className"));*/
		model.put("tableName",tableName);
		model.put("urlPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
				?"/"+StringUtils.lowerCase(subModuleName):"")+"/"+model.get("className"));
		model.put("viewPrefix", //StringUtils.substringAfterLast(model.get("packageName"),".")+"/"+
				model.get("urlPrefix"));
		model.put("permissionPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
				?":"+StringUtils.lowerCase(subModuleName):"")+":"+model.get("className"));

		// 生成 Entity
		Template template = cfg.getTemplate("entity.ftl");
		String content = FreeMarkers.renderTemplate(template, model);
		String filePath = javaPath+separator+model.get("moduleName")+separator+"entity"
				+separator+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName")+".java";
		writeFile(content, filePath);
		logger.info("Entity: {}", filePath);
		
		// 生成 Dao
		template = cfg.getTemplate("dao.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"dao"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName")+"Dao.java";
		writeFile(content, filePath);
		logger.info("Dao: {}", filePath);
		
		// 生成 Service
		template = cfg.getTemplate("service.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"service"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName")+"Service.java";
		writeFile(content, filePath);
		logger.info("Service: {}", filePath);
		
		// 生成 Controller
		template = cfg.getTemplate("controller.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"web"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName")+"Controller.java";
		writeFile(content, filePath);
		logger.info("Controller: {}", filePath);
		
		// 生成 ViewForm
		template = cfg.getTemplate("viewForm.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName"),".")
				+separator+model.get("moduleName")+separator+StringUtils.lowerCase(subModuleName)
				+separator+model.get("className")+"Form.jsp";
		writeFile(content, filePath);
		logger.info("ViewForm: {}", filePath);
		
		// 生成 ViewList
		template = cfg.getTemplate("viewList.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName"),".")
				+separator+model.get("moduleName")+separator+StringUtils.lowerCase(subModuleName)
				+separator+model.get("className")+"List.jsp";
		writeFile(content, filePath);
		logger.info("ViewList: {}", filePath);
		
		logger.info("Generate Success.");
	}
	
	/*挂在基本信息里的子表*/
	public static void createSubTable(String packageName, String moduleName, String subModuleName, String className, String className3, String classAuthor, String functionName, String tableName, String FKField) throws Exception{
		// ========== ↓↓↓↓↓↓ 执行前请修改参数，谨慎执行。↓↓↓↓↓↓ ====================
		// 目录生成结构：{packageName}/{moduleName}/{dao,entity,service,web}/{subModuleName}/{className}
		// packageName 包名，这里如果更改包名，请在applicationContext.xml和srping-mvc.xml中配置base-package、packagesToScan属性，来指定多个（共4处需要修改）。
		//String packageName = "com.thinkgem.jeesite.modules";
		//String moduleName = "shebei";			// 模块名，例：sys
		//String subModuleName = "sbggxhgl";				// 子模块名（可选） 
		//String className = "sbggxh";			// 类名，例：user
		//String className3 = "equipmentBOMSheet";			// 主表关联的子表
		//String classAuthor = "LiHR";		// 类作者，例：ThinkGem
		//String functionName = "设备BOM单";			// 功能名，例：用户
		//String tableName="IP_SBBOMD";     //实体映射的数据库表名--一般不区分大小写
		//String FKField="IP_SBBOMD";
		// 是否启用生成工具
		Boolean isEnable = true;
		
		// ========== ↑↑↑↑↑↑ 执行前请修改参数，谨慎执行。↑↑↑↑↑↑ ====================
		
		if (!isEnable){
			logger.error("请启用代码生成工具，设置参数：isEnable = true");
			return;
		}
		
		if (StringUtils.isBlank(moduleName) || StringUtils.isBlank(moduleName) 
				/*|| StringUtils.isBlank(className2)*/|| StringUtils.isBlank(className) || StringUtils.isBlank(functionName)){
			logger.error("参数设置错误：包名、模块名、类名、功能名不能为空。");
			return;
		}
		// 获取文件分隔符
		String separator = File.separator;
		
		// 获取工程路径
		File projectPath = new DefaultResourceLoader().getResource("").getFile();
		while(!new File(projectPath.getPath()+separator+"src"+separator+"main").exists()){
			projectPath = projectPath.getParentFile();
		}
		logger.info("Project Path: {}", projectPath);
		
		// 模板文件路径
		String tplPath = StringUtils.replace(projectPath+"/src/main/java/com/thinkgem/jeesite/generate/template", "/", separator);
		logger.info("Template Path: {}", tplPath);
		
		// Java文件路径
		String javaPath = StringUtils.replaceEach(projectPath+"/src/main/java/"+StringUtils.lowerCase(packageName), 
				new String[]{"/", "."}, new String[]{separator, separator});
		logger.info("Java Path: {}", javaPath);
		
		// 视图文件路径
		String viewPath = StringUtils.replace(projectPath+"/src/main/webapp/WEB-INF/views", "/", separator);
		logger.info("View Path: {}", viewPath);
		
		// 代码模板配置
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setDirectoryForTemplateLoading(new File(tplPath));

		// 定义模板变量
		Map<String, String> model = Maps.newHashMap();
		model.put("packageName", StringUtils.lowerCase(packageName));
		model.put("moduleName", StringUtils.lowerCase(moduleName));
		model.put("ModuleName", StringUtils.capitalize(moduleName));
		model.put("subModuleName_", StringUtils.isNotBlank(subModuleName)?"."+StringUtils.lowerCase(subModuleName):"");
		model.put("subModuleName", StringUtils.lowerCase(subModuleName));
		model.put("SubModuleName", StringUtils.capitalize(subModuleName));
		model.put("className", StringUtils.uncapitalize(className));
		model.put("ClassName", StringUtils.capitalize(className));
		model.put("className3", StringUtils.uncapitalize(className3));
		model.put("ClassName3", StringUtils.capitalize(className3));
		model.put("classAuthor", StringUtils.isNotBlank(classAuthor)?classAuthor:"Generate Tools");
		model.put("classVersion", DateUtils.getDate());
		model.put("functionName", functionName);
		model.put("tableName",tableName);
		model.put("FKField",FKField);
		model.put("urlPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
				?"/"+StringUtils.lowerCase(subModuleName):"")+"/"+model.get("className3"));
		model.put("viewPrefix",model.get("urlPrefix"));
		model.put("permissionPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
				?":"+StringUtils.lowerCase(subModuleName):"")+":"+model.get("className3"));
		// 生成 Entity
		Template template = cfg.getTemplate("entity3.ftl");
		String content = FreeMarkers.renderTemplate(template, model);
		String filePath = javaPath+separator+model.get("moduleName")+separator+"entity"
				+separator+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName3")+".java";
		writeFile(content, filePath);
		logger.info("Entity: {}", filePath);
		
		// 生成 Dao
		template = cfg.getTemplate("dao3.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"dao"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName3")+"Dao.java";
		writeFile(content, filePath);
		logger.info("Dao: {}", filePath);
		
		// 生成 Service
		template = cfg.getTemplate("service3.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"service"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName3")+"Service.java";
		writeFile(content, filePath);
		logger.info("Service: {}", filePath);
		
		// 生成 Controller
		template = cfg.getTemplate("controller3.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"web"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName3")+"Controller.java";
		writeFile(content, filePath);
		logger.info("Controller: {}", filePath);
		
		// 生成 ViewForm
		template = cfg.getTemplate("viewForm3.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName"),".")
				+separator+model.get("moduleName")+separator+StringUtils.lowerCase(subModuleName)
				+separator+model.get("className3")+"Form.jsp";
		writeFile(content, filePath);
		logger.info("ViewForm: {}", filePath);
		
		// 生成 ViewList
		template = cfg.getTemplate("viewList3.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName"),".")
				+separator+model.get("moduleName")+separator+StringUtils.lowerCase(subModuleName)
				+separator+model.get("className3")+"List.jsp";
		writeFile(content, filePath);
		logger.info("ViewList: {}", filePath);
		
		logger.info("Generate Success.");
	
	}
	
	/*挂在树上的基本信息表*/
	public static void createTable(String packageName, String moduleName, String subModuleName, String className, String className3, String classAuthor, String functionName, String tableName, String FKField) throws Exception{
		// ========== ↓↓↓↓↓↓ 执行前请修改参数，谨慎执行。↓↓↓↓↓↓ ====================
		// 目录生成结构：{packageName}/{moduleName}/{dao,entity,service,web}/{subModuleName}/{className}
		// packageName 包名，这里如果更改包名，请在applicationContext.xml和srping-mvc.xml中配置base-package、packagesToScan属性，来指定多个（共4处需要修改）。
		//String packageName = "com.thinkgem.jeesite.modules";
		//String moduleName = "shebei";			// 模块名，例：sys
		//String subModuleName = "sbggxhgl";		// 子模块名（可选） 
		//String className = "sbggxh";			//树表类名 
		//String className3 = "equipmentBOMSheet";// 主表类名
		//String classAuthor = "LiHR";			// 类作者，例：ThinkGem
		//String functionName = "设备BOM单";			// 功能名，例：用户
		//String tableName="IP_SBBOMD";     		//实体映射的数据库表名--一般不区分大小写
		//String FKField="IP_SBBOMD";     			//外键--一般不区分大小写（这里只生成一个外键）
		// 是否启用生成工具
		Boolean isEnable = true;
		
		// ========== ↑↑↑↑↑↑ 执行前请修改参数，谨慎执行。↑↑↑↑↑↑ ====================
		
		if (!isEnable){
			logger.error("请启用代码生成工具，设置参数：isEnable = true");
			return;
		}
		
		if (StringUtils.isBlank(moduleName) || StringUtils.isBlank(moduleName) 
				|| StringUtils.isBlank(className3) || StringUtils.isBlank(className) || StringUtils.isBlank(functionName)){
			logger.error("参数设置错误：包名、模块名、类名、功能名不能为空。");
			return;
		}
		// 获取文件分隔符
		String separator = File.separator;
		
		// 获取工程路径
		File projectPath = new DefaultResourceLoader().getResource("").getFile();
		while(!new File(projectPath.getPath()+separator+"src"+separator+"main").exists()){
			projectPath = projectPath.getParentFile();
		}
		logger.info("Project Path: {}", projectPath);
		
		// 模板文件路径
		String tplPath = StringUtils.replace(projectPath+"/src/main/java/com/thinkgem/jeesite/generate/template", "/", separator);
		logger.info("Template Path: {}", tplPath);
		
		// Java文件路径
		String javaPath = StringUtils.replaceEach(projectPath+"/src/main/java/"+StringUtils.lowerCase(packageName), 
				new String[]{"/", "."}, new String[]{separator, separator});
		logger.info("Java Path: {}", javaPath);
		
		// 视图文件路径
		String viewPath = StringUtils.replace(projectPath+"/src/main/webapp/WEB-INF/views", "/", separator);
		logger.info("View Path: {}", viewPath);
		
		// 代码模板配置
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setDirectoryForTemplateLoading(new File(tplPath));

		// 定义模板变量
		Map<String, String> model = Maps.newHashMap();
		model.put("packageName", StringUtils.lowerCase(packageName));
		model.put("moduleName", StringUtils.lowerCase(moduleName));
		model.put("ModuleName", StringUtils.capitalize(moduleName));
		model.put("subModuleName_", StringUtils.isNotBlank(subModuleName)?"."+StringUtils.lowerCase(subModuleName):"");
		model.put("subModuleName", StringUtils.lowerCase(subModuleName));
		model.put("SubModuleName", StringUtils.capitalize(subModuleName));
		model.put("className", StringUtils.uncapitalize(className));
		model.put("ClassName", StringUtils.capitalize(className));
		model.put("className3", StringUtils.uncapitalize(className3));
		model.put("ClassName3", StringUtils.capitalize(className3));
		model.put("classAuthor", StringUtils.isNotBlank(classAuthor)?classAuthor:"Generate Tools");
		model.put("classVersion", DateUtils.getDate());
		model.put("functionName", functionName);
		model.put("tableName",tableName);
		model.put("FKField",FKField);
		model.put("urlPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
				?"/"+StringUtils.lowerCase(subModuleName):"")+"/"+model.get("className3"));
		model.put("viewPrefix",model.get("urlPrefix"));
		model.put("permissionPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
				?":"+StringUtils.lowerCase(subModuleName):"")+":"+model.get("className3"));
		// 生成 Entity
		Template template = cfg.getTemplate("entity1.ftl");
		String content = FreeMarkers.renderTemplate(template, model);
		String filePath = javaPath+separator+model.get("moduleName")+separator+"entity"
				+separator+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName3")+".java";
		writeFile(content, filePath);
		logger.info("Entity: {}", filePath);
		
		// 生成 Dao
		template = cfg.getTemplate("dao1.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"dao"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName3")+"Dao.java";
		writeFile(content, filePath);
		logger.info("Dao: {}", filePath);
		
		// 生成 Service
		template = cfg.getTemplate("service1.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"service"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName3")+"Service.java";
		writeFile(content, filePath);
		logger.info("Service: {}", filePath);
		
		// 生成 Controller
		template = cfg.getTemplate("controller1.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"web"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName3")+"Controller.java";
		writeFile(content, filePath);
		logger.info("Controller: {}", filePath);
		
		// 生成 ViewForm
		template = cfg.getTemplate("viewForm1.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName"),".")
				+separator+model.get("moduleName")+separator+StringUtils.lowerCase(subModuleName)
				+separator+model.get("className3")+"Form.jsp";
		writeFile(content, filePath);
		logger.info("ViewForm: {}", filePath);
		
		// 生成 ViewList
		template = cfg.getTemplate("viewList1.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName"),".")
				+separator+model.get("moduleName")+separator+StringUtils.lowerCase(subModuleName)
				+separator+model.get("className3")+"List.jsp";
		writeFile(content, filePath);
		logger.info("ViewList: {}", filePath);
		
		logger.info("Generate Success.");
	
	}
	
	/*基本信息要挂的树*/
	public static void createTable_tree(String packageName, String moduleName, String subModuleName, String className, String className2, String classAuthor, String functionName, String tableName, String upFiled) throws Exception{
		// ========== ↓↓↓↓↓↓ 执行前请修改参数，谨慎执行。↓↓↓↓↓↓ ====================
		// 主要提供基本功能模块代码生成。
		// 目录生成结构：{packageName}/{moduleName}/{dao,entity,service,web}/{subModuleName}/{className}
		// packageName 包名，这里如果更改包名，请在applicationContext.xml和srping-mvc.xml中配置base-package、packagesToScan属性，来指定多个（共4处需要修改）。
		//String packageName = "com.thinkgem.jeesite.modules";
		//String moduleName = "shebei";			// 模块名，例：sys
		//String subModuleName = "sbggxhgl";				// 子模块名（可选） 
		//String className = "equipmentBOMSheet";			// 树类
		//String className2 = "equipmentBOMSheet";		// 树上挂的信息类
		//String classAuthor = "LiHR";		// 类作者，例：ThinkGem
		//String functionName = "设备BOM单";			// 功能名，例：用户
		//String tableName = "IP_SBBOMD";     //实体映射的数据库表名--一般不区分大小写
		//String upFiled = "";
		// 是否启用生成工具
		Boolean isEnable = true;
		
		// ========== ↑↑↑↑↑↑ 执行前请修改参数，谨慎执行。↑↑↑↑↑↑ ====================
		
		if (!isEnable){
			logger.error("请启用代码生成工具，设置参数：isEnable = true");
			return;
		}
		
		if (StringUtils.isBlank(moduleName) || StringUtils.isBlank(moduleName) || StringUtils.isBlank(functionName)
				|| StringUtils.isBlank(className2) || StringUtils.isBlank(className)){
			logger.error("参数设置错误：包名、模块名、类名、功能名不能为空。");
			return;
		}
		// 获取文件分隔符
		String separator = File.separator;
		
		// 获取工程路径
		File projectPath = new DefaultResourceLoader().getResource("").getFile();
		while(!new File(projectPath.getPath()+separator+"src"+separator+"main").exists()){
			projectPath = projectPath.getParentFile();
		}
		logger.info("Project Path: {}", projectPath);
		
		// 模板文件路径
		String tplPath = StringUtils.replace(projectPath+"/src/main/java/com/thinkgem/jeesite/generate/template", "/", separator);
		logger.info("Template Path: {}", tplPath);
		
		// Java文件路径
		String javaPath = StringUtils.replaceEach(projectPath+"/src/main/java/"+StringUtils.lowerCase(packageName), 
				new String[]{"/", "."}, new String[]{separator, separator});
		logger.info("Java Path: {}", javaPath);
		
		// 视图文件路径
		String viewPath = StringUtils.replace(projectPath+"/src/main/webapp/WEB-INF/views", "/", separator);
		logger.info("View Path: {}", viewPath);
		
		// 代码模板配置
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setDirectoryForTemplateLoading(new File(tplPath));

		// 定义模板变量
		Map<String, String> model = Maps.newHashMap();
		model.put("packageName", StringUtils.lowerCase(packageName));
		model.put("moduleName", StringUtils.lowerCase(moduleName));
		model.put("ModuleName", StringUtils.capitalize(moduleName));
		model.put("subModuleName_", StringUtils.isNotBlank(subModuleName)?"."+StringUtils.lowerCase(subModuleName):"");
		model.put("subModuleName", StringUtils.lowerCase(subModuleName));
		model.put("SubModuleName", StringUtils.capitalize(subModuleName));
		model.put("className", StringUtils.uncapitalize(className));
		model.put("ClassName", StringUtils.capitalize(className));
		model.put("className2", StringUtils.uncapitalize(className2));
		model.put("ClassName2", StringUtils.capitalize(className2));
		model.put("classAuthor", StringUtils.isNotBlank(classAuthor)?classAuthor:"Generate Tools");
		model.put("classVersion", DateUtils.getDate());
		model.put("functionName", functionName);
		model.put("tableName",tableName);
		model.put("upFiled",upFiled);
		model.put("urlPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
				?"/"+StringUtils.lowerCase(subModuleName):"")+"/"+model.get("className"));
		model.put("viewPrefix",model.get("urlPrefix"));
		model.put("permissionPrefix", model.get("moduleName")+(StringUtils.isNotBlank(subModuleName)
				?":"+StringUtils.lowerCase(subModuleName):"")+":"+model.get("className"));
		// 生成 Entity
		Template template = cfg.getTemplate("entity2.ftl");
		String content = FreeMarkers.renderTemplate(template, model);
		String filePath = javaPath+separator+model.get("moduleName")+separator+"entity"
				+separator+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName")+".java";
		writeFile(content, filePath);
		logger.info("Entity: {}", filePath);
		
		// 生成 Dao
		template = cfg.getTemplate("dao2.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"dao"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName")+"Dao.java";
		writeFile(content, filePath);
		logger.info("Dao: {}", filePath);
		
		// 生成 Service
		template = cfg.getTemplate("service2.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"service"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName")+"Service.java";
		writeFile(content, filePath);
		logger.info("Service: {}", filePath);
		
		// 生成 Controller
		template = cfg.getTemplate("controller2.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = javaPath+separator+model.get("moduleName")+separator+"web"+separator
				+StringUtils.lowerCase(subModuleName)+separator+model.get("ClassName")+"Controller.java";
		writeFile(content, filePath);
		logger.info("Controller: {}", filePath);
		
		// 生成 ViewForm
		template = cfg.getTemplate("viewForm2.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName"),".")
				+separator+model.get("moduleName")+separator+StringUtils.lowerCase(subModuleName)
				+separator+model.get("className")+"Form.jsp";
		writeFile(content, filePath);
		logger.info("ViewForm: {}", filePath);
		
		// 生成 ViewList
		template = cfg.getTemplate("viewList2.ftl");
		content = FreeMarkers.renderTemplate(template, model);
		filePath = viewPath+separator+StringUtils.substringAfterLast(model.get("packageName"),".")
				+separator+model.get("moduleName")+separator+StringUtils.lowerCase(subModuleName)
				+separator+model.get("className")+"List.jsp";
		writeFile(content, filePath);
		logger.info("ViewList: {}", filePath);
		
		logger.info("Generate Success.");
	
	}

	/**
	 * 将内容写入文件
	 * @param content
	 * @param filePath
	 */
	public static void writeFile(String content, String filePath) {
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

/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.ip.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.ip.entity.Jldw;
import com.thinkgem.jeesite.modules.ip.service.JldwService;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class JldwDictUtils {
	private static JldwService jldwService = SpringContextHolder.getBean(JldwService.class);
	public static final String CACHE_DICT_MAP = "dictMap";

	/**
	 * 通过计量单位ID  找到计量单位名称
	 * @Title: getJldwDictLabel 
	 * @Description: TODO
	 * @param id
	 * @param defaultValue
	 * @return
	 * @return: String
	 */
	public static String getJldwDictLabel(String id, String defaultValue) {
		if (StringUtils.isNotBlank(id)) {
			for (Jldw jldw : getJldwDictList()) {
				if (id.equals(jldw.getId())) {
					return jldw.getJldwmc();
				}
			}
		}
		return defaultValue;
	}

	/**
	 * 通过计量单位名称  找到ID值
	 * @Title: getJldwDictValue 
	 * @Description: TODO
	 * @param jldwmc
	 * @param defaultLabel
	 * @return
	 * @return: String
	 */
	public static String getJldwDictValue(String jldwmc, String defaultLabel) {
		if (StringUtils.isNotBlank(jldwmc)) {
			for (Jldw jldw : getJldwDictList()) {
				if (jldwmc.equals(jldw.getJldwmc())) {
					return jldw.getId();
				}
			}
		}
		return defaultLabel;
	}

	/**
	 * 获取计量单位数据字典值
	 * @Title: getJldwDictList 
	 * @Description: TODO
	 * @return
	 * @return: List<Jldw>
	 */
	public static List<Jldw> getJldwDictList() {
		List<Jldw> dictList = jldwService.findAll();
		return dictList;
	}
}

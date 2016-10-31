package com.thinkgem.jeesite.common.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 导出页面的table Controller
 * 
 * @author LiHR
 * @version 2016-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/common/export")
public class ExportTableController extends BaseController {
	final static String exportDir = "c:/hxmes";

	public static void deleteFile(String url) {
		File file = new File(url);
		if (file.exists()) {
			file.delete();
		}
	}

	public static void createExportDir() {
		File file = new File(exportDir);
		if (!file.exists())
			file.mkdir();
	}

	@RequestMapping(value = { "export", "" })
	public String export(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) throws IOException {
		String currentPageUrl = request.getParameter("currentPageUrl");   // 当前页URL
		if(StringUtils.isBlank(currentPageUrl))	currentPageUrl=null;	 // 处理失败就返回该页
		String returnPageUrl = request.getParameter("returnPageUrl");   // 要跳转的页面
		if(StringUtils.isBlank(returnPageUrl))	returnPageUrl=null;   // 处理成功要跳转的页面
		String tableText = "<table border='1' cellpadding='2' cellspacing='0'>"
				+ request.getParameter("tableText") + "</table>";

		// 把table 写进文本文件
		String url = exportDir+"/temp" + DateUtils.getDate("yyyyMMddHHmmss")+ ".xls";
		createExportDir();
		
		File txt = new File(url);
		if (!txt.exists()) {
			txt.createNewFile();
		}
		FileWriter fw = new FileWriter(txt.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(tableText);
		bw.close();
		fw.close();

		// 把table 写进文本文件了，开始下载
		try {
			// 文件后缀
			int i = url.lastIndexOf("/");
			String str = url.substring(i + 1, url.length());
			String houzhui = str.substring(str.indexOf("."), str.length());
			// 解决中文乱码问题
			response.setCharacterEncoding("UTF-8"); // 必须写，否则会有乱码
			String filename = request.getParameter("filename");
			String agent = request.getHeader("User-Agent").toLowerCase();
			if (agent.indexOf("firefox") > 0) {// 谷歌一直不乱骂，火狐不乱码，其他都i乱
				filename = new String(filename.getBytes("utf-8"), "iso-8859-1");
			} else {// 谷歌一直不乱骂，火狐乱码，其他都不乱
				filename = URLEncoder.encode(filename, "utf-8");
			}

			// 设置response的编码方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename + DateUtils.getDate("yyyyMMddHHmmss") + houzhui);
			// 读取目标文件，通过response将目标文件写到客户端
			InputStream in = new FileInputStream(url);
			OutputStream out = response.getOutputStream();
			// 写文件
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
			out.close();
			deleteFile(url);// 删除导出产生的临时文件
			return returnPageUrl;
		} catch (Exception e) {
			deleteFile(url);//删除导出产生的临时文件
			addMessage(redirectAttributes, "下载失败！失败信息：" + e.getMessage());
			e.printStackTrace();
		}

		return currentPageUrl;
	}
}

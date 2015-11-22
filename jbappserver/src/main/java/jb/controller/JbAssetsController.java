package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.JbAssets;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.JbAssetsServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * JbAssets管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/jbAssetsController")
public class JbAssetsController extends BaseController {

	@Autowired
	private JbAssetsServiceI jbAssetsService;


	/**
	 * 跳转到JbAssets管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/jbassets/jbAssets";
	}

	/**
	 * 获取JbAssets数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(JbAssets jbAssets, PageHelper ph) {
		return jbAssetsService.dataGrid(jbAssets, ph);
	}
	/**
	 * 获取JbAssets数据表格excel
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(JbAssets jbAssets, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(jbAssets,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加JbAssets页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		JbAssets jbAssets = new JbAssets();
		jbAssets.setId(UUID.randomUUID().toString());
		return "/jbassets/jbAssetsAdd";
	}

	/**
	 * 添加JbAssets
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(JbAssets jbAssets) {
		Json j = new Json();		
		jbAssetsService.add(jbAssets);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到JbAssets查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		JbAssets jbAssets = jbAssetsService.get(id);
		request.setAttribute("jbAssets", jbAssets);
		return "/jbassets/jbAssetsView";
	}

	/**
	 * 跳转到JbAssets修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		JbAssets jbAssets = jbAssetsService.get(id);
		request.setAttribute("jbAssets", jbAssets);
		return "/jbassets/jbAssetsEdit";
	}

	/**
	 * 修改JbAssets
	 * 
	 * @param jbAssets
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(JbAssets jbAssets) {
		Json j = new Json();		
		jbAssetsService.edit(jbAssets);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除JbAssets
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		jbAssetsService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}

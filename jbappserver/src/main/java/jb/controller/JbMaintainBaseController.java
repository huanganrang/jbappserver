package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.JbMaintainBase;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.JbMaintainBaseServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * JbMaintainBase管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/jbMaintainBaseController")
public class JbMaintainBaseController extends BaseController {

	@Autowired
	private JbMaintainBaseServiceI jbMaintainBaseService;


	/**
	 * 跳转到JbMaintainBase管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/jbmaintainbase/jbMaintainBase";
	}

	/**
	 * 获取JbMaintainBase数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(JbMaintainBase jbMaintainBase, PageHelper ph) {
		return jbMaintainBaseService.dataGrid(jbMaintainBase, ph);
	}
	/**
	 * 获取JbMaintainBase数据表格excel
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
	public void download(JbMaintainBase jbMaintainBase, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(jbMaintainBase,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加JbMaintainBase页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		JbMaintainBase jbMaintainBase = new JbMaintainBase();
		jbMaintainBase.setId(UUID.randomUUID().toString());
		return "/jbmaintainbase/jbMaintainBaseAdd";
	}

	/**
	 * 添加JbMaintainBase
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(JbMaintainBase jbMaintainBase) {
		Json j = new Json();		
		jbMaintainBaseService.add(jbMaintainBase);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到JbMaintainBase查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		JbMaintainBase jbMaintainBase = jbMaintainBaseService.get(id);
		request.setAttribute("jbMaintainBase", jbMaintainBase);
		return "/jbmaintainbase/jbMaintainBaseView";
	}

	/**
	 * 跳转到JbMaintainBase修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		JbMaintainBase jbMaintainBase = jbMaintainBaseService.get(id);
		request.setAttribute("jbMaintainBase", jbMaintainBase);
		return "/jbmaintainbase/jbMaintainBaseEdit";
	}

	/**
	 * 修改JbMaintainBase
	 * 
	 * @param jbMaintainBase
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(JbMaintainBase jbMaintainBase) {
		Json j = new Json();		
		jbMaintainBaseService.edit(jbMaintainBase);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除JbMaintainBase
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		jbMaintainBaseService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}

package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.JbRegularCheck;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.JbRegularCheckServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * JbRegularCheck管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/jbRegularCheckController")
public class JbRegularCheckController extends BaseController {

	@Autowired
	private JbRegularCheckServiceI jbRegularCheckService;


	/**
	 * 跳转到JbRegularCheck管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/jbregularcheck/jbRegularCheck";
	}

	/**
	 * 获取JbRegularCheck数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(JbRegularCheck jbRegularCheck, PageHelper ph) {
		return jbRegularCheckService.dataGrid(jbRegularCheck, ph);
	}
	/**
	 * 获取JbRegularCheck数据表格excel
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
	public void download(JbRegularCheck jbRegularCheck, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(jbRegularCheck,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加JbRegularCheck页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		JbRegularCheck jbRegularCheck = new JbRegularCheck();
		jbRegularCheck.setId(UUID.randomUUID().toString());
		return "/jbregularcheck/jbRegularCheckAdd";
	}

	/**
	 * 添加JbRegularCheck
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(JbRegularCheck jbRegularCheck) {
		Json j = new Json();		
		jbRegularCheckService.add(jbRegularCheck);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到JbRegularCheck查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		JbRegularCheck jbRegularCheck = jbRegularCheckService.get(id);
		request.setAttribute("jbRegularCheck", jbRegularCheck);
		return "/jbregularcheck/jbRegularCheckView";
	}

	/**
	 * 跳转到JbRegularCheck修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		JbRegularCheck jbRegularCheck = jbRegularCheckService.get(id);
		request.setAttribute("jbRegularCheck", jbRegularCheck);
		return "/jbregularcheck/jbRegularCheckEdit";
	}

	/**
	 * 修改JbRegularCheck
	 * 
	 * @param jbRegularCheck
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(JbRegularCheck jbRegularCheck) {
		Json j = new Json();		
		jbRegularCheckService.edit(jbRegularCheck);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除JbRegularCheck
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		jbRegularCheckService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}

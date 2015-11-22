package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.JbRepairCheck;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.JbRepairCheckServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * JbRepairCheck管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/jbRepairCheckController")
public class JbRepairCheckController extends BaseController {

	@Autowired
	private JbRepairCheckServiceI jbRepairCheckService;


	/**
	 * 跳转到JbRepairCheck管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/jbrepaircheck/jbRepairCheck";
	}

	/**
	 * 获取JbRepairCheck数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(JbRepairCheck jbRepairCheck, PageHelper ph) {
		return jbRepairCheckService.dataGrid(jbRepairCheck, ph);
	}
	/**
	 * 获取JbRepairCheck数据表格excel
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
	public void download(JbRepairCheck jbRepairCheck, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(jbRepairCheck,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加JbRepairCheck页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		JbRepairCheck jbRepairCheck = new JbRepairCheck();
		jbRepairCheck.setId(UUID.randomUUID().toString());
		return "/jbrepaircheck/jbRepairCheckAdd";
	}

	/**
	 * 添加JbRepairCheck
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(JbRepairCheck jbRepairCheck) {
		Json j = new Json();		
		jbRepairCheckService.add(jbRepairCheck);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到JbRepairCheck查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		JbRepairCheck jbRepairCheck = jbRepairCheckService.get(id);
		request.setAttribute("jbRepairCheck", jbRepairCheck);
		return "/jbrepaircheck/jbRepairCheckView";
	}

	/**
	 * 跳转到JbRepairCheck修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		JbRepairCheck jbRepairCheck = jbRepairCheckService.get(id);
		request.setAttribute("jbRepairCheck", jbRepairCheck);
		return "/jbrepaircheck/jbRepairCheckEdit";
	}

	/**
	 * 修改JbRepairCheck
	 * 
	 * @param jbRepairCheck
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(JbRepairCheck jbRepairCheck) {
		Json j = new Json();		
		jbRepairCheckService.edit(jbRepairCheck);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除JbRepairCheck
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		jbRepairCheckService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}

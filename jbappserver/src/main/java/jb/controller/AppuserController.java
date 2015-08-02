package jb.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import jb.pageModel.Appuser;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.AppuserServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Appuser管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/appuserController")
public class AppuserController extends BaseController {

	@Autowired
	private AppuserServiceI appuserService;


	/**
	 * 跳转到Appuser管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/appuser/appuser";
	}

	/**
	 * 获取Appuser数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Appuser appuser, PageHelper ph) {
		return appuserService.dataGrid(appuser, ph);
	}

	/**
	 * 跳转到添加Appuser页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		Appuser appuser = new Appuser();
		appuser.setId(UUID.randomUUID().toString());
		return "/appuser/appuserAdd";
	}

	/**
	 * 添加Appuser
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Appuser appuser) {
		Json j = new Json();		
		appuserService.add(appuser);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到Appuser查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		Appuser appuser = appuserService.get(id);
		request.setAttribute("appuser", appuser);
		return "/appuser/appuserView";
	}

	/**
	 * 跳转到Appuser修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		Appuser appuser = appuserService.get(id);
		request.setAttribute("appuser", appuser);
		return "/appuser/appuserEdit";
	}

	/**
	 * 修改Appuser
	 * 
	 * @param appuser
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Appuser appuser) {
		Json j = new Json();		
		appuserService.edit(appuser);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除Appuser
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		appuserService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}

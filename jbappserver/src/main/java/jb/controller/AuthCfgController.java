package jb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.Tree;
import jb.service.AppServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 权限管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/authCfgController")
public class AuthCfgController extends BaseController {

	@Autowired
	private AppServiceI appService;

	/**
	 * 跳转到权限管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {		
		return "/auth/authCfg";
	}

		
	/**
	 * 获取用户组数据
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGridGroup")
	@ResponseBody
	public DataGrid dataGridGroup(PageHelper ph) {		
		DataGrid dg = appService.dataGrid(null, ph);
		return dg;
	}
	
	/**
	 * 获得资源树(包括所有资源类型)
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree(HttpServletRequest request) {
		String nGroupNo = request.getParameter("nGroupNo");
		
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		return appService.allTree(nGroupNo);
	}
	
	/**
	 * 授权
	 * 
	 * @return
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public Json grant(HttpServletRequest request) {
		String resourceIds = request.getParameter("resourceIds");
		String nGroupNo = request.getParameter("nGroupNo");
		String uidIds = request.getParameter("uidIds");		
		Json j = new Json();
		appService.grant(nGroupNo, resourceIds.split("[,]"), uidIds.split("[,]"));
		j.setSuccess(true);
		j.setMsg("授权成功！");		
		return j;
	}	
}

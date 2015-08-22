package jb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.BaseData;
import jb.pageModel.Json;
import jb.pageModel.Resource;
import jb.pageModel.SessionInfo;
import jb.pageModel.Tree;
import jb.service.BasedataServiceI;
import jb.service.ResourceServiceI;
import jb.service.ResourceTypeServiceI;
import jb.util.ConfigUtil;
import jb.util.Constants;
import jb.util.JbApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资源控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/appResourceController")
public class AppResourceController extends BaseController {

	@Autowired
	private ResourceServiceI resourceService;

	@Autowired
	private ResourceTypeServiceI resourceTypeService;
	
	@Autowired
	private BasedataServiceI basedataService;
	

	/**
	 * 获得资源树(资源类型为菜单类型)
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		return resourceService.tree(sessionInfo);
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
	public List<Tree> allTree(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		return resourceService.allTree(sessionInfo);
	}

	/**
	 * 跳转到资源管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/appresource/resource";
	}

	/**
	 * 跳转到资源添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		request.setAttribute("resourceTypeList", resourceTypeService.getResourceTypeList());
		Resource r = new Resource();
		r.setId(UUID.randomUUID().toString());
		request.setAttribute("resource", r);
		return "/admin/resourceAdd";
	}

	/**
	 * 添加资源
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Resource resource, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		resourceService.add(resource, sessionInfo);
		j.setSuccess(true);
		j.setMsg("添加成功！");
		return j;
	}

	/**
	 * 跳转到资源编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		request.setAttribute("resourceTypeList", resourceTypeService.getResourceTypeList());
		Resource r = resourceService.get(id);
		request.setAttribute("resource", r);
		return "/appresource/resourceEdit";
	}

	/**
	 * 跳转到html5资源编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editHtml5Page")
	public String editHtml5Page(HttpServletRequest request, String id) {
		String nid = parseHtmlToUid(id);
		String folderPath = Application.getString(Constants.SYSTEM_FOLDER_PATH);
		File file = new File(folderPath);
		List<String> files = new ArrayList<String>();
		if(file.isDirectory()){
			  String[] filelist = file.list();
			  files = Arrays.asList(filelist);			  
		}
		request.setAttribute("resourceHtml5List", files);
		Resource r = new Resource();
		r.setId(id);
		BaseData bd = basedataService.get("HL"+nid);
		if(bd!=null)
		r.setHtmlUrl(bd.getName());
		request.setAttribute("resource",r);
		return "/appresource/resourceHtml5";
	}
	
	/**
	 * 跳转到Map资源编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editMapPage")
	public String editMapPage(HttpServletRequest request, String id) {
		Resource r = new Resource();
		r.setId(id);
		String nid = parseHtmlToUid(id);
		BaseData bd = basedataService.get("MP"+nid);
		if(bd!=null)
		r.setPosition(bd.getName());
		request.setAttribute("resource",r);
		return "/appresource/resourceMap";
	}
	/**
	 * 跳转到html5资源编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editHtml5")
	@ResponseBody
	public Json editHtml5(Resource resource) {
		/*
		Resource r = resourceService.get(id);
		request.setAttribute("resource", r);*/
		resource.setId(parseHtmlToUid(resource.getId()));
		Json j = new Json();
		BaseData baseData = new BaseData();
		baseData.setId("HL"+resource.getId());
		baseData.setBasetypeCode("HL");
		baseData.setName(resource.getHtmlUrl());
		basedataService.add(baseData);
		j.setSuccess(true);
		j.setMsg("编辑成功！");
		return j;
	}
	/**
	 * 跳转到html5资源编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editMap")
	@ResponseBody
	public Json editMap(Resource resource) {
		/*
		Resource r = resourceService.get(id);
		request.setAttribute("resource", r);*/
		resource.setId(parseHtmlToUid(resource.getId()));
		Json j = new Json();
		BaseData baseData = new BaseData();
		baseData.setId("MP"+resource.getId());
		baseData.setBasetypeCode("MP");
		baseData.setName(resource.getPosition());
		if(F.empty(resource.getPosition())){
			basedataService.delete(baseData.getId());
		}else{
			basedataService.add(baseData);
		}
		j.setSuccess(true);
		j.setMsg("编辑成功！");
		return j;
	}
	/**
	 * 编辑资源
	 * 
	 * @param resource
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Resource resource) {
		Json j = new Json();
		resourceService.edit(resource);
		j.setSuccess(true);
		j.setMsg("编辑成功！");
		return j;
	}

	/**
	 * 获得资源列表
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @return
	 */
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Resource> treeGrid(HttpSession session) {
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Resource> list = resourceService.treeGridApp(Application.getString(Constants.SYSTEM_APP_SERVER_ROLE));
		List<BaseData> bds = basedataService.list("HL");
		List<BaseData> mps = basedataService.list("MP");
		Map<String,String> map = new HashMap<String,String>();
		for(BaseData bd : bds){
			map.put(bd.getId(), bd.getName());		
		}
		for(BaseData bd : mps){
			map.put(bd.getId(), bd.getName());		
		}
		try {
			list.addAll(JbApi.getResources(null));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for(Resource r : list){
			if("3".equals(r.getTypeId())){
				r.setHtmlUrl(map.get("HL"+parseHtmlToUid(r.getId())));
				r.setPosition(map.get("MP"+parseHtmlToUid(r.getId())));
			}
		}
		return list;
	}

	@SuppressWarnings("unused")
	private String parseUidToHtml(String uid){
		return uid.replace(".", "_");
	}
	private String parseHtmlToUid(String html){
		return html.replace("_", ".");
	}
	/**
	 * 删除资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		resourceService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}

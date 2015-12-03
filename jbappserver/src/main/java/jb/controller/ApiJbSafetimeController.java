package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.absx.F;
import jb.pageModel.Colum;
import jb.pageModel.DataGrid;
import jb.pageModel.JbSafetime;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.JbSafetimeServiceI;
import jb.util.JbApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * JbSafetime管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/jbSafetimeController")
public class ApiJbSafetimeController extends BaseController {

	@Autowired
	private JbSafetimeServiceI jbSafetimeService;


	/**
	 * 跳转到JbSafetime管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/jbsafetime/jbSafetime";
	}

	/**
	 * 获取JbSafetime数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(JbSafetime jbSafetime, PageHelper ph) {
		DataGrid dg = new DataGrid();
		try {
			dg.setRows(JbApi.getSafeTimeList(jbSafetime.getUid()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dg;
	}
	/**
	 * 获取JbSafetime数据表格excel
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
	public void download(JbSafetime jbSafetime, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(jbSafetime,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加JbSafetime页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		JbSafetime jbSafetime = new JbSafetime();
		jbSafetime.setId(UUID.randomUUID().toString());
		return "/jbsafetime/jbSafetimeAdd";
	}

	/**
	 * 添加JbSafetime
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(JbSafetime jbSafetime) {
		Json j = new Json();		
		try {
			j.setSuccess(JbApi.addSafeTime(jbSafetime.getUid(),jbSafetime.getStartWeek(),jbSafetime.getEndWeek(), jbSafetime.getStartTimeStr(), jbSafetime.getEndTimeStr()));
			j.setSuccess(true);
			j.setMsg("添加成功！");	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return j;
	}

	/**
	 * 跳转到JbSafetime查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		JbSafetime jbSafetime = jbSafetimeService.get(id);
		request.setAttribute("jbSafetime", jbSafetime);
		return "/jbsafetime/jbSafetimeView";
	}

	/**
	 * 跳转到JbSafetime修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		JbSafetime jbSafetime = jbSafetimeService.get(id);
		request.setAttribute("jbSafetime", jbSafetime);
		return "/jbsafetime/jbSafetimeEdit";
	}

	/**
	 * 修改JbSafetime
	 * 
	 * @param jbSafetime
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(JbSafetime jbSafetime) {
		Json j = new Json();		
		try {
			if(!F.empty(jbSafetime.getStatus())){
				JbApi.setSafeTimeStatus(jbSafetime.getId(), jbSafetime.getStatus());
			}else{
				JbApi.editSafeTime(jbSafetime.getId(), jbSafetime.getUid(), jbSafetime.getStartTimeStr(), jbSafetime.getEndTimeStr());
			}
			j.setSuccess(true);
			j.setMsg("编辑成功！");	
		} catch (IOException e) {
			e.printStackTrace();
		}			
		return j;
	}

	/**
	 * 删除JbSafetime
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		try {
			j.setSuccess(JbApi.delSafeTime(id));
			j.setSuccess(true);
			j.setMsg("删除成功！");	
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return j;
	}

}

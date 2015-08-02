package jb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import jb.absx.Objectx;
import jb.listener.Application;
import jb.pageModel.Json;
import jb.util.Constants;
import jb.util.StringEscapeEditor;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 基础控制器
 * 
 * 其他控制器继承此控制器获得日期字段类型转换和防止XSS攻击的功能
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/baseController")
public class BaseController extends Objectx {

	private String _publishSettingVal = "2"; //生产环境
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}

	/**
	 * 用户跳转JSP页面
	 * 
	 * 此方法不考虑权限控制
	 * 
	 * @param folder
	 *            路径
	 * @param jspName
	 *            JSP名称(不加后缀)
	 * @return 指定JSP页面
	 */
	@RequestMapping("/{folder}/{jspName}")
	public String redirectJsp(@PathVariable String folder, @PathVariable String jspName) {
		return "/" + folder + "/" + jspName;
	}
	
	@ExceptionHandler(Exception.class) 
	@ResponseBody
	public ModelAndView ExceptionHandler(Exception e) {
		
		Json j = new Json();
		if(_publishSettingVal.equals(Application.getString(Constants.SYSTEM_PUBLISH_SETTING))){
			j.setMsg(Application.getString(Constants.SYSTEM_GLOBAL_MESSAGE));
		}else{
			j.setMsg(e.getMessage());
		}
		ModelAndView mv = new ModelAndView();
		return mv;
	}

}

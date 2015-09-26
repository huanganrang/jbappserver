package jb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.pageModel.Tree;
import jb.pageModel.UserGroup;
import jb.service.AppServiceI;
import jb.service.impl.AndroidUserServiceImpl;
import jb.util.JbApi;

import org.androidpn.server.model.User;
import org.androidpn.server.xmpp.XmppServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * app控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/appClientController")
public class AppclientController extends BaseController {

	@Autowired
	private AppServiceI appService;
	
	private AndroidUserServiceImpl userService;
	
	public AppclientController(){
		 userService = (AndroidUserServiceImpl)XmppServer.getInstance().getBean("userService"); 
	}

	
	
	/**
	 * 在线用户
	 * 
	 * @return
	 */
	@RequestMapping("/onlineuser")
	@ResponseBody
	public Json onlineuser(HttpServletRequest request) {	
		Json j = new Json();
		try{
			j.setObj(userService.getUserInfo());
			j.setSuccess(true);
		}catch(Exception e){
			rememberLog(j,e);
		}
		
		return j;
	}
	@RequestMapping("/login")
	@ResponseBody
	public Json login(HttpServletRequest request){
		Json j = new Json();
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			password = password==null?"":password;
			User user = userService.getUserByUsername(username, password);
			if(user.getPassword().equals(password)){								
				j.setSessionId(request.getSession().getId());
				j.setObj(appService.getAppShowTree(username,null));
				j.setSuccess(true);
			}			
		}catch(Exception e){
			rememberLog(j,e);
		}
		return j;
	}
	
	@RequestMapping("/updatePsd")
	@ResponseBody
	public Json updatePsd(HttpServletRequest request){
		Json j = new Json();
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String newpassword = request.getParameter("newpassword");
			password = password==null?"":password;
			User user = userService.getUserByUsername(username, password);
			if(user.getPassword().equals(password)){		
				//调用接口去修改
				j.setSuccess(JbApi.updtePsd(username, newpassword));
			}else{
				j.setErrorCode("E2001");
				j.setMsg("用户名密码不正确");
			}			
		}catch(Exception e){
			rememberLog(j,e);
		}
		return j;
	}
	
	@RequestMapping("/getDoorList")
	@ResponseBody
	public Json getDoorList(HttpServletRequest request){
		Json j = new Json();
		try{
			String doorNo = request.getParameter("doorNo");
			String[] doorNos = doorNo.split("[,;]");
			j.setObj(JbApi.getDoorList(doorNos));
			j.setSuccess(true);
		}catch(Exception e){
			rememberLog(j,e);
		}
		return j;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Json logout(HttpServletRequest request){
		Json j = new Json();
		try{
			
			String sessionId = request.getParameter("sessionId");
			j.setSuccess(true);
			/*String username = request.getParameter("username");
			String password = request.getParameter("password");s
			password = password==null?"":password;
			User user = userService.getUserByUsername(username, password);
			if(user.getPassword().equals(password)){								
				j.setSessionId(request.getSession().getId());
				j.setObj(appService.getAppShowTree(username,null));
				j.setSuccess(true);
			}	*/		
		}catch(Exception e){
			rememberLog(j,e);
		}
		return j;
	}
	
	@RequestMapping("/refreshPerm")
	@ResponseBody
	public Json refreshPerm(HttpServletRequest request){
		Json j = new Json();
		try{
			String username = request.getParameter("username");			
			j.setSuccess(true);
			j.setObj(appService.getAppShowTree(username,null));			
		}catch(Exception e){
			rememberLog(j,e);
		}
		return j;
	}
	
	/**
	 * 获取测点值
	 * 
	 * @return
	 */
	@RequestMapping("/queryuid")
	@ResponseBody
	public Json queryuid(HttpServletRequest request) {	
		Json j = new Json();
		try{
			String quids = request.getParameter("quid");
	        if(logger.isDebugEnabled()){
	        	logger.debug("测点值查询quid:"+quids);
	        }
	        List<Map<String,String>> rs = appService.getUidValues(quids.split("[,;|]"));
	        if(logger.isDebugEnabled()){
	        	logger.debug("测点结果："+JSON.toJSONString(rs));
	        }
			j.setObj(rs);
			j.setSuccess(true);
		}catch(Exception e){
			rememberLog(j,e);
		}
		
		return j;
	}
	
	/**
	 * 设置测点值
	 * 
	 * @return
	 */
	@RequestMapping("/setuid")
	@ResponseBody
	public Json setuid(HttpServletRequest request) {	
		Json j = new Json();
		try{
			String quids = request.getParameter("quid");
			String values = request.getParameter("value");
			String command = request.getParameter("command");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
	        if(logger.isDebugEnabled()){
	        	logger.debug("测点值查询quid:"+quids);
	        }
	        String[] _quids =  quids.split("[,;|]");
	        String[] _values=  values.split("[,;|]");
	        if(_quids.length == _values.length){	        
		       String rs = appService.setUidValues(_quids, command, _values,userName,password);
		        if(logger.isDebugEnabled()){
		        	logger.debug("测点结果："+rs);
		        }
				j.setObj(rs);
				j.setSuccess(true);
	        }
		}catch(Exception e){
			rememberLog(j,e);
		}
		
		return j;
	}
	
	/**
	 * 获取tcp 结果
	 * 
	 * @return
	 */
	@RequestMapping("/querytcp")
	public void queryTcp(HttpServletRequest request,HttpServletResponse response) {	
		PrintWriter writer = null;
		try{
			String req = request.getParameter("req");
			String jsonp = request.getParameter("jsoncallback");
	        if(logger.isDebugEnabled()){
	        	logger.debug("req:"+req);
	        }
	        String rs = appService.getTcpResult(req);
	        if(logger.isDebugEnabled()){
	        	logger.debug("测点结果："+rs);
	        }
			String str = jsonp + "(" +rs+")";
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			writer.write(str);
			writer.flush();
		}catch(Exception e){
			if(writer!=null)
				writer.close();
			logger.error("AppclientController erro", e);			
		}finally{
			if(writer!=null)
				writer.close();
		}
		
	}
	
	/**
	 * 曲线图
	 * 
	 * @return
	 */
	@RequestMapping("/seqchart")
	@ResponseBody
	public Json seqchart(HttpServletRequest request) {	
		Json j = new Json();
		try{
			String fromDateTime = F.delNull(request.getParameter("fromDateTime"));
	        String toDateTime = F.delNull(request.getParameter("toDateTime"));
	        String uid = F.delNull(request.getParameter("uid"));
	        String minWidth = F.delNull(request.getParameter("minWidth"));
	        String name = F.delNull(request.getParameter("name"));
	        String chartType = F.delNull(request.getParameter("chartType"));
	        String dateType = F.delNull(request.getParameter("dateType"));
	        String rs = JbApi.getChart(chartType, dateType, fromDateTime, toDateTime, uid, minWidth, name);	       
	        if(logger.isDebugEnabled()){
	        	logger.debug("时序图结果："+rs);
	        }	        
		   /* rs = rs.substring(1, rs.length()-1);
			JSONObject obj = JSON.parseObject(rs);*/
			j.setObj(rs);
			j.setSuccess(true);
		}catch(Exception e){
			rememberLog(j,e);
		}
		
		return j;
	}
	@RequestMapping("/sureevent")
	@ResponseBody
	public Json sureevent(HttpServletRequest request) {	
		Json j = new Json();
		try{
			String username = F.delNull(request.getParameter("username"));
		    String confirm = F.delNull(request.getParameter("confirm"));
		    String rs = JbApi.sureEvent(username, confirm);		    
			j.setObj(rs);
			j.setSuccess(true);
		}catch(Exception e){
			rememberLog(j,e);
		}
		
		return j;
	}
	
	@RequestMapping("/historyevent")
	@ResponseBody
	public Json historyevent(HttpServletRequest request) {	
		Json j = new Json();
		try{
			String username = F.delNull(request.getParameter("username"));
		    String past = F.delNull(request.getParameter("past"));
		    String rs = JbApi.getHistoryEvent(username, past);	 
		   /* rs = rs.substring(1, rs.length()-1);
			JSONObject obj = JSON.parseObject(rs);*/
			j.setObj(rs);
			j.setSuccess(true);
		}catch(Exception e){
			rememberLog(j,e);
		}		
		return j;
	}
	
	@RequestMapping("/allgroup")
	@ResponseBody
	public Json allgroup(HttpServletRequest request) {	
		Json j = new Json();
		try{
        	@SuppressWarnings("unchecked")
			List<UserGroup> rs = appService.dataGrid(null, new PageHelper()).getRows();    
			j.setObj(rs);
			j.setSuccess(true);
		}catch(Exception e){
			rememberLog(j,e);
		}		
		return j;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/groupperm")
	@ResponseBody
	public Json groupperm(HttpServletRequest request) {	
		Json j = new Json();
		try{
			String nGroupNo =  request.getParameter("nGroupNo");
			List<Tree> trees = appService.allTree(nGroupNo);
			Map<String,Tree> map = new HashMap<String,Tree>();
			for(Tree t : trees){
				map.put(t.getId(), t);
			}
			Tree pt;
			Map<String,Object> attr;
			Iterator<Tree> iter = trees.iterator();
			Tree t;
			while(iter.hasNext()){
				t = iter.next();
				if(!F.empty(t.getPid())){
					pt = map.get(t.getPid());
					if(pt!=null){
						List<Tree> children = pt.getChildren();
						if(children == null){
							children = new ArrayList<Tree>();
							pt.setChildren(children);
						}
						children.add(t);
						iter.remove();
					}
				}
				attr = (HashMap<String,Object>)t.getAttributes();
				if(attr!=null){
					if(attr.get("spotlist")!=null){
						List<Tree> children = t.getChildren();
						if(children == null){
							children = new ArrayList<Tree>();
							t.setChildren(children);
						}
						children.addAll((ArrayList)attr.get("spotlist"));
						attr.remove("spotlist");
					}
				}
			}
			j.setObj(trees);
			j.setSuccess(true);
		}catch(Exception e){
			rememberLog(j,e);
		}		
		return j;
	}
	@RequestMapping("/savepermcfg")
	@ResponseBody
	public Json savepermconfig(HttpServletRequest request) {	
		Json j = new Json();
		try{
			String resourceIds = request.getParameter("resourceIds");
			String nGroupNo = request.getParameter("nGroupNo");
			String uidIds = request.getParameter("uidIds");
			appService.grant(nGroupNo, resourceIds.split("[,]"), uidIds.split("[,]"));			
			j.setSuccess(true);
		}catch(Exception e){
			rememberLog(j,e);
		}		
		return j;
	}
	private void rememberLog(Json j,Exception e){
		logger.error("AppclientController erro", e);
		if (e instanceof IOException) {
			String m = Application.getString("SV150");
			j.setErrorCode("E1001");
			if(F.empty(m))				
				j.setMsg("请联系管理员，检查kingweb是否良好");
			else
				j.setMsg(m);			
		}	
	}
}

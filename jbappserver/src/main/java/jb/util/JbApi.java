package jb.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.JbSafetime;
import jb.pageModel.Resource;
import jb.pageModel.Tree;
import jb.pageModel.UserGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public abstract class JbApi {
	
	public static boolean login(String username,String psd) throws IOException{
		//登录
		String url = Application.getString("UL001");
		//UserName=Admin&UserPWD=
		Map<String,String> param = new HashMap<String,String>();
		param.put("UserName", username);
		param.put("UserPWD",psd);
	    String rs =WebUtils.doPost(url, param, 15000,25000);
	    if(rs.indexOf("登录成功")>-1){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	public static boolean updtePsd(String username,String psd)throws IOException{
		//登录
		String url = Application.getString("UL101");
		//UserName=Admin&UserPWD=
		Map<String,String> param = new HashMap<String,String>();
		param.put("UserName", username);
		param.put("UserPWD",psd);
	    String rs =WebUtils.doPost(url, param, 15000,25000);
	    if(rs.indexOf("修改成功")>-1){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	public static List<UserGroup> getAllGroup() throws IOException{
		 //获取所有用户组
		 String url = Application.getString("UL002");
		 String rs =WebUtils.doGetJson(url, null);
	
		 return JSONObject.parseArray(rs, UserGroup.class);
	}
	
	public static List<UserGroup> getGroups(String username) throws IOException{
		 //获取所有用户组
		 String url = Application.getString("UL003");
		 Map<String,String> param = new HashMap<String,String>();
		 param.put("UserName", username);
		 String rs =WebUtils.doGet(url, param);
		 return JSONArray.parseArray(rs, UserGroup.class);
	}
	
	public static List<Tree> getTree() throws IOException{
		String url = Application.getString("UL004");
		String rs = WebUtils.doGetJson(url, null);
		rs = replaceSpecialChar(rs);
		rs = rs.substring(1, rs.length()-1);
		System.out.println(rs);
		JSONObject obj = JSON.parseObject(rs);
		JSONArray list = obj.getJSONArray("data");
		List<Tree> trees = new ArrayList<Tree>();
		paseTree(list, trees);
		return trees;
		 
	}
	
	public static void main(String[] args){
		
	}
	
	
	private static void paseTree(JSONArray list,List<Tree> trees){
		if(list==null||list.size()==0)
			return;
		JSONObject t = null;
		Tree tree = null;
		Map<String,Object> attr = null;
		for(int i = 0;i<list.size();i++){
			t = list.getJSONObject(i);
			tree = new Tree();
			attr = new HashMap<String,Object>();
			tree.setId(t.getString("UID"));
			tree.setText(t.getString("Title"));
			tree.setKw(true);
			attr.put("spotType", t.getString("SpotType"));
			attr.put("property", t.getString("property"));
			attr.put("kw", true);
			tree.setAttributes(attr);
			tree.setPid(t.getString("Parent"));
			trees.add(tree);
			paseTree(t.getJSONArray("SubNodes"),trees);
		}
	}
	
	private static void paseResource(JSONArray list,List<Resource> trees,String pid){
		if(list==null||list.size()==0)
			return;		
		JSONObject t = null;
		Resource tree = null;
		for(int i = 0;i<list.size();i++){
			t = list.getJSONObject(i);
			if(!F.empty(t.getString("SpotType")))
				continue;
			String pp = t.getString("property");
			if(!F.empty(pp)){
				if(Integer.parseInt(pp)>2)
					continue;
			}
			tree = new Resource();
			tree.setId(parseUidToHtml(t.getString("UID")));
			tree.setName(t.getString("Title"));
			tree.setTypeId("3");
			tree.setPid(parseUidToHtml(t.getString("Parent")));
			if(F.empty(tree.getPid())){
				tree.setPid(pid);
			}			
			trees.add(tree);
			paseResource(t.getJSONArray("SubNodes"),trees,pid);
		}
	}
	
	public static List<Resource> getResources(String groupno) throws IOException{
		String pid = Application.getString(Constants.SYSTEM_APP_RESOURCE_ID);
		Map<String, String> param = null;
		String url = null;
		if(F.empty(groupno)){
			url = Application.getString("UL004");
		}else{
			url = Application.getString("UL005");
			param = new HashMap<String, String>();
			param.put("GroupNo", groupno);
		}
		String rs = WebUtils.doGetJson(url, param);		
		rs = replaceSpecialChar(rs);
		rs = rs.substring(1, rs.length()-1);
		JSONObject obj = JSON.parseObject(rs);
		JSONArray list = obj.getJSONArray("data");
		List<Resource> trees = new ArrayList<Resource>();
		paseResource(list, trees, pid);
		return trees;
	}
	
	private static String parseUidToHtml(String uid){
		return uid.replace(".", "_");
	}
	
	public static List<Tree> getTree(String usergroup) throws IOException{
		 //获取所有用户组
		 String url = Application.getString("UL005");
		 Map<String,String> param = new HashMap<String,String>();
		 param.put("GroupNo", usergroup);
		 String rs = WebUtils.doGetJson(url, param);
		 rs = replaceSpecialChar(rs);
		 rs = rs.substring(1, rs.length()-1);
		// System.out.println(rs);
		 JSONObject obj = JSON.parseObject(rs);
		 JSONArray list = obj.getJSONArray("data");
		 List<Tree> trees = new ArrayList<Tree>();
		 paseTree(list,trees);
		 return trees;
	}
	
	/**
	 * 历史事件
	 * @param username
	 * @param past
	 * @return
	 * @throws IOException
	 */
	public static String getHistoryEvent(String username,String past) throws IOException{
		String charset = "UTF-8";
		String url = Application.getString("UL007");
		JSONObject object = JSON.parseObject(past);
		String query ="UserName="+URLEncoder.encode(username, charset);
		Object _v;
		String _p = "";
		for(String key : object.keySet()){
			_p = "Past["+key+"]";
			_v = object.get(key);
			if(_v instanceof JSONArray) {
				JSONArray vlist = (JSONArray)_v;
				for(Object s : vlist){
					query +="&"+_p+"[]"+"="+URLEncoder.encode(s.toString(),charset);
				}
				
			}else{
				query +="&"+_p+"="+URLEncoder.encode(_v.toString(),charset);
			}
		}
		
		//query = "UserName=Admin&Past[DateTime][]=2015-05-01&Past[DateTime][]=2015-05-16&Past[UID][]=6.12.2.21.&Past[Level]=3&Past[Symbol]=0";
		String rs = WebUtils.doPostJson(url, query);
		rs = replaceSpecialChar(rs);
		return rs;
	}
	public static String getDoorList(String[] doors,String cardNO,String startDate,String endDate) throws IOException{
		String charset = "UTF-8";
		String url = Application.getString("UL102");
		String query = "";		
		for(String door : doors){
			query +="&DoorNO[]="+URLEncoder.encode(door,charset);
		}
		query +="&CardNO="+URLEncoder.encode(cardNO,charset);
		query +="&StartDate="+URLEncoder.encode(startDate,charset);
		query +="&endTime="+URLEncoder.encode(endDate,charset);

		if(query.length()>0){
			query = query.substring(1);
		}
		String rs = WebUtils.doPostJson(url, query);
		rs = replaceSpecialChar(rs);
		rs = rs.substring(1, rs.length()-1);
		return rs;
	}
	
	//DoorNO[]
	/**
	 * 确认事件
	 * @param username
	 * @param confirm
	 * @return
	 * @throws IOException
	 */
	public static String sureEvent(String username,String confirm) throws IOException{
		String charset = "UTF-8";
		String url = Application.getString("UL008");
		JSONArray array = JSON.parseArray(confirm);
		String query = "UserName="+URLEncoder.encode(username,charset);
		JSONObject jobj = null;
		String _p;
		Object _v;
		for(int i = 0;i<array.size();i++){
			_p ="Confirm["+i+"]";
			jobj = array.getJSONObject(i);
			for(String key : jobj.keySet()){
				_v = jobj.get(key);
				query +="&"+URLEncoder.encode(_p+"["+key+"]",charset)+"="+URLEncoder.encode(_v.toString(),charset) ;
			}
		}
		String rs = WebUtils.doPostJson(url, query);
		rs = replaceSpecialChar(rs);
		return rs;
	}
	
	/**
	 * @param chartType
	 * @param dateType
	 * @param fromDateTime
	 * @param toDateTime
	 * @param uid
	 * @param minWidth
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static String getChart(String chartType,String dateType,String fromDateTime,String toDateTime,String uid,String minWidth,String name) throws IOException{
		String url = Application.getString("UL009");
		String charset = "UTF-8";		
		String content ="ChartType="+URLEncoder.encode(chartType,charset);
		content +="&DateType="+URLEncoder.encode(dateType,charset);
		content +="&FromDateTime="+URLEncoder.encode(fromDateTime,charset);
		content +="&ToDateTime="+URLEncoder.encode(toDateTime,charset);
		content +="&Uid="+URLEncoder.encode(uid,charset);	
		content +="&Name="+URLEncoder.encode(name,charset);	
		content +="&MinWidth="+URLEncoder.encode(minWidth,charset);	
		String rs = WebUtils.doPostJson(url, content);		
		rs = replaceSpecialChar(rs);
		return rs;
	}
	
	public static List<JbSafetime> getSafeTimeList(String uid) throws IOException{
		String url = Application.getString("UL103")+uid;
		//url: "http://183.193.5.74:8282/api/TimeZone/QueryTimeList/?uid=5.",
		
		String rs = WebUtils.doPostJson(url, null);		
		rs = replaceSpecialChar(rs);
		rs = rs.substring(1, rs.length()-1);
		JSONObject obj = JSON.parseObject(rs);
		JSONArray list = obj.getJSONArray("table");
		JSONObject temp = null;
		List<JbSafetime> resultList = new ArrayList();
		for(int i = 0;i<list.size();i++){
			temp = list.getJSONObject(i);
			if(F.empty(temp.getString("Uid"))){
				continue;
			}
			JbSafetime safetime = new JbSafetime();
			safetime.setId(temp.getIntValue("NID")+"");
			safetime.setStartTimeStr(to2n(temp.getIntValue("NHour1"))+to2n(temp.getIntValue("NMinute1"))+to2n(temp.getIntValue("NSecond1")));
			safetime.setEndTimeStr(to2n(temp.getIntValue("NHour2"))+to2n(temp.getIntValue("NMinute2"))+to2n(temp.getIntValue("NSecond2")));
			safetime.setStatus(temp.getIntValue("Enabled") == 0?"SS01":"SS02");
			resultList.add(safetime);
		}
		return resultList;
	}
	
	public static boolean setSafeTimeStatus(String id,String status) throws IOException{
		String url = Application.getString("UL104");
		//http://183.193.5.74:8282/api/TimeZone/UpTimeStatus/data
		String query = "timezone[0][NID]="+id+"&timezone[0][Enabled]="+("SS01".equals(status)?0:1);
		String rs = WebUtils.doPostJson(url, query);
		rs = replaceSpecialChar(rs);
		return rs.indexOf("OK")>-1;
	}
	
	public static boolean delSafeTime(String id) throws IOException{
		String url = Application.getString("UL105");
		url += "?no="+id;
		//http://183.193.5.74:8282/api/TimeZone/DelTimeByNo/?no=4
		String rs = WebUtils.doPostJson(url, null);
		rs = replaceSpecialChar(rs);
		return rs.indexOf("true")>-1;
	}
	public static boolean addSafeTime(String uid,String starttime,String endtime) throws IOException{
		String url = Application.getString("UL106");
		//http://183.193.5.74:8282/api/TimeZone/InTimeByNo/time
		String query = "timezone[NID]=0";
		/*query += "&timezone[NWeek1]:2";
		query += "&timezone[NWeek2]:2";*/
		query += "&timezone[nHour1]="+starttime.substring(0, 2);
		query += "&timezone[nHour2]="+endtime.substring(0, 2);
		query += "&timezone[NMinute1]="+starttime.substring(2, 4);
		query += "&timezone[NMinute2]="+endtime.substring(2, 4);
		query += "&timezone[NSecond1]="+starttime.substring(4, 6);
		query += "&timezone[NSecond2]="+endtime.substring(4, 6);
		//query += "&timezone[SDescription]:dsfasdfasd";
		//query += "&timezone[BWeekRange]:1";
		query += "&timeZonedetail[Uid]="+uid;
		String rs = WebUtils.doPostJson(url, query);
		rs = replaceSpecialChar(rs);
		return rs.indexOf("添加成功")>-1;
	}
	public static boolean editSafeTime(String id,String uid,String starttime,String endtime) throws IOException{
		String url = Application.getString("UL106");
		//http://183.193.5.74:8282/api/TimeZone/InTimeByNo/time
		String query = "timezone[NID]="+id;
		/*query += "&timezone[NWeek1]:2";
		query += "&timezone[NWeek2]:2";*/
		query += "&timezone[nHour1]="+starttime.substring(0, 2);
		query += "&timezone[nHour2]="+endtime.substring(0, 2);
		query += "&timezone[NMinute1]="+starttime.substring(2, 4);
		query += "&timezone[NMinute2]="+endtime.substring(2, 4);
		query += "&timezone[NSecond1]="+starttime.substring(4, 6);
		query += "&timezone[NSecond2]="+endtime.substring(4, 6);
		//query += "&timezone[SDescription]:dsfasdfasd";
		//query += "&timezone[BWeekRange]:1";
		query += "&timeZonedetail[Uid]="+uid;
		String rs = WebUtils.doPostJson(url, query);
		rs = replaceSpecialChar(rs);
		return rs.indexOf("添加成功")>-1;
	}

	public static String getAllDoor() throws IOException {
		String url = Application.getString("UL017");
		String rs = WebUtils.doGetJson(url, null);
		rs = replaceSpecialChar(rs);
		rs = rs.substring(1, rs.length()-1);
		return rs;
	}

	public static String getAllCard() throws IOException {
		String url = Application.getString("UL018");
		String rs = WebUtils.doGetJson(url, null);
		rs = replaceSpecialChar(rs);
		rs = rs.substring(1, rs.length()-1);
		return rs;
	}

	private static String to2n(int number){
		return String.format("%02d", number); 
	}
	private static String replaceSpecialChar(String str){
		str = str.trim();
		return str.replace("\\\"", "\"");
	}
}

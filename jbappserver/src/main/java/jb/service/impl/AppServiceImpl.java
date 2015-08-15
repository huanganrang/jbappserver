package jb.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jb.absx.F;
import jb.absx.Objectx;
import jb.absx.UUID;
import jb.android.push.NotificationManager;
import jb.dao.GroupUidDaoI;
import jb.listener.Application;
import jb.mina.MconnMange;
import jb.mina.MinaRequest;
import jb.model.TgroupUid;
import jb.pageModel.BaseData;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.pageModel.Resource;
import jb.pageModel.Tree;
import jb.pageModel.UserGroup;
import jb.service.AppServiceI;
import jb.service.BasedataServiceI;
import jb.service.ResourceServiceI;
import jb.util.Constants;
import jb.util.JbApi;

import org.androidpn.server.service.UserNotFoundException;
import org.androidpn.server.xmpp.XmppServer;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.SessionManager;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class AppServiceImpl extends Objectx implements AppServiceI {
	private int UID_TYPE = 0; //kingweb 资源
	private int RES_TYPE = 1; //本系统资源
	private String SPLIT = "_";
	@Autowired
	private ResourceServiceI resourceService;
	
	@Autowired
	private GroupUidDaoI groupUidDao;
	
	@Autowired
	private BasedataServiceI basedataService;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tree> allTree(String groupNo) {
		List<Tree> lt = new ArrayList<Tree>();
		String roleId = Application.getString(Constants.SYSTEM_APP_SERVER_ROLE);
		List<Resource> l = resourceService.treeGridApp(roleId);
		Map<String,Integer> authMap = getAuthMap(groupNo);
		String pid = Application.getString(Constants.SYSTEM_APP_RESOURCE_ID);
		if (l != null && l.size() > 0) {
			for (Resource r : l) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(r, tree);
				tree.setText(r.getName());
				tree.setIconCls(r.getIconCls());
				/*Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);*/
				if(isChecked(RES_TYPE+SPLIT+r.getId(), authMap)){
					tree.setChecked(true);
				}				
				lt.add(tree);
			}
		}
		try {
			//List<Tree> outTrees = JbApi.getTree(groupNo);
			List<Tree> outTrees = JbApi.getTree();			
			Map<String,Tree> mapTree = new HashMap<String,Tree>();
			for(Tree tree : outTrees){
				mapTree.put(tree.getId(), tree);
			}
			Map<String,Object> map = null;
			Tree pt = null;
			List<Tree> removes = new ArrayList<Tree>();
			for(Tree t : outTrees){
				if(F.empty(t.getPid())){
					t.setPid(pid);					
				}
				map = (Map<String,Object>)t.getAttributes();
				if(!F.empty(map.get("spotType").toString())){
					pt = mapTree.get(t.getPid());
					map = (Map<String,Object>)pt.getAttributes();
					List<Tree> list = (List<Tree>)map.get("spotlist");
					if(list==null){
						list = new ArrayList<Tree>();
						map.put("spotlist", list);
					}
					list.add(t);
					removes.add(t);
				}			
			}
			outTrees.removeAll(removes);
			for(Tree t : outTrees){
				
				if(isChecked(UID_TYPE+SPLIT+t.getId(), authMap)){
					t.setChecked(true);
				}
				map = (Map<String,Object>)t.getAttributes();
				List<Tree> list = (List<Tree>)map.get("spotlist");
				if(list!=null){
					map.put("spotlist", sort(list,authMap));
				}
			}
			lt.addAll(outTrees);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
				
		return lt;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tree> getAppShowTree(String username,ClientSession session){
		List<UserGroup> groups;
		try {
			groups = JbApi.getGroups(username);
			if(session!=null)
			session.setSessionData("groups", groups);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		String[] groupnos = new String[groups.size()];
		for(int i = 0;i<groupnos.length;i++){
			groupnos[i] = groups.get(i).getNGroupNo()+"";
		}		
		List<Tree> lt = new ArrayList<Tree>();
		String roleId = Application.getString(Constants.SYSTEM_APP_SERVER_ROLE);
		List<Resource> l = resourceService.treeGridApp(roleId);
		Map<String,Integer> authMap = getAuthMap(groupnos);
		if(session!=null)
		session.setSessionData("authMap", authMap);
		String pid = Application.getString(Constants.SYSTEM_APP_RESOURCE_ID);
		if (l != null && l.size() > 0) {
			for (Resource r : l) {
				Tree tree = new Tree();
				BeanUtils.copyProperties(r, tree);
				tree.setText(r.getName());
				tree.setIconCls(r.getIconCls());
				if(isChecked(RES_TYPE+SPLIT+r.getId(), authMap)){
					tree.setChecked(true);
				}				
				lt.add(tree);
			}
		}
		List<BaseData> bds = basedataService.list("HL");
		List<BaseData> mps = basedataService.list("MP");
		Map<String,String> bdMap = new HashMap<String,String>();
		for(BaseData bd : bds){
			bdMap.put(bd.getId(), bd.getName());
		}
		for(BaseData bd : mps){
			bdMap.put(bd.getId(), bd.getName());
		}
		String prePath = Application.getString(Constants.SYSTEM_PRE_URL_PATH);
		try {
			
			lt.addAll(JbApi.getTree());
			Map<String,Tree> mapTree = new HashMap<String,Tree>();
			for(Tree tree : lt){
				mapTree.put(tree.getId(), tree);
			}
			Tree pt = null;			
			List<Tree> removes = new ArrayList<Tree>();
			for(Tree t : lt){
				if(F.empty(t.getPid())&&t.isKw()){
					t.setPid(pid);					
				}
				if(t.isKw()&&isChecked(UID_TYPE+SPLIT+t.getId(), authMap)){
					t.setChecked(true);
				}
				
				if(t.getAttributes()!=null&&bdMap.get("HL"+t.getId())!=null){
					Map<String,Object> attr = (Map<String,Object>)t.getAttributes();
					attr.put("html5", prePath+bdMap.get("HL"+t.getId()));
				}
				if(t.getAttributes()!=null&&bdMap.get("MP"+t.getId())!=null){
					Map<String,Object> attr = (Map<String,Object>)t.getAttributes();
					attr.put("position",bdMap.get("MP"+t.getId()));
				}
				pt = mapTree.get(t.getPid());
				if(pt == null)
					continue;
				List<Tree> list = pt.getChildren();
				if(list==null){
					list = new ArrayList<Tree>();
					pt.setChildren(list);
				}
				list.add(t);
				removes.add(t);
			}			
			lt.removeAll(removes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		filterTrees(lt,authMap);
		return lt;
	}
	private void filterTrees(List<Tree> trees,Map<String,Integer> authMap){
		List<Tree> removes = new ArrayList<Tree>();
		for(Tree tree :trees){
			if(tree.isChecked()){
				if(tree.getChildren()!=null){
					filterTrees(tree.getChildren(),authMap);
					tree.setChildren(sort(tree.getChildren(),authMap));
				}
			}else{
				removes.add(tree);
			}
		}
		trees.removeAll(removes);	
	}
	
	
	private List<Tree> sort(List<Tree> trees,Map<String,Integer> authMap){
		List<Map<String,Object>> nlist = new ArrayList<Map<String,Object>>();		
		for(Tree t:trees){
			if(isChecked(UID_TYPE+SPLIT+t.getId(), authMap)){
				t.setChecked(true);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("t", t);
				map.put("s", authMap.get(UID_TYPE+SPLIT+t.getId()));
				nlist.add(map);
			}
		}
		Collections.sort(nlist, new Comparator<Map<String,Object>>() {
			           @Override
			           public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			                 return ((Integer)o1.get("s"))-((Integer)o2.get("s"));
			            }
		});
		List<Tree> rslist = new ArrayList<Tree>();
		for(Map<String,Object> map : nlist){
			trees.remove(map.get("t"));
			rslist.add((Tree)map.get("t"));
		}
		rslist.addAll(trees);
		return rslist;
	}
	
	
	private boolean isChecked(String key,Map<String,Integer> authMap){
		Integer i = authMap.get(key);
		return i == null? false : true;
	}
	
	private Map<String,Integer> getAuthMap(String groupNo){
		Map<String,Integer> map  = new HashMap<String,Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupNo", Integer.parseInt(groupNo));
		List<TgroupUid> list = groupUidDao.find(" from TgroupUid t where t.ngroupNo =:groupNo ", params);
		for(TgroupUid t : list){
			int seq = 0;
			if(t.getSeq() == null)
				seq = -1;
			else
				seq = t.getSeq();
			map.put(t.getUtype()+SPLIT+t.getUid(), seq);
		}
		return map;
	}
	
	private Map<String,Integer> getAuthMap(String[] groupNo){
		Map<String,Integer> map  = new HashMap<String,Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		Integer[] grsno = new Integer[groupNo.length];
		for(int i = 0;i<grsno.length;i++){
			grsno[i] = Integer.parseInt(groupNo[i]);
		}
		params.put("groupNo", grsno);
		List<TgroupUid> list = groupUidDao.find(" from TgroupUid t where t.ngroupNo in(:groupNo) ", params);
		for(TgroupUid t : list){
			int seq = 0;
			if(t.getSeq() == null)
				seq = -1;
			else
				seq = t.getSeq();
			map.put(t.getUtype()+SPLIT+t.getUid(), seq);
		}
		return map;
	}

	@Override
	public DataGrid dataGrid(UserGroup userGroup, PageHelper ph) {
		DataGrid dg = new DataGrid();
		List<UserGroup> rs = new ArrayList<UserGroup>();
		try {
			rs = JbApi.getAllGroup();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		dg.setRows(rs);
		dg.setTotal(new Long(rs.size()));
		return dg;
	}

	@Override
	public void grant(String groupNo, String[] resourceId, String[] uid) {
		Map<String, Object> params = new HashMap<String, Object>();
		int gno = Integer.parseInt(groupNo);
		params.put("groupNo", gno);
		groupUidDao.executeHql(" delete from TgroupUid t where t.ngroupNo =:groupNo ", params);
	
		for(String r : resourceId){
			TgroupUid t = new TgroupUid();
			t.setId(UUID.uuid());
			t.setNgroupNo(gno);
			t.setUid(r);
			t.setUtype(RES_TYPE);
			groupUidDao.save(t);
		}
		int jj = 0;
		for(String r : uid){
			TgroupUid t = new TgroupUid();
			t.setId(UUID.uuid());
			t.setNgroupNo(gno);
			t.setUid(r);
			t.setUtype(UID_TYPE);
			t.setSeq(jj++);
			groupUidDao.save(t);
		}
	}



	@Override
	public List<Map<String, String>> getUidValues(String[] uids) {
		
		Map<String,String> map = null;
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for(String uid : uids){
			map = new HashMap<String,String>();
			map.put("UID", uid);
			map.put("Properties", "Value");
			list.add(map);
			map = new HashMap<String,String>();
			map.put("UID", uid);
			map.put("Properties", "Status");
			list.add(map);
		}
		Map<String,Object> requestMap = new HashMap<String,Object>();
		requestMap.put("Action", "Q");
		requestMap.put("Data", list);
		MinaRequest request  = new MinaRequest(JSON.toJSONString(requestMap));	
		IoSession session = null;
		try {
			session = (IoSession) MconnMange.getPool().borrowObject();
			synchronized (session) {
				try {
					session.write(request.getRequestText());
					session.wait(15000);
				} catch (InterruptedException e) {
					logger.error("tcp kingweb error", e);
					e.printStackTrace();
				}
			}	
			request.setResponse((String)session.getAttribute("response"));
		} catch (Exception e1) {
			logger.error("tcp kingweb error", e1);
			throw new RuntimeException(e1);
		}finally{
			if(session!=null){
				MconnMange.getPool().returnObject(session);
			}
		}	
		String response = request.getResponse();
		JSONObject jb = JSONObject.parseObject(response);
		list = new ArrayList<Map<String,String>>();
		if(jb!=null){			
			JSONArray jarray = jb.getJSONArray("Data");
			if(jarray!=null){
				Map<String,Map<String,String>> temp = new HashMap<String,Map<String,String>>();
				String uid = null;
				String properties = null;
				for(Object t : jarray){
					JSONObject json = (JSONObject)t;
					uid = json.getString("UID");
					properties = json.getString("Properties");
					map = temp.get(uid);
					if(map==null){
						map = new HashMap<String,String>();
						temp.put(uid, map);
					}
					map.put("uid", uid);
					if("Value".equals(properties)){
						map.put("value", json.getString("Value"));
					}else if("Status".equals(properties)){
						map.put("status", json.getString("Value"));
					}
				}
				list.addAll(temp.values());
			}
		}
		return list;
	}



	@Override
	public Map<String, Object> sureEvent(String id, String remark) {
		Map<String,Object>  rs = new HashMap<String,Object>();
		rs.put("success", true);
		return rs;
	}



	@Override
	public void notification(String rs) {
		JSONObject obj = JSON.parseObject(rs);
		if (obj != null) {
			JSONArray alist = obj.getJSONArray("Data");
			if (alist == null || alist.size() == 0)
				return;
			rs = JSON.toJSONString(alist);
			NotificationManager notif = (NotificationManager) XmppServer
					.getInstance().getBean("notificationManager");
			Collection<ClientSession> sessions = SessionManager.getInstance()
					.getSessions();
			Set<ClientSession> usernames = new HashSet<ClientSession>();
			for (ClientSession cs : sessions) {
				if (hasPerm(cs)) {
					usernames.add(cs);
				}

			}
			notif.sendNotifcationToSession("1234567890", "Admin", "timtle", rs,
					"uri",
					usernames.toArray(new ClientSession[usernames.size()]));
		}
	}

	@SuppressWarnings("unchecked")
	private boolean hasPerm(ClientSession session){
		List<UserGroup> groups=(List<UserGroup>)session.getSessionData("groups");
		if(groups == null){
			try {
				//帐号取用户组  变小写了
				String username = session.getUsername();
				try {
					groups = JbApi.getGroups(username);
					if(session!=null)
					session.setSessionData("groups", groups);
				} catch (IOException e1) {
					throw new RuntimeException(e1);
				}
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(groups!=null){
			for(UserGroup ug : groups){
				if(ug.getBCanConfirmEvent() == 1){
					return true;
				}
			}
		}
		return false;
	}
	


	@Override
	public List<Map<String, Object>> seqchart(String start, String end,
			String uid) {
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datetime", "2009-03-17 16:50:49");
		map.put("value", "11");
		ls.add(map);
		map = new HashMap<String, Object>();
		map.put("datetime", "2009-04-17 16:50:49");
		map.put("value", "15");
		ls.add(map);
		return ls;
	}



	@Override
	public String getTcpResult(String requestText) {
		MinaRequest request  = new MinaRequest(requestText);	
		IoSession session = null;
		try {
			session = (IoSession) MconnMange.getPool().borrowObject();
			synchronized (session) {
				try {
					session.write(request.getRequestText());
					session.wait(15000);
				} catch (InterruptedException e) {
					logger.error("tcp kingweb error", e);
					e.printStackTrace();
				}
			}	
			request.setResponse((String)session.getAttribute("response"));
		} catch (Exception e1) {
			logger.error("tcp kingweb error", e1);
			throw new RuntimeException(e1);
		}finally{
			if(session!=null){
				MconnMange.getPool().returnObject(session);
			}
		}	
		String response = request.getResponse();
		return response;
	}
}

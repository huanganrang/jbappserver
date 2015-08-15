package jb.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.AppuserDaoI;
import jb.model.Tappuser;
import jb.pageModel.Appuser;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.pageModel.UserGroup;
import jb.service.AppuserServiceI;

import org.androidpn.server.service.UserNotFoundException;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.SessionManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppuserServiceImpl implements AppuserServiceI {

	@Autowired
	private AppuserDaoI appuserDao;

	@SuppressWarnings("unchecked")
	@Override
	public DataGrid dataGrid(Appuser appuser, PageHelper ph) {
		DataGrid dg = new DataGrid();		
		Collection<ClientSession> sessions = SessionManager.getInstance().getSessions();
		List<ClientSession> nlist = new ArrayList<ClientSession>();		
		for(ClientSession cs : sessions){
			String uname = null;
			try {
				//uname = cs.getUsername();
				uname = (String)cs.getSessionData("username");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(F.empty(appuser.getUsername())||(!F.empty(uname)&&uname.indexOf(appuser.getUsername())>-1)){
				nlist.add(cs);
			}
		}		
		ClientSession[] slists = nlist.toArray(new ClientSession[nlist.size()]);
		List<Appuser> list = new ArrayList<Appuser>();
		Appuser u = null;
		int start = (ph.getPage()-1)*ph.getRows();
		int end = ph.getPage()*ph.getRows();
		for(;start<end&&start<slists.length;start++){
			ClientSession s = slists[start];
			u = new Appuser();
			try {
				u.setId(s.getUsername());
				u.setUsername((String)s.getSessionData("username"));
				u.setFromIp(s.getHostName());
				u.setId(s.getStreamID());
				List<UserGroup> groups = ((List<UserGroup>)s.getSessionData("groups"));
				if(groups!=null&&groups.size()>0){
					String gname  = "";
					for(UserGroup ug : groups){
						gname += ug.getSGroupName()+",";
					}
					if(gname.length()>0){
						gname = gname.substring(0, gname.length()-1);
					}
					u.setGroup(gname);
				}				
				list.add(u);
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dg.setRows(list);
		dg.setTotal(new Long(sessions.size()));
		return dg;
	}

	@SuppressWarnings("unused")
	private String orderHql(PageHelper ph) {
		String orderString = "";
		if (ph.getSort() != null && ph.getOrder() != null) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}

	@SuppressWarnings("unused")
	private String whereHql(Appuser appuser, Map<String, Object> params) {
		String whereHql = "";	
		if (appuser != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(appuser.getUsername())) {
				whereHql += " and t.username = :username";
				params.put("username", appuser.getUsername());
			}		
			if (!F.empty(appuser.getGroup())) {
				whereHql += " and t.group = :group";
				params.put("group", appuser.getGroup());
			}		
			if (!F.empty(appuser.getFromIp())) {
				whereHql += " and t.fromIp = :fromIp";
				params.put("fromIp", appuser.getFromIp());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(Appuser appuser) {
		Tappuser t = new Tappuser();
		BeanUtils.copyProperties(appuser, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		appuserDao.save(t);
	}

	@Override
	public Appuser get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Tappuser t = appuserDao.get("from Tappuser t  where t.id = :id", params);
		Appuser o = new Appuser();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(Appuser appuser) {
		Tappuser t = appuserDao.get(Tappuser.class, appuser.getId());
		if (t != null) {
			BeanUtils.copyProperties(appuser, t, new String[] { "id" , "createdatetime" });
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		Collection<ClientSession> sessions = SessionManager.getInstance().getSessions();
		for(ClientSession s : sessions){
			if(s.getStreamID().equals(id)){
				s.close();
				break;
			}
		}
	}

}

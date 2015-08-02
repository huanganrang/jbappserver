package jb.service.impl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jb.pageModel.UserGroup;
import jb.util.JbApi;

import org.androidpn.server.model.User;
import org.androidpn.server.service.UserExistsException;
import org.androidpn.server.service.UserNotFoundException;
import org.androidpn.server.service.UserService;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.SessionManager;

public class AndroidUserServiceImpl implements UserService {
	
	protected SessionManager sessionManager;
	
	public AndroidUserServiceImpl(){
		sessionManager = SessionManager.getInstance();
	}

	@Override
	public User getUser(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String u) throws UserNotFoundException {
		User user = new User();
		user.setUsername(u);
		user.setPassword("123456");
		return user;
	}
	
	
	public User getUserByUsername(String u,String psd) throws UserNotFoundException {
		/*if(true){
		User user = new User();
		user.setUsername(u);
		user.setPassword("123");
		return user;
		}*/
		/*if(F.empty(psd)){
			ClientSession session = SessionManager.getInstance().getSession(u);
			if(session!=null&&session.getSessionData("userinfo")!=null){
				User user = (User)session.getSessionData("userinfo");
				return user;
			}
		}*/
		boolean lg = false;
		try {
		
			lg = JbApi.login(u, psd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		User user = new User();
		user.setUsername(u);
		if(lg){
			user.setPassword(psd);
		}else{
			user.setPassword("");
		}
		return user;
	}
	
	public User getUserByUsernameHttp(String u,String psd) throws UserNotFoundException {
		
		boolean lg = false;
		try {
		
			lg = JbApi.login(u, psd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		User user = new User();
		user.setUsername(u);
		if(lg){
			user.setPassword(psd);
		}else{
			user.setPassword("");
		}
		return user;
	}
	@SuppressWarnings("unused")
	@Override
	public List<User> getUsers() {
		Collection<ClientSession> sessions = sessionManager.getSessions();
		List<User> list = new ArrayList<User>();
		/**/
		return list;
	}

	public List<Map<String,String>> getUserInfo(){
		Collection<ClientSession> sessions = SessionManager.getInstance().getSessions();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> u = null;
		for(ClientSession s : sessions){
			if(s.getAuthToken()==null)continue;
			u = new HashMap<String,String>();
			try {
				@SuppressWarnings("unchecked")
				List<UserGroup> groups = ((List<UserGroup>)s.getSessionData("groups"));
				if(groups!=null&&groups.size()>0){
					String gname  = "";
					for(UserGroup ug : groups){
						gname += ug.getSGroupName()+",";
					}
					if(gname.length()>0){
						gname = gname.substring(0, gname.length()-1);
					}
					u.put("ugroup", gname);	
				}else{
					u.put("ugroup", "");	
				}
				u.put("username", s.getUsername());			
				u.put("ip", s.getHostName());
				u.put("id", s.getStreamID());
				list.add(u);
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public void removeUser(Long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public User saveUser(User u) throws UserExistsException {
		// TODO Auto-generated method stub
		//System.out.println("11111111111111");
		return null;
	}

}

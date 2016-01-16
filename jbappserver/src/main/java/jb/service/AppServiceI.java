package jb.service;

import java.util.List;
import java.util.Map;

import org.androidpn.server.xmpp.session.ClientSession;

import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.pageModel.Tree;
import jb.pageModel.UserGroup;

public interface AppServiceI {
	
	/**
	 * 根据用户组获取权限树
	 * @param groupNo
	 * @return
	 */
	public List<Tree> allTree(String groupNo);
	
	
	/**
	 * 获取用户组信息
	 * @param bug
	 * @param ph
	 * @return
	 */
	public DataGrid dataGrid(UserGroup userGroup, PageHelper ph);
	
	
	/**
	 * 授权
	 * @param groupNo
	 * @param resourceId
	 * @param uid
	 */
	public void grant(String groupNo,String[] resourceId,String[] uid);

	
	/**
	 * app获取权限树
	 * @return
	 */
	public List<Tree> getAppShowTree(String usergroup,ClientSession session);
	
	/**
	 * 获取测点信息
	 * @return
	 */
	public List<Map<String,String>> getUidValues(String[] uids);
	
	/**
	 * 设置测点值
	 * @return
	 */
	public String setUidValues(String[] uids,String command,String[] values,String userName,String password);
	
	/**
	 * 事件确认
	 * @param id
	 * @param remark
	 * @return
	 */
	public Map<String,Object> sureEvent(String id,String remark);
	
	
	/**
	 * 曲线图查找
	 * @param start
	 * @param end
	 * @param uid
	 * @return
	 */
	public List<Map<String,Object>> seqchart(String start,String end,String uid);
	
	/**
	 * 消息通知
	 * @param rs
	 */
	public void notification(String rs);

	/**
	 * 消息通知
	 * @param rs
	 */
	void notificationString(String rs);
	
	/**
	 * 发送命令，取tcp结果
	 * @param request
	 * @return
	 */
	public String getTcpResult(String request);

	void addIosDeviceToken(String userId,String deviceToken);
}

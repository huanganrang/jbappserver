package jb.service;

import jb.pageModel.Appuser;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface AppuserServiceI {

	/**
	 * 获取Appuser数据表格
	 * 
	 * @param appuser
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(Appuser appuser, PageHelper ph);

	/**
	 * 添加Appuser
	 * 
	 * @param appuser
	 */
	public void add(Appuser appuser);

	/**
	 * 获得Appuser对象
	 * 
	 * @param id
	 * @return
	 */
	public Appuser get(String id);

	/**
	 * 修改Appuser
	 * 
	 * @param appuser
	 */
	public void edit(Appuser appuser);

	/**
	 * 删除Appuser
	 * 
	 * @param id
	 */
	public void delete(String id);

}

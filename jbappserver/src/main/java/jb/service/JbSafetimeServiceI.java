package jb.service;

import jb.pageModel.JbSafetime;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface JbSafetimeServiceI {

	/**
	 * 获取JbSafetime数据表格
	 * 
	 * @param jbSafetime
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(JbSafetime jbSafetime, PageHelper ph);

	/**
	 * 添加JbSafetime
	 * 
	 * @param jbSafetime
	 */
	public void add(JbSafetime jbSafetime);

	/**
	 * 获得JbSafetime对象
	 * 
	 * @param id
	 * @return
	 */
	public JbSafetime get(String id);

	/**
	 * 修改JbSafetime
	 * 
	 * @param jbSafetime
	 */
	public void edit(JbSafetime jbSafetime);

	/**
	 * 删除JbSafetime
	 * 
	 * @param id
	 */
	public void delete(String id);

}

package jb.service;

import jb.pageModel.JbRegularCheck;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface JbRegularCheckServiceI {

	/**
	 * 获取JbRegularCheck数据表格
	 * 
	 * @param jbRegularCheck
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(JbRegularCheck jbRegularCheck, PageHelper ph);

	/**
	 * 添加JbRegularCheck
	 * 
	 * @param jbRegularCheck
	 */
	public void add(JbRegularCheck jbRegularCheck);

	/**
	 * 获得JbRegularCheck对象
	 * 
	 * @param id
	 * @return
	 */
	public JbRegularCheck get(String id);

	/**
	 * 修改JbRegularCheck
	 * 
	 * @param jbRegularCheck
	 */
	public void edit(JbRegularCheck jbRegularCheck);

	/**
	 * 删除JbRegularCheck
	 * 
	 * @param id
	 */
	public void delete(String id);

}

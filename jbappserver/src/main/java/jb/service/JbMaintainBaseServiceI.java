package jb.service;

import jb.pageModel.JbMaintainBase;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface JbMaintainBaseServiceI {

	/**
	 * 获取JbMaintainBase数据表格
	 * 
	 * @param jbMaintainBase
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(JbMaintainBase jbMaintainBase, PageHelper ph);

	/**
	 * 添加JbMaintainBase
	 * 
	 * @param jbMaintainBase
	 */
	public void add(JbMaintainBase jbMaintainBase);

	/**
	 * 获得JbMaintainBase对象
	 * 
	 * @param id
	 * @return
	 */
	public JbMaintainBase get(String id);
	
	/**
	 * 获得JbMaintainBase对象
	 * 
	 * @param id
	 * @return
	 */
	public JbMaintainBase getByAssetId(String id);

	/**
	 * 修改JbMaintainBase
	 * 
	 * @param jbMaintainBase
	 */
	public void edit(JbMaintainBase jbMaintainBase);

	/**
	 * 删除JbMaintainBase
	 * 
	 * @param id
	 */
	public void delete(String id);

}

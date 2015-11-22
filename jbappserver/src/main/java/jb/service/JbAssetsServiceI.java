package jb.service;

import jb.pageModel.JbAssets;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface JbAssetsServiceI {

	/**
	 * 获取JbAssets数据表格
	 * 
	 * @param jbAssets
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(JbAssets jbAssets, PageHelper ph);

	/**
	 * 添加JbAssets
	 * 
	 * @param jbAssets
	 */
	public void add(JbAssets jbAssets);

	/**
	 * 获得JbAssets对象
	 * 
	 * @param id
	 * @return
	 */
	public JbAssets get(String id);
	
	
	/**
	 * @param id
	 * @return
	 */
	public JbAssets getByUid(String id);
	

	/**
	 * 修改JbAssets
	 * 
	 * @param jbAssets
	 */
	public void edit(JbAssets jbAssets);

	/**
	 * 删除JbAssets
	 * 
	 * @param id
	 */
	public void delete(String id);

}

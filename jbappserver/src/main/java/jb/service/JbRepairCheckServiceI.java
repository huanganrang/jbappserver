package jb.service;

import jb.pageModel.JbRepairCheck;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface JbRepairCheckServiceI {

	/**
	 * 获取JbRepairCheck数据表格
	 * 
	 * @param jbRepairCheck
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(JbRepairCheck jbRepairCheck, PageHelper ph);

	/**
	 * 添加JbRepairCheck
	 * 
	 * @param jbRepairCheck
	 */
	public void add(JbRepairCheck jbRepairCheck);

	/**
	 * 获得JbRepairCheck对象
	 * 
	 * @param id
	 * @return
	 */
	public JbRepairCheck get(String id);

	/**
	 * 修改JbRepairCheck
	 * 
	 * @param jbRepairCheck
	 */
	public void edit(JbRepairCheck jbRepairCheck);

	/**
	 * 删除JbRepairCheck
	 * 
	 * @param id
	 */
	public void delete(String id);

}

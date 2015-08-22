package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.JbRegularCheckDaoI;
import jb.model.TjbRegularCheck;
import jb.pageModel.DataGrid;
import jb.pageModel.JbRegularCheck;
import jb.pageModel.PageHelper;
import jb.service.JbRegularCheckServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JbRegularCheckServiceImpl extends BaseServiceImpl<JbRegularCheck> implements JbRegularCheckServiceI {

	@Autowired
	private JbRegularCheckDaoI jbRegularCheckDao;

	@Override
	public DataGrid dataGrid(JbRegularCheck jbRegularCheck, PageHelper ph) {
		List<JbRegularCheck> ol = new ArrayList<JbRegularCheck>();
		String hql = " from TjbRegularCheck t ";
		DataGrid dg = dataGridQuery(hql, ph, jbRegularCheck, jbRegularCheckDao);
		@SuppressWarnings("unchecked")
		List<TjbRegularCheck> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TjbRegularCheck t : l) {
				JbRegularCheck o = new JbRegularCheck();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(JbRegularCheck jbRegularCheck, Map<String, Object> params) {
		String whereHql = "";	
		if (jbRegularCheck != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(jbRegularCheck.getMaintainId())) {
				whereHql += " and t.maintainId = :maintainId";
				params.put("maintainId", jbRegularCheck.getMaintainId());
			}		
			if (!F.empty(jbRegularCheck.getCheckOrder())) {
				whereHql += " and t.checkOrder = :checkOrder";
				params.put("checkOrder", jbRegularCheck.getCheckOrder());
			}		
			if (!F.empty(jbRegularCheck.getCheckPeople())) {
				whereHql += " and t.checkPeople = :checkPeople";
				params.put("checkPeople", jbRegularCheck.getCheckPeople());
			}		
			if (!F.empty(jbRegularCheck.getCheckPhone())) {
				whereHql += " and t.checkPhone = :checkPhone";
				params.put("checkPhone", jbRegularCheck.getCheckPhone());
			}		
			if (!F.empty(jbRegularCheck.getSummary())) {
				whereHql += " and t.summary = :summary";
				params.put("summary", jbRegularCheck.getSummary());
			}		
			if (!F.empty(jbRegularCheck.getScanFile())) {
				whereHql += " and t.scanFile = :scanFile";
				params.put("scanFile", jbRegularCheck.getScanFile());
			}		
			if (!F.empty(jbRegularCheck.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", jbRegularCheck.getRemark());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(JbRegularCheck jbRegularCheck) {
		TjbRegularCheck t = new TjbRegularCheck();
		BeanUtils.copyProperties(jbRegularCheck, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		jbRegularCheckDao.save(t);
	}

	@Override
	public JbRegularCheck get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TjbRegularCheck t = jbRegularCheckDao.get("from TjbRegularCheck t  where t.id = :id", params);
		JbRegularCheck o = new JbRegularCheck();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(JbRegularCheck jbRegularCheck) {
		TjbRegularCheck t = jbRegularCheckDao.get(TjbRegularCheck.class, jbRegularCheck.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(jbRegularCheck, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		jbRegularCheckDao.delete(jbRegularCheckDao.get(TjbRegularCheck.class, id));
	}

}

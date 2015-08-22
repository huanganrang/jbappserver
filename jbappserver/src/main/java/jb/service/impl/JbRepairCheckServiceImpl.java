package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.JbRepairCheckDaoI;
import jb.model.TjbRepairCheck;
import jb.pageModel.DataGrid;
import jb.pageModel.JbRepairCheck;
import jb.pageModel.PageHelper;
import jb.service.JbRepairCheckServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JbRepairCheckServiceImpl extends BaseServiceImpl<JbRepairCheck> implements JbRepairCheckServiceI {

	@Autowired
	private JbRepairCheckDaoI jbRepairCheckDao;

	@Override
	public DataGrid dataGrid(JbRepairCheck jbRepairCheck, PageHelper ph) {
		List<JbRepairCheck> ol = new ArrayList<JbRepairCheck>();
		String hql = " from TjbRepairCheck t ";
		DataGrid dg = dataGridQuery(hql, ph, jbRepairCheck, jbRepairCheckDao);
		@SuppressWarnings("unchecked")
		List<TjbRepairCheck> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TjbRepairCheck t : l) {
				JbRepairCheck o = new JbRepairCheck();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(JbRepairCheck jbRepairCheck, Map<String, Object> params) {
		String whereHql = "";	
		if (jbRepairCheck != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(jbRepairCheck.getMaintainId())) {
				whereHql += " and t.maintainId = :maintainId";
				params.put("maintainId", jbRepairCheck.getMaintainId());
			}		
			if (!F.empty(jbRepairCheck.getRepairOrder())) {
				whereHql += " and t.repairOrder = :repairOrder";
				params.put("repairOrder", jbRepairCheck.getRepairOrder());
			}		
			if (!F.empty(jbRepairCheck.getRepairPeople())) {
				whereHql += " and t.repairPeople = :repairPeople";
				params.put("repairPeople", jbRepairCheck.getRepairPeople());
			}		
			if (!F.empty(jbRepairCheck.getRepairPhone())) {
				whereHql += " and t.repairPhone = :repairPhone";
				params.put("repairPhone", jbRepairCheck.getRepairPhone());
			}		
			if (!F.empty(jbRepairCheck.getSummary())) {
				whereHql += " and t.summary = :summary";
				params.put("summary", jbRepairCheck.getSummary());
			}		
			if (!F.empty(jbRepairCheck.getScanFile())) {
				whereHql += " and t.scanFile = :scanFile";
				params.put("scanFile", jbRepairCheck.getScanFile());
			}		
			if (!F.empty(jbRepairCheck.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", jbRepairCheck.getRemark());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(JbRepairCheck jbRepairCheck) {
		TjbRepairCheck t = new TjbRepairCheck();
		BeanUtils.copyProperties(jbRepairCheck, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		jbRepairCheckDao.save(t);
	}

	@Override
	public JbRepairCheck get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TjbRepairCheck t = jbRepairCheckDao.get("from TjbRepairCheck t  where t.id = :id", params);
		JbRepairCheck o = new JbRepairCheck();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(JbRepairCheck jbRepairCheck) {
		TjbRepairCheck t = jbRepairCheckDao.get(TjbRepairCheck.class, jbRepairCheck.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(jbRepairCheck, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		jbRepairCheckDao.delete(jbRepairCheckDao.get(TjbRepairCheck.class, id));
	}

}

package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.JbSafetimeDaoI;
import jb.model.TjbSafetime;
import jb.pageModel.JbSafetime;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.JbSafetimeServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class JbSafetimeServiceImpl extends BaseServiceImpl<JbSafetime> implements JbSafetimeServiceI {

	@Autowired
	private JbSafetimeDaoI jbSafetimeDao;

	@Override
	public DataGrid dataGrid(JbSafetime jbSafetime, PageHelper ph) {
		List<JbSafetime> ol = new ArrayList<JbSafetime>();
		String hql = " from TjbSafetime t ";
		DataGrid dg = dataGridQuery(hql, ph, jbSafetime, jbSafetimeDao);
		@SuppressWarnings("unchecked")
		List<TjbSafetime> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TjbSafetime t : l) {
				JbSafetime o = new JbSafetime();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(JbSafetime jbSafetime, Map<String, Object> params) {
		String whereHql = "";	
		if (jbSafetime != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(jbSafetime.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", jbSafetime.getStatus());
			}		
			if (!F.empty(jbSafetime.getUid())) {
				whereHql += " and t.uid = :uid";
				params.put("uid", jbSafetime.getUid());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(JbSafetime jbSafetime) {
		TjbSafetime t = new TjbSafetime();
		BeanUtils.copyProperties(jbSafetime, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		jbSafetimeDao.save(t);
	}

	@Override
	public JbSafetime get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TjbSafetime t = jbSafetimeDao.get("from TjbSafetime t  where t.id = :id", params);
		JbSafetime o = new JbSafetime();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(JbSafetime jbSafetime) {
		TjbSafetime t = jbSafetimeDao.get(TjbSafetime.class, jbSafetime.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(jbSafetime, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		jbSafetimeDao.delete(jbSafetimeDao.get(TjbSafetime.class, id));
	}

}

package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.JbMaintainBaseDaoI;
import jb.model.TjbMaintainBase;
import jb.pageModel.DataGrid;
import jb.pageModel.JbMaintainBase;
import jb.pageModel.PageHelper;
import jb.service.JbMaintainBaseServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JbMaintainBaseServiceImpl extends BaseServiceImpl<JbMaintainBase> implements JbMaintainBaseServiceI {

	@Autowired
	private JbMaintainBaseDaoI jbMaintainBaseDao;

	@Override
	public DataGrid dataGrid(JbMaintainBase jbMaintainBase, PageHelper ph) {
		List<JbMaintainBase> ol = new ArrayList<JbMaintainBase>();
		String hql = " from TjbMaintainBase t ";
		DataGrid dg = dataGridQuery(hql, ph, jbMaintainBase, jbMaintainBaseDao);
		@SuppressWarnings("unchecked")
		List<TjbMaintainBase> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TjbMaintainBase t : l) {
				JbMaintainBase o = new JbMaintainBase();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(JbMaintainBase jbMaintainBase, Map<String, Object> params) {
		String whereHql = "";	
		if (jbMaintainBase != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(jbMaintainBase.getAssetId())) {
				whereHql += " and t.assetId = :assetId";
				params.put("assetId", jbMaintainBase.getAssetId());
			}		
			if (!F.empty(jbMaintainBase.getMaintainUnit())) {
				whereHql += " and t.maintainUnit = :maintainUnit";
				params.put("maintainUnit", jbMaintainBase.getMaintainUnit());
			}		
			if (!F.empty(jbMaintainBase.getTelphone())) {
				whereHql += " and t.telphone = :telphone";
				params.put("telphone", jbMaintainBase.getTelphone());
			}		
			if (!F.empty(jbMaintainBase.getCheckCycle())) {
				whereHql += " and t.checkCycle = :checkCycle";
				params.put("checkCycle", jbMaintainBase.getCheckCycle());
			}		
			if (!F.empty(jbMaintainBase.getCheckMode())) {
				whereHql += " and t.checkMode = :checkMode";
				params.put("checkMode", jbMaintainBase.getCheckMode());
			}		
			if (!F.empty(jbMaintainBase.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", jbMaintainBase.getRemark());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(JbMaintainBase jbMaintainBase) {
		TjbMaintainBase t = new TjbMaintainBase();
		String uuid = UUID.randomUUID().toString();
		jbMaintainBase.setId(uuid);
		BeanUtils.copyProperties(jbMaintainBase, t);
		t.setId(uuid);
		//t.setCreatedatetime(new Date());
		jbMaintainBaseDao.save(t);
	}

	@Override
	public JbMaintainBase get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TjbMaintainBase t = jbMaintainBaseDao.get("from TjbMaintainBase t  where t.id = :id", params);		
		JbMaintainBase o = new JbMaintainBase();
		if(t!=null)
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public JbMaintainBase getByAssetId(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TjbMaintainBase t = jbMaintainBaseDao.get("from TjbMaintainBase t  where t.assetId = :id", params);
		JbMaintainBase o = new JbMaintainBase();
		if(t!=null)
		BeanUtils.copyProperties(t, o);
		return o;
	}
	@Override
	public void edit(JbMaintainBase jbMaintainBase) {
		TjbMaintainBase t = jbMaintainBaseDao.get(TjbMaintainBase.class, jbMaintainBase.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(jbMaintainBase, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		jbMaintainBaseDao.delete(jbMaintainBaseDao.get(TjbMaintainBase.class, id));
	}

}

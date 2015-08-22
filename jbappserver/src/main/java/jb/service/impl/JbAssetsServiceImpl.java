package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.JbAssetsDaoI;
import jb.model.TjbAssets;
import jb.pageModel.JbAssets;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.JbAssetsServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

@Service
public class JbAssetsServiceImpl extends BaseServiceImpl<JbAssets> implements JbAssetsServiceI {

	@Autowired
	private JbAssetsDaoI jbAssetsDao;

	@Override
	public DataGrid dataGrid(JbAssets jbAssets, PageHelper ph) {
		List<JbAssets> ol = new ArrayList<JbAssets>();
		String hql = " from TjbAssets t ";
		DataGrid dg = dataGridQuery(hql, ph, jbAssets, jbAssetsDao);
		@SuppressWarnings("unchecked")
		List<TjbAssets> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TjbAssets t : l) {
				JbAssets o = new JbAssets();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(JbAssets jbAssets, Map<String, Object> params) {
		String whereHql = "";	
		if (jbAssets != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(jbAssets.getAssetNumber())) {
				whereHql += " and t.assetNumber = :assetNumber";
				params.put("assetNumber", jbAssets.getAssetNumber());
			}		
			if (!F.empty(jbAssets.getFirstCategory())) {
				whereHql += " and t.firstCategory = :firstCategory";
				params.put("firstCategory", jbAssets.getFirstCategory());
			}		
			if (!F.empty(jbAssets.getSecondCategory())) {
				whereHql += " and t.secondCategory = :secondCategory";
				params.put("secondCategory", jbAssets.getSecondCategory());
			}		
			if (!F.empty(jbAssets.getDescription())) {
				whereHql += " and t.description = :description";
				params.put("description", jbAssets.getDescription());
			}		
			if (!F.empty(jbAssets.getFactory())) {
				whereHql += " and t.factory = :factory";
				params.put("factory", jbAssets.getFactory());
			}		
			if (!F.empty(jbAssets.getAssetType())) {
				whereHql += " and t.assetType = :assetType";
				params.put("assetType", jbAssets.getAssetType());
			}		
			if (!F.empty(jbAssets.getSerialNumber())) {
				whereHql += " and t.serialNumber = :serialNumber";
				params.put("serialNumber", jbAssets.getSerialNumber());
			}		
			if (!F.empty(jbAssets.getLocation())) {
				whereHql += " and t.location = :location";
				params.put("location", jbAssets.getLocation());
			}		
			if (!F.empty(jbAssets.getDeptId())) {
				whereHql += " and t.deptId = :deptId";
				params.put("deptId", jbAssets.getDeptId());
			}		
			if (!F.empty(jbAssets.getPrincipal())) {
				whereHql += " and t.principal = :principal";
				params.put("principal", jbAssets.getPrincipal());
			}		
			if (!F.empty(jbAssets.getSupplier())) {
				whereHql += " and t.supplier = :supplier";
				params.put("supplier", jbAssets.getSupplier());
			}		
			if (!F.empty(jbAssets.getSupplierPhone())) {
				whereHql += " and t.supplierPhone = :supplierPhone";
				params.put("supplierPhone", jbAssets.getSupplierPhone());
			}		
			if (!F.empty(jbAssets.getMeasure())) {
				whereHql += " and t.measure = :measure";
				params.put("measure", jbAssets.getMeasure());
			}		
				
			if (!F.empty(jbAssets.getIcon())) {
				whereHql += " and t.icon = :icon";
				params.put("icon", jbAssets.getIcon());
			}		
			if (!F.empty(jbAssets.getParentId())) {
				whereHql += " and t.parentId = :parentId";
				params.put("parentId", jbAssets.getParentId());
			}		
			if (!F.empty(jbAssets.getRoomAreaId())) {
				whereHql += " and t.roomAreaId = :roomAreaId";
				params.put("roomAreaId", jbAssets.getRoomAreaId());
			}		
			if (!F.empty(jbAssets.getScope())) {
				whereHql += " and t.scope = :scope";
				params.put("scope", jbAssets.getScope());
			}		
				
			if (!F.empty(jbAssets.getUid())) {
				whereHql += " and t.uid = :uid";
				params.put("uid", jbAssets.getUid());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(JbAssets jbAssets) {
		TjbAssets t = new TjbAssets();
		BeanUtils.copyProperties(jbAssets, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		jbAssetsDao.save(t);
	}

	@Override
	public JbAssets get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TjbAssets t = jbAssetsDao.get("from TjbAssets t  where t.id = :id", params);
		JbAssets o = new JbAssets();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public JbAssets getByUid(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TjbAssets t = jbAssetsDao.get("from TjbAssets t  where t.uid = :id", params);
		JbAssets o = new JbAssets();
		if(t!=null)
		BeanUtils.copyProperties(t, o);
		return o;
	}
	@Override
	public void edit(JbAssets jbAssets) {
		TjbAssets t = jbAssetsDao.get(TjbAssets.class, jbAssets.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(jbAssets, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		jbAssetsDao.delete(jbAssetsDao.get(TjbAssets.class, id));
	}

}

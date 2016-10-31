/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.gen.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.gen.entity.GenTableColumn;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import com.thinkgem.jeesite.modules.gen.dao.GenDataBaseDictDao;
import com.thinkgem.jeesite.modules.gen.dao.GenTableColumnDao;
import com.thinkgem.jeesite.modules.gen.dao.GenTableDao;

/**
 * 业务表配置Service
 * @author LiuBJ
 * @version 2016-06-07
 */
@Service
@Transactional(readOnly = true)
public class GenTableService extends BaseService {

	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	@Autowired
	private GenDataBaseDictDao genDataBaseDictDao;
	
	public GenTable get(String id) {
		return genTableDao.get(id);
	}
	
	public Page<GenTable> find(Page<GenTable> page, GenTable genTable) {
		DetachedCriteria dc = genTableDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(genTable.getName())){
			dc.add(Restrictions.like("name", "%"+genTable.getName()+"%"));
		}
		dc.add(Restrictions.eq(GenTable.FIELD_DEL_FLAG, GenTable.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return genTableDao.find(page, dc);
	}
	
	public List<GenTable> findAll() {
		return genTableDao.findAll();
	}
	/**
	 * 验证表名是否可用，如果已存在，则返回false
	 * @param genTable
	 * @return
	 */
	public boolean checkTableName(String tableName){
		if (StringUtils.isBlank(tableName)){
			return true;
		}
		//GenTable genTable = new GenTable();
		//genTable.setName(tableName);
		List<GenTable> list = genTableDao.findBySql("select * from gen_table where name='"+tableName+"'");
		return list.size() == 0;
	}	
	/**
	 * 获取物理数据表列表
	 * @param genTable
	 * @return
	 */
	public List<GenTable> findTableListFormDb(GenTable genTable){
		return genDataBaseDictDao.findTableList(genTable);	
	}
	/**
	 * 获取物理数据表列表
	 * @param genTable
	 * @return
	 */
	public GenTable getTableFormDb(GenTable genTable){
		// 如果有表名，则获取物理表
		if (StringUtils.isNotBlank(genTable.getName())){
			
			List<GenTable> list = genDataBaseDictDao.findTableList(genTable);
			if (list.size() > 0){
				
				// 如果是新增，初始化表属性
				if (StringUtils.isBlank(genTable.getId())){
					genTable = list.get(0);
					// 设置字段说明
					if (StringUtils.isBlank(genTable.getComments())){
						genTable.setComments(genTable.getName());
					}
					genTable.setClassName(StringUtils.toCapitalizeCamelCase(genTable.getName()));
				}
				
				// 添加新列
				List<GenTableColumn> columnList = genDataBaseDictDao.findTableColumnList(genTable);
				for (GenTableColumn column : columnList){
					boolean b = false;
					for (GenTableColumn e : genTable.getColumnList()){
						if (e.getName().equals(column.getName())){
							b = true;
						}
					}
					if (!b){
						genTable.getColumnList().add(column);
					}
				}
				
				// 删除已删除的列
				for (GenTableColumn e : genTable.getColumnList()){
					boolean b = false;
					for (GenTableColumn column : columnList){
						if (column.getName().equals(e.getName())){
							b = true;
						}
					}
					if (!b){
						e.setDelFlag(GenTableColumn.DEL_FLAG_DELETE);
					}
				}
				
				// 获取主键
				genTable.setPkList(genDataBaseDictDao.findTablePK(genTable));
				
				// 初始化列属性字段
				GenUtils.initColumnField(genTable);
				
			}
		}
		return genTable;
	}	
	@Transactional(readOnly = false)
	public void save(GenTable genTable) {
		if (StringUtils.isBlank(genTable.getId())){
			genTable.prePersist();
			//genTableDao.insert(genTable);
		}else{
			genTable.preUpdate();
		}
		genTableDao.save(genTable);
		// 保存列
		for (GenTableColumn column : genTable.getColumnList()){
			column.setGenTable(genTable);
			if (StringUtils.isBlank(column.getId())){
				column.prePersist();
				//genTableColumnDao.insert(column);
			}else{
				column.preUpdate();
			}
			genTableColumnDao.save(column);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		genTableDao.deleteById(id);
		genTableColumnDao.deleteByGenTableId(id);
	}
	
}

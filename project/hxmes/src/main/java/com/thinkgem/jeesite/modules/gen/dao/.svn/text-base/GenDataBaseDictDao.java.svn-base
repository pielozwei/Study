package com.thinkgem.jeesite.modules.gen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.gen.entity.GenTableColumn;
import com.thinkgem.jeesite.modules.sys.entity.User;
/**
 * 业务表字段DAO接口
 * @author Admin
 * @version 2013-10-15
 */
@Repository
public class GenDataBaseDictDao extends BaseDao<GenTableColumn> {
	/**
	 * 查询表列表
	 * @param genTable
	 * @return
	 */
	public List<GenTable> findTableList(GenTable genTable)
	{
		List<GenTable> genTableList=Lists.newArrayList();
		String sqlString = "select t.table_name as name,c.comments as comments from user_tables t,user_tab_comments c where t.table_name = c.table_name";
		String tableName=genTable.getName();
		if(tableName!=null && tableName.trim()!="")
			sqlString+=" and t.table_name ='"+tableName.toUpperCase()+"'";
		List<Object[]> objList = findBySql(sqlString);
		for (Object o[] : objList) {
			GenTable newGenTable=new GenTable();
			newGenTable.setName(o[0].toString());
			if(o[1]!=null)
				newGenTable.setComments(o[1].toString());
			genTableList.add(newGenTable);
		}
		
		return genTableList;
	}

	/**
	 * 获取数据表字段
	 * @param genTable
	 * @return
	 */
	public List<GenTableColumn> findTableColumnList(GenTable genTable)
	{
		List<GenTableColumn> genTableColumList=Lists.newArrayList();
		String sqlString = "select t.COLUMN_NAME as name,(CASE WHEN t.NULLABLE = 'Y' THEN '1' ELSE '0' END) AS isNull,"
				+ "(t.COLUMN_ID * 10) AS sort,c.COMMENTS AS comments,"
				+ "decode(t.DATA_TYPE,'DATE',t.DATA_TYPE || '(' || t.DATA_LENGTH || ')',"
				+ "'VARCHAR2', t.DATA_TYPE || '(' || t.DATA_LENGTH || ')',"
				+ "'VARCHAR', t.DATA_TYPE || '(' || t.DATA_LENGTH || ')',"
				+ "'NVARCHAR2', t.DATA_TYPE || '(' || t.DATA_LENGTH/2 || ')',"
				+ "'CHAR', t.DATA_TYPE || '(' || t.DATA_LENGTH || ')',"
				+ "'NUMBER',t.DATA_TYPE || (nvl2(t.DATA_PRECISION,nvl2(decode(t.DATA_SCALE,0,null,t.DATA_SCALE),"
				+ "'(' || t.DATA_PRECISION || ',' || t.DATA_SCALE || ')', "
				+ "'(' || t.DATA_PRECISION || ')'),'(18)')),t.DATA_TYPE) AS jdbcType"
				+ " FROM user_tab_columns t, user_col_comments c"
				+ " WHERE t.TABLE_NAME = c.table_name"
				+ " AND t.COLUMN_NAME = c.column_name";
		String tableName=genTable.getName();
		if(tableName!=null && tableName.trim()!="")
			sqlString+=" and t.table_name ='"+tableName.toUpperCase()+"'";
		List<Object[]> objList = findBySql(sqlString);
		for (Object o[] : objList) {
			GenTableColumn newGenTablColum=new GenTableColumn();
			newGenTablColum.setName(o[0].toString());
			if(o[1]!=null)
				newGenTablColum.setIsNull(o[1].toString());
			if(o[2]!=null)
				newGenTablColum.setSort(Integer.valueOf(o[2].toString()));
			if(o[3]!=null)
				newGenTablColum.setComments(o[3].toString());
			if(o[4]!=null)
				newGenTablColum.setJdbcType(o[4].toString());
			genTableColumList.add(newGenTablColum);
		}
		
		return genTableColumList;
}
	
	/**
	 * 获取数据表主键
	 * @param genTable
	 * @return
	 */
	public List<String> findTablePK(GenTable genTable)
	{
		List<String> genTablePK=Lists.newArrayList();
		String sqlString = "select lower(cu.COLUMN_NAME) AS columnName FROM user_cons_columns cu, user_constraints au"
				+ " WHERE cu.constraint_name = au.constraint_name"
				+ " AND au.constraint_type = 'P'";
		String tableName=genTable.getName();
		if(tableName!=null && tableName.trim()!="")
			sqlString+=" and au.table_name ='"+tableName.toUpperCase()+"'";
		List<Object[]> objList = findBySql(sqlString);
		if(objList.size()>0){
			Object o=objList.get(0);
			genTablePK.add(o.toString());
		}
				
		return genTablePK;
	}
	
}

package me.daoke.mileage.common;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作数据库基础类
 * @author wangzp
 *
 */
public class BaseDao extends SqlSessionDaoSupport {
	
	/**
	 * 根据sql文查询一行记录
	 * @param <T>
	 * @param statement
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:17:04 PM
	 */
	public <T> T selectOne(String statement) {
		return (T)getSqlSession().selectOne(statement);
	}

	public <T> T selectOne(String statement, Object parameter){
		return (T)getSqlSession().selectOne(statement, parameter);
	}
	
	/**
	 * 查询多行记录
	 * @param statement
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:36:16 PM
	 */
	protected <T> List<T> selectList(String statement){
		return getSqlSession().selectList(statement);
	}
	
	protected <T> List<T> selectList(String statement, Object parameter){
		return (List<T>)getSqlSession().selectList(statement, parameter);
	}
	
	protected <T> List<T> selectList(String statement, Object parameter, RowBounds rowBounds){
		return  (List<T>)getSqlSession().selectList(statement, parameter, rowBounds);
	}
	
	/**
	 * 返回值封装为map类型
	 * @param <K>
	 * @param <V>
	 * @param statement
	 * @param mapKey
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:36:42 PM
	 */
	protected <K, V> Map<K, V> selectMap(String statement, String mapKey){
		return (Map<K, V>)getSqlSession().selectMap(statement, mapKey);
	}

	protected <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey){
		return (Map<K, V>)getSqlSession().selectMap(statement, parameter, mapKey);
	}
	
	protected <K, V> Map<K, V> selectMap(String statement, Object parameter,
			String mapKey, RowBounds rowBounds){
		return (Map<K, V>)getSqlSession().selectMap(statement, parameter, mapKey, rowBounds);
	}
	
	/**
	 * 插入记录
	 * @param statement
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:18:09 PM
	 */
	protected int insert(String statement){
		return getSqlSession().insert(statement);
	}
	
	/**
	 * 插入记录
	 * @param statement
	 * @param parameter
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:18:22 PM
	 */
	protected int insert(String statement, Object parameter){
		return getSqlSession().insert(statement, parameter);
	}
	
	/**
	 * 修改
	 * @param statement
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:19:06 PM
	 */
	protected int update(String statement){
		return getSqlSession().update(statement);
	}

	protected int update(String statement, Object parameter){
		return getSqlSession().update(statement, parameter);
	}
	
	/**
	 * 删除
	 * @param statement
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:19:35 PM
	 */
	protected int delete(String statement){
		return getSqlSession().delete(statement);
	}

	protected int delete(String statement, Object parameter){
		return getSqlSession().delete(statement, parameter);
	}

	/**
	 * 提交事务
	 * 
	 * @author wangzp
	 * @date May 16, 2014 4:19:50 PM
	 */
	protected void commit(){
		getSqlSession().commit();
	}

	protected void commit(boolean force){
		getSqlSession().commit(force);
	}
	
	/**
	 * 回滚事务
	 * @author wangzp
	 * @date May 16, 2014 4:20:01 PM
	 */
	protected void rollback(){
		getSqlSession().rollback();	
	}

	protected void rollback(boolean force){
		getSqlSession().rollback(force);
	}

	protected List<BatchResult> flushStatements(){
		return getSqlSession().flushStatements();
	}
	
	/**
	 * 关闭sqlsession
	 * @author wangzp
	 * @date May 16, 2014 4:20:33 PM
	 */
	protected void close(){
		getSqlSession().close();
	}
	
	/**
	 * 清缓存
	 * @author wangzp
	 * @date May 16, 2014 4:20:45 PM
	 */
	protected void clearCache(){
		getSqlSession().clearCache();
	}
	
	/**
	 * 获取sqlsession配置信息
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:21:07 PM
	 */
	protected Configuration getConfiguration(){
		return getSqlSession().getConfiguration();
	}
	
	/**
	 * 检索映射
	 * @param <T>
	 * @param type
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:23:58 PM
	 */
	protected <T> T getMapper(Class<T> type){
		return (T)getSqlSession().getMapper(type);
	}
	
	/**
	 * 获取内部数据库连接
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:21:40 PM
	 */
	protected Connection getConnection(){
		return getSqlSession().getConnection();
	}
	
	/**
	 * 分页
	 * @param sqlstatement sql文
	 * @param param 参数
	 * @param toPage 页码
	 * @param pageSize 数量
	 * @return
	 * @author wangzp
	 * @date May 16, 2014 4:31:23 PM
	 */
	@SuppressWarnings("unchecked")
	public <T> PageList<T> queryForPageList(String sqlstatement,Map<String,Object> param,int toPage,int pageSize){
		
		if(toPage<1){
			toPage=1;
		}
		if(pageSize<1){
			pageSize=10;
		}
		
		Integer totalSize = (Integer) selectOne(sqlstatement+"Count", param);
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		param.put("gotopage", toPage);
		param.put("pagesize", pageSize);
		
		
		PageList pageList = new PageList(toPage, totalSize,pageSize, selectList(sqlstatement, param));
		
		return pageList;
	}

}

package com.wgjc.base.service;
/** 
 * @ClassName: BaseService
 * @Description: 业务层基础类，包含CRUD基础操作接口
 * @author hc
 * @date 2019年7月22日上午10:11:15
 */
public interface BaseService<T> {
	/**
	 * @Title: save  
	 * @Description: 保存对象
	 * @param record
	 * @return
	 */
	public int save(T record);
	
	/**
	 * @Title: update  
	 * @Description: 更新对象
	 * @param record
	 * @return
	 */
	public int update(T record);
	
	/**
	 * @Title: delete  
	 * @Description: 通过主键删除对象
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * @Title: getById  
	 * @Description: 通过主键获取对象
	 * @param id
	 * @return
	 */
	public T getById(String id);
}

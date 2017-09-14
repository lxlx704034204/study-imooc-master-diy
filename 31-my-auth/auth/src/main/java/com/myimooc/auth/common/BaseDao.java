package com.myimooc.auth.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Dao基类（数据访问对象）
 * @author zc on 2017-08-19
 */
public abstract class BaseDao {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
}

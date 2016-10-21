package org.demo.logger.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.demo.logger.entity.User;

public interface UserDao {

	public User login(@Param("username")String username, @Param("password")String password);

	public List<User> getUserList();
	
	public int delete(List<Integer> idList);

}

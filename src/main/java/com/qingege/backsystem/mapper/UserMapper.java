package com.qingege.backsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingege.backsystem.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

//@Mapper
public interface UserMapper extends BaseMapper<User> {
    //由于使用了mybatis-plus，自己不用写sql语句
//    @Select("SELECT * FROM sys_user")
//    List<User> queryAll();
//
//    @Insert("INSERT INTO sys_user(username,password,nickname,email,phone,address) VALUES(#{username},#{password},#{nickname},#{email},#{phone},#{address})")
//    int insert(User user);
//
//    int update(User user);
//
//    @Delete("DELETE from sys_user  where id = #{id} ")
//    Integer deleteById(@Param("id") Integer id);  //@Param("id")表示告诉框架从url路径中获取的是id
//
//    @Select("SELECT * FROM sys_user where username like #{username} limit #{PageNum},#{pageSize} ")
//    List<User> selectPage(Integer PageNum, Integer pageSize, String username);
//
//    @Select("select count(*) from sys_user where username like CONCAT('%',#{username},'%')")
//    Integer selectTotal(String username);
}

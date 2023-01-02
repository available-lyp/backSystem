package com.qingege.backsystem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingege.backsystem.entity.User;
import com.qingege.backsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    //实现了选择新增还是更改
    public boolean saveUser(User user) {
          return saveOrUpdate(user);
//        if (user.getId()==null){
//           return save(user);   //mybatis-plus提供的方法，表示插入数据
//        }else {
//           return updateById(user);
    }

//    @Autowired
//    private UserMapper userMapper;

/*  由于使用到了MybatisPlus，所以进行优化
public int save(User user){
        if (user.getId()==null){    //如果id为空的话调用insert方法实现新增,否则就是更新
           return userMapper.insert(user);
         }else {
           return userMapper.update(user);
        }
    }
*/
}

package com.qingege.backsystem.service.impl;

import com.qingege.backsystem.entity.User;
import com.qingege.backsystem.mapper.UserMapper;
import com.qingege.backsystem.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 骆爷
 * @since 2023-01-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

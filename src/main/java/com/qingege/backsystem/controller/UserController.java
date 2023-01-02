package com.qingege.backsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingege.backsystem.entity.User;
import com.qingege.backsystem.mapper.UserMapper;
import com.qingege.backsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
//   @Autowired
//   private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //新增、修改
    //新增、更新这两个操作都在这个controller里面
    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //查询所有
    @GetMapping
    public List<User> findAll() {
      //List<User> users = userMapper.queryAll();
        return userService.list();
    }

    //mybatis实现分页查询
    //@GetMapping("/page")  //@RequestParam 可以将前端获取的页数（pageNum）和页面数据大小（pageSize）分别映射到后端中
    //    public Map<String, Object> findAll(@RequestParam Integer pageNum,
    //                                       @RequestParam Integer pageSize,
    //                                       @RequestParam String username) {
    //        Integer PageNum = (pageNum - 1) * pageSize;
    //        username="%"+username+"%";    //为了实现模糊查询而进行拼接
    //        List<User> data = userMapper.selectPage(PageNum, pageSize,username);
    //        Integer total = userMapper.selectTotal(username);
    //        Map<String,Object> res = new HashMap<>();
    //        res.put("data",data);
    //        res.put("total",total);
    //        return res;
    //    }
    //mybatis-plus实现分页查询
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,  //默认username为空
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address){
        Page<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();  //queryWrapper就是实现模糊查询
        if (!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if (!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if (!"".equals(address)){
            queryWrapper.like("address",address);
        }
       // 实现倒叙的
       // queryWrapper.orderByDesc("id");
        return userService.page(page,queryWrapper);
    }

    //删除
    @DeleteMapping("/{id}")
    public  boolean delete(@PathVariable Integer id) {
        // return userMapper.delete(id);
        return userService.removeById(id);
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeByIds(ids);
    }
}

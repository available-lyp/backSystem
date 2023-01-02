package com.qingege.backsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "sys_user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)  //实现id的自增
    private Integer id;
    private String username;

    //由于这是密码，在前端就不用展示了，就是忽略掉这个，前端无法获取
    @JsonIgnore
    private String password;

    private String nickname;
    private String email;
    private String phone;
    private String address;
    private String create_time;
}

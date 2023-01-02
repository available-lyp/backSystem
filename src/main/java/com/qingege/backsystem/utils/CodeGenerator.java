package com.qingege.backsystem.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * mybatis-plus代码生成器
 * 建议使用代码生成器之前一定要进行代码的备份，因为你一般情况下之前写的代码会被替换掉，且不知道代码shi否存在问题
 * by 骆爷
 * @Since 2023-01-01
 */
public class CodeGenerator {
    public static void main(String[] args) {
        generator();
    }
    private static void generator() {
        //一定要注意这儿的数据库url，一个空格都会使其出错
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/backstage-management?serverTimezone=GMT%2b8", "root", "134340")
            .globalConfig(builder -> {
                builder.author("骆爷") // 设置作者
                    .enableSwagger() // 开启 swagger 模式
                    .fileOverride() // 覆盖已生成文件
                    .outputDir("E:\\workspace.IDEA\\SpringBoot-project\\SB-Vue-BackstageAgement\\backSystem\\src\\main\\java\\"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.qingege.backsystem") // 设置父包名
                        .moduleName("") // 设置父包模块名
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\workspace.IDEA\\SpringBoot-project\\SB-Vue-BackstageAgement\\backSystem\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
            })
            .strategyConfig(builder -> {
                builder.addInclude("sys_user") // 设置需要生成的表名
                        .addTablePrefix("t_", "sys_"); // 设置过滤表前缀.就是如果不忽略表名前缀的话，最后输出的就是sys_user,而不是user
            })
// .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }
}

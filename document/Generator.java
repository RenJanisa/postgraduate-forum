package com.banner.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;

/**
 * @author rjj
 * @date 2023/3/11 - 9:59
 */
public class Generator {

    // TODO 修改服务名以及数据表名
    private static final String SERVICE_NAME = "ucenter";

    //数据库账号
    private static final String DATA_SOURCE_USER_NAME  = "root";
    //数据库密码
    private static final String DATA_SOURCE_PASSWORD  = "RenJianJing1025!";
    //生成的表
    private static final String[] TABLE_NAMES = new String[]{
            "user",
            "user_info",
            "profession_categories_relation",
            "reply",
            "profession_categories",
            "profession",
            "post_info",
            "institution",
            "follow_relation",
            "experience",
            "comment",
            "borderline",
            "likes_collection",

    };

    // TODO 默认生成entity，需要生成DTO修改此变量
    // 一般情况下要先生成 DTO类 然后修改此参数再生成 PO 类。
    private static final Boolean IS_DTO = false;

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Velocity
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setFileOverride(true);
        //生成路径
        gc.setOutputDir(System.getProperty("user.dir") + "/postgraduateforum/src/main/java");
        gc.setAuthor("banner");
        gc.setOpen(false);
        gc.setSwagger2(false);
        gc.setServiceName("%sService");
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);

        if (IS_DTO) {
            gc.setSwagger2(true);
            gc.setEntityName("%sDTO");
        }
        mpg.setGlobalConfig(gc);

        // 数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://39.105.163.167:3306/postgraduate_forum"
                + "?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=utf8");
//		dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(DATA_SOURCE_USER_NAME);
        dsc.setPassword(DATA_SOURCE_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(SERVICE_NAME);
        pc.setParent("com.banner");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);


        // 设置模板
        TemplateConfig tc = new TemplateConfig();
        mpg.setTemplate(tc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TABLE_NAMES);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // Boolean类型字段是否移除is前缀处理
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        strategy.setRestControllerStyle(true);

        // 自动填充字段配置
        strategy.setTableFillList(Arrays.asList(
                new TableFill("create_time", FieldFill.INSERT),
                new TableFill("update_time", FieldFill.INSERT_UPDATE)
        ));
        mpg.setStrategy(strategy);

        mpg.execute();
    }

}

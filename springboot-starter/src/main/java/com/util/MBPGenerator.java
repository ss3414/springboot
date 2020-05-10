package com.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class MBPGenerator {

    public static void generate(
            String url, String username, String password,
            String projectPath, String projectName, String moduleName, String parent,
            String[] tableArray) {
        AutoGenerator autoGenerator = new AutoGenerator();

        /* 数据源 */
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        autoGenerator.setDataSource(dataSourceConfig);

        /* 全局配置 */
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/" + projectName + "/src/main/java");
        globalConfig.setAuthor("generator");
        globalConfig.setOpen(false);
        globalConfig.setActiveRecord(true);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
//        globalConfig.setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);

        /* 包配置 */
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(moduleName);
        packageConfig.setParent(parent);
        packageConfig.setEntity("model");
        packageConfig.setMapper("mapper");
        packageConfig.setXml("mapper");
        autoGenerator.setPackageInfo(packageConfig);

        /* 自定义配置 */
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("mapper.xml.ftl") { /* 模板要放在resources目录下 */
            @Override
            public String outputFile(TableInfo tableInfo) {
                /* 自定义输入文件名称 */
                return projectPath + "/" + projectName + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + ".xml";
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        autoGenerator.setCfg(injectionConfig);
        autoGenerator.setTemplate(new TemplateConfig().setXml(null));

        /* 策略配置 */
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setInclude(tableArray);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setTablePrefix(packageConfig.getModuleName() + "_");
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        autoGenerator.execute();
    }

}

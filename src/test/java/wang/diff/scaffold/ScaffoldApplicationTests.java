package wang.diff.scaffold;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class ScaffoldApplicationTests {

//	@Value("${spring.datasource.url}")
	@Value("${spring.datasource.dynamic.datasource.patient.url}")
	private String url;

//	@Value("${spring.datasource.username}")
	@Value("${spring.datasource.dynamic.datasource.patient.username}")
	private String username;


//	@Value("${spring.datasource.password}")
	@Value("${spring.datasource.dynamic.datasource.patient.password}")
	private String password;

	private final String projectPath = System.getProperty("user.dir");

	@Test
	void contextLoads() {
		System.out.println(url+"..."+username+"..."+password+ projectPath);
	}


	@Test
	void mybatisPlusCodeGenerator() {
		FastAutoGenerator.create(url, username, password)
				.globalConfig(builder -> {
					builder.author("airvip") // 设置作者
							// .enableSwagger() // 开启 swagger 模式
							.disableOpenDir() // 禁止打开输出目录
							.dateType(DateType.ONLY_DATE)
							.outputDir(projectPath+"\\src\\main\\resources\\generated"); // 指定输出目录
				})
				.packageConfig(builder -> {
					builder.parent("wang.diff.scaffold") // 设置父包名
							.moduleName("") // 设置父包模块名
							.mapper("dao")
							.pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "\\src\\main\\resources\\db\\mapper")); // 设置mapperXml生成路径
				})
				.strategyConfig(builder -> {
					builder.addInclude("t_patient") // 设置需要生成的表名
							.addTablePrefix("t_", "c_")// 设置过滤表前缀
							.entityBuilder()
							.enableLombok() // 开启 lombok 模型
							.enableFileOverride() // 覆盖已生成文件
							.enableRemoveIsPrefix() // 开启 Boolean 类型字段移除 is 前缀
							.enableColumnConstant() // 开启生成字段常量
							.enableTableFieldAnnotation() // 开启生成实体时生成字段注解
					;
				})
				.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();

	}

}

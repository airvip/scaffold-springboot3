# 工作流

1. 编写接口文档 yml
2. 通过接口文档生成代码
3. 通过 liquibase 管理数据表
4. 通过 mybatis 代码生成插件生成需要的代码

之所以采用这种工作流方式是方便框架基础版本升级，在升级的时候只需要修改很少的代码就可完成项目升级。尤其是从 springboot2 升级到 springboot3，可以充分体会这种工作流的优势。

# TODO
1. kafka stream
2. 监听redis过期key
3. redis延时队列

# 技术栈
1. springboot3
* [Start a new Spring Boot project](https://spring.io/quickstart)
* [Guides](https://spring.io/guides)
2. liquibase
* [Get Started with Liquibase](https://docs.liquibase.com/start/home.html?_ga=2.35877216.1519204558.1680801849-302714384.1679928665)
* [Liquibase Documentation](https://docs.liquibase.com/start/home.html?_ga=2.35877216.1519204558.1680801849-302714384.1679928665)
3. openapi + swagger
* [openapi plugins](https://openapi-generator.tech/docs/plugins/)
* [Documentation for the spring Generator](https://openapi-generator.tech/docs/generators/spring)
* [OpenAPI Guide](https://swagger.io/docs/specification/about/)
4. mybatis-plus
* [快速入门](https://baomidou.com/pages/24112f/)
* [代码生成器（新）](https://baomidou.com/pages/779a6e/#%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8)
* [代码生成器配置新](https://baomidou.com/pages/981406/#%E6%95%B0%E6%8D%AE%E5%BA%93%E9%85%8D%E7%BD%AE-datasourceconfig)
* [多数据源](https://baomidou.com/pages/a61e1b/#dynamic-datasource)
5. github.pagehelper
* [如何使用分页插件](https://pagehelper.github.io/docs/howtouse/)
6. easyexcel
* [EasyExcel](https://easyexcel.opensource.alibaba.com/docs/current/)
7. object store
* [s3](https://docs.aws.amazon.com/AmazonS3/latest/userguide/Welcome.html)
* [minio](http://www.minio.org.cn/docs/minio/container/index.html)
* [minio-java](https://min.io/docs/minio/linux/developers/java/minio-java.html)
8. redis
* [Redis support](https://docs.spring.io/spring-data/redis/docs/current/reference/html/#redis:setup)
9. kafka
* [Apache Kafka Support](https://docs.spring.io/spring-boot/docs/current/reference/html/messaging.html#messaging.kafka)
* [Apache Kafka Reference](https://docs.spring.io/spring-kafka/docs/current/reference/html/#reference)
10. jwt
* [jjwt](https://github.com/jwtk/jjwt)



# 打包运行

1. 运行
```
mvn spring-boot:run
```

2. 打包跳过所有测试

```
mvn clean package -DskipTests
```
# Ebook-SOA

本项目实现的是一个基于微服务架构的B2C网上书城，前台客户端包含登录、浏览商品、下单等操作，后台客户端包含商品管理、库存管理等。

后端采用微服务架构进行服务拆分，对各个业务模块分别进行开发，降低业务模块之间的耦合，从而便于开发维护和水平扩展。

## 进展
- Mysql
- Maven
- JPA
- Mybatis
- Redis
- Zuul
- Hystrix
- Swagger

## 架构设计
```
├── mvnw
├── mvnw.cmd
├── pom.xml                                         : maven 全局配置文件
├── 各个服务(category-service等)
|    └── src
|        ├── main
|        │   ├── java
|        │   │   └── com.cloud
|        │   │       ├── 。。。ServiceApplication.java     启动类
|        │   │       ├── config                          [Config]
|        |   |       │                                   : 配置类库
|        │   │       ├── controller                      [Controller] 
|        |   |       │                                   : 前端控制层
|        │   │       ├── domain/pojo                     [Domain]
|        |   |       │                                   : 实体类 : 
|        │   │       ├── dao                             [Dao]
|        |   |       │                                   : 数据接口访问层 
|        │   │       ├── util                            [Utilities]
|        |   |       │                                   : 工具类库
|       封装客户端请求，防止数据泄露，保证数据安全，不破坏实体类结构
|        │   └── resources
|        │       ├── application.yml                     : 服务配置文件
|        └── test 
|            └── java
|                └── com.cloud
|                    └── 。。。ServiceApplicationTests.java      : 单元测试目录
└── src
    ├── main
    │   ├── java
    │   │   └── com.cloud
    │   │       ├── CloudDiscoveryApplication.java    启动类
    │   └── resources
    │       ├── application.yml                     : 项目配置文件
    └── test 
        └── java
            └── com.cloud                            : 单元测试目录
```

## 说明
本项目涉及多个服务器，成本较大，因此在项目结束后，就停止了部署。因此提供了本项目正常运行时的视频，分别是```/video目录下的在线书城.mp4 和后台管理video.mp4。 ```

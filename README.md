# Spring Cloud Nacos Sample

这是一个演示 Spring Cloud 集成 Nacos 配置中心的示例项目。

## 项目介绍

本项目展示了如何在 Spring Cloud 应用中集成 Nacos 配置中心，实现配置的动态刷新和集中管理。

## 技术栈

- Spring Boot 2.7.18
- Spring Cloud Alibaba 2021.0.1.0
- Nacos Config

## 快速开始

### 前置条件

- JDK 17
- Maven 3.6+
- Nacos Server

### 配置说明

在 `application.yml` 中配置 Nacos 服务器信息：

```yaml
spring:
  application:
    name: your-application
  cloud:
    nacos:
      server-addr: your-nacos-server:80
      config:
        namespace: your-namespace
        group: your-group
        file-extension: yml
```

### 运行项目

```bash
mvn clean package
java -jar target/spring-cloud-nacos-sample-1.0-SNAPSHOT.jar
```

## 功能演示

项目提供了以下 REST 接口来演示配置功能：

### 1. 获取配置信息

```
GET /config/message
```

返回从 Nacos 获取的配置信息，同时展示通过 `@Value` 和 `Environment` 两种方式读取的配置值。

### 2. 查看调试信息

```
GET /config/debug
```

返回完整的配置信息，包括应用名称、命名空间、分组等详细信息。

## 配置刷新

项目支持配置的动态刷新：

1. 通过 `@RefreshScope` 注解实现配置自动刷新
2. 支持 `@Value` 注解的属性刷新
3. 支持 `@ConfigurationProperties` 的配置类刷新

## 项目结构

```
src/main/java/cn/llamazing/cloud/
├── NacosSampleApplication.java        # 应用入口
├── controller
│   └── ConfigController.java          # 配置演示控制器
└── config
    └── ConfigProperties.java          # 配置属性类
```

## 注意事项

1. 确保 Nacos 服务器正常运行
2. 检查配置的命名空间和分组是否正确
3. 配置变更后会自动刷新，无需重启应用

## 参考文档

- [Spring Cloud Alibaba 文档](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html)
- [Nacos 官方文档](https://nacos.io/zh-cn/docs/quick-start.html)

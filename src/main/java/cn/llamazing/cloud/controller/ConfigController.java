package cn.llamazing.cloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

  private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

  @Value("${sample.message:默认消息}")
  private String messageFromValue;

  @Autowired
  private Environment environment;

  @GetMapping("/message")
  public String getMessage() {
    String envMessage = environment.getProperty("sample.message");
    String valueMessage = messageFromValue;

    logger.info("Environment中的消息: {}", envMessage);
    logger.info("@Value注解的消息: {}", valueMessage);

    // 打印所有激活的配置文件
    String[] activeProfiles = environment.getActiveProfiles();
    logger.info("激活的配置文件: {}", String.join(", ", activeProfiles));

    return String.format("Environment: %s\n@Value: %s",
        envMessage != null ? envMessage : "null",
        valueMessage);
  }

  @GetMapping("/debug")
  public String getDebugInfo() {
    StringBuilder debug = new StringBuilder();
    debug.append("应用名称: ").append(environment.getProperty("spring.application.name")).append("\n");
    debug.append("命名空间: ").append(environment.getProperty("spring.cloud.nacos.config.namespace")).append("\n");
    debug.append("分组: ").append(environment.getProperty("spring.cloud.nacos.config.group")).append("\n");
    debug.append("服务器地址: ").append(environment.getProperty("spring.cloud.nacos.server-addr")).append("\n");
    debug.append("配置文件: ").append(environment.getProperty("spring.cloud.nacos.config.file-extension")).append("\n");
    debug.append("配置导入: ").append(environment.getProperty("spring.config.import")).append("\n");
    debug.append("当前消息: ").append(environment.getProperty("sample.message")).append("\n");
    debug.append("完整配置路径: ").append(String.format("nacos:%s.%s?group=%s&namespace=%s",
        environment.getProperty("spring.application.name"),
        environment.getProperty("spring.cloud.nacos.config.file-extension"),
        environment.getProperty("spring.cloud.nacos.config.group"),
        environment.getProperty("spring.cloud.nacos.config.namespace"))).append("\n");

    logger.info("完整调试信息：\n{}", debug);
    return debug.toString();
  }
} 
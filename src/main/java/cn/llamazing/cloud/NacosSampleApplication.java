package cn.llamazing.cloud;

import cn.llamazing.cloud.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class NacosSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(NacosSampleApplication.class, args);
  }
}

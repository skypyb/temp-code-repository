package com.skypyb.temp;


import com.skypyb.temp.config.Oauth2SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;


/**
 * 在使用 {@link ConfigurationPropertiesScan} 指定 basePackages参数和 basePackageClasses 参数的效果是一致的
 * 如果使用的是 basePackageClasses 参数
 * 则在  ConfigurationPropertiesScanRegistrar#getPackagesToScan
 * 方法中获取 Class 的 package name
 */
//@EnableConfigurationProperties(value = {Oauth2SecurityProperties.class})
@ConfigurationPropertiesScan(basePackageClasses = {Oauth2SecurityProperties.class})
@SpringBootApplication
public class TempApplication {

    private static Logger logger = LoggerFactory.getLogger(TempApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TempApplication.class, args);
    }


    @Bean
    public CommandLineRunner afterStartingService(Oauth2SecurityProperties properties) {
        return (args) -> {
            Assert.notNull(properties, "Properties bean create failed.");
            Assert.isTrue(properties.getRefreshTokenSupport(), "Properties value injection failed");

            logger.info("successful ! ");
        };
    }


}

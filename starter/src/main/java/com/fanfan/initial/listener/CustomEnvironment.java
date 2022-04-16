package com.fanfan.initial.listener;

import com.fanfan.initial.SensitiveKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class CustomEnvironment implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String sourcePath = environment.getProperty("custom.env.path");
        Resource resource = new FileSystemResource(sourcePath);
        PropertySource propertySource = null;
        if (resource.exists()) {
            try {
                propertySource = new YamlPropertySourceLoader().load("customProperties", resource).get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (propertySource != null) {
            environment.getSystemProperties().putIfAbsent(SensitiveKeyProperties.DB_USER, propertySource.getProperty(SensitiveKeyProperties.DB_USER));
            environment.getSystemProperties().putIfAbsent(SensitiveKeyProperties.DB_PWD, propertySource.getProperty(SensitiveKeyProperties.DB_PWD));
            environment.getSystemProperties().putIfAbsent(SensitiveKeyProperties.DB_IP_PORT, propertySource.getProperty(SensitiveKeyProperties.DB_IP_PORT));
            environment.getSystemProperties().putIfAbsent(SensitiveKeyProperties.REDIS_DOMAIN, propertySource.getProperty(SensitiveKeyProperties.REDIS_DOMAIN));
            environment.getSystemProperties().putIfAbsent(SensitiveKeyProperties.REDIS_PWD, propertySource.getProperty(SensitiveKeyProperties.REDIS_PWD));
            environment.getSystemProperties().putIfAbsent(SensitiveKeyProperties.HTTP_SECURITY_USER, propertySource.getProperty(SensitiveKeyProperties.HTTP_SECURITY_USER));
            environment.getSystemProperties().putIfAbsent(SensitiveKeyProperties.HTTP_SECURITY_PWD, propertySource.getProperty(SensitiveKeyProperties.HTTP_SECURITY_PWD));
            environment.getSystemProperties().putIfAbsent(SensitiveKeyProperties.HTTP_SECURITY_MATCH, propertySource.getProperty(SensitiveKeyProperties.HTTP_SECURITY_MATCH));
        }
    }
}

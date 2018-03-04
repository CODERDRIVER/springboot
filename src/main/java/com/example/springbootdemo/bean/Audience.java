package com.example.springbootdemo.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt的实体类
 */
@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {
    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;
}


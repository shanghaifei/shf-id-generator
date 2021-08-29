package com.hyfetech.idgenerator.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author shanghaifei
 * @date 2021/8/25
 */
@Configuration
@ConfigurationProperties("shf.id-generator")
public class ShfIdGeneratorConfig {
    /**
     * 机房ID
     */
    public Long machineroomid;

    /**
     * 机器ID
     */
    public Long machineid;
}

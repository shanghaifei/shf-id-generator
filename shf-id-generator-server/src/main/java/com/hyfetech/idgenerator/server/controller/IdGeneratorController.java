package com.hyfetech.idgenerator.server.controller;

import com.hyfetech.idgenerator.core.SnowflakeIdGenerator;
import com.hyfetech.idgenerator.server.config.ShfIdGeneratorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ID生成器controller
 * @author shanghaifei
 * @date 2021/8/25
 */
@RestController
public class IdGeneratorController {
    /**
     * 雪花算法ID生成器
     */
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    /**
     * ID生成器配置
     */
    @Autowired
    private ShfIdGeneratorConfig shfIdGeneratorConfig;

    /**
     * 使用雪花算法生成ID
     * @return ID
     */
    @GetMapping("snowflake/generateId")
    public Long getSnowflakeId() {
        try {
            return snowflakeIdGenerator.generateId(
                    shfIdGeneratorConfig.machineroomid, shfIdGeneratorConfig.machineid);
        }
        catch (Exception ex) {
            return null;
        }
    }
}

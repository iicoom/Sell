package com.icoom.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by mxj on 2018/9/2 上午9:59
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat") // 可以拿到application.yml 中weixin配置
public class WechatAccountConfig {

    private String mpAppId;

    private String mpAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户秘钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;


}

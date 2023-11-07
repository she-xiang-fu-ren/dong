package cn.iocoder.dong.module.system.framework.dinging;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "dong.justauth")
@Validated
@Component
@Data
public class DingingCodeProperties {

    private String clientId;

    private String clientSecret;

}

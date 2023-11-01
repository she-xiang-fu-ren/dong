package cn.iocoder.dong.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${yudao.info.base-package}
@SpringBootApplication(scanBasePackages = {"${yudao.info.base-package}.server", "${yudao.info.base-package}.module"})
@MapperScan("${yudao.info.base-package}.module.system.dal.mysql")
public class DongServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DongServerApplication.class,args);
    }
}

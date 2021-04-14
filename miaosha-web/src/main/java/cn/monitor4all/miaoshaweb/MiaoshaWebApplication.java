package cn.monitor4all.miaoshaweb;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 启动类
 *
 * @author Shadowalker
 */
@SpringBootApplication(scanBasePackages = "cn.monitor4all")
public class MiaoshaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiaoshaWebApplication.class, args);
    }

    @Bean
    public Redisson redisson() {
        // 单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

}

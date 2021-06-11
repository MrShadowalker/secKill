package org.shadowalker.seckillweb;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.URISyntaxException;

/**
 * 启动类
 *
 * @author Shadowalker
 */
@SpringBootApplication(scanBasePackages = "org.shadowalker")
public class SeckillWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillWebApplication.class, args);
    }

    @Bean
    public Redisson redisson() throws URISyntaxException {
        // 单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        // URI masterUri1 = new URI("xxx");
        // URI masterUri2 = new URI("xxx");
        // URI slaveUri1 = new URI("xxx");
        // URI slaveUri2 = new URI("xxx");
        // URI slaveUri3 = new URI("xxx");
        // Set<URI> slaveUris = new HashSet<>();
        // slaveUris.add(slaveUri1);
        // slaveUris.add(slaveUri2);
        // slaveUris.add(slaveUri3);
        // // 主从架构
        // config.useMasterSlaveServers().setMasterAddress(masterUri1);
        // config.useMasterSlaveServers().setMasterAddress(masterUri2);
        // config.useMasterSlaveServers().setSlaveAddresses(slaveUris);
        return (Redisson) Redisson.create(config);
    }

}

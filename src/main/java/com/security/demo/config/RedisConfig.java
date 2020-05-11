package com.security.demo.config;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

/**
 * @param
 * @author 【author：chenglonghy , QQ：983956409】
 * @return
 * @date 2020/5/11 16:12
 * @history 2020/5/11 16:12 【author：chenglonghy , QQ：983956409】  create.
 */
@Configuration
@AutoConfigureBefore(LettuceConnectionFactory.class)
public class RedisConfig implements LettuceClientConfigurationBuilderCustomizer {

    /**
     * 个性化配置Redis
     *
     * @param clientConfigurationBuilder
     */
    @Override
    public void customize(LettuceClientConfiguration.LettuceClientConfigurationBuilder clientConfigurationBuilder) {
        clientConfigurationBuilder.readFrom(ReadFrom.REPLICA);
        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                                                                       .enablePeriodicRefresh(Duration.ofHours(1L))
                                                                       .enableAllAdaptiveRefreshTriggers()
                                                                       .build();
        clientConfigurationBuilder.clientOptions(ClusterClientOptions.builder()
                                                         .topologyRefreshOptions(topologyRefreshOptions)
                                                         .build());
    }
}

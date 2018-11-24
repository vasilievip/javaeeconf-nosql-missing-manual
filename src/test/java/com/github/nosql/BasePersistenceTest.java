package com.github.nosql;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;
import com.playtika.test.aerospike.AerospikeTestOperations;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasePersistenceTest.TestConfiguration.class)
public abstract class BasePersistenceTest {

    static final String SET = "userScore";

    @Value("${embedded.aerospike.namespace}")
    String namespace;

    @Autowired
    ConfigurableListableBeanFactory beanFactory;

    @Autowired
    AerospikeClient client;

    @Autowired
    WritePolicy policy;

    @Autowired
    AerospikeTestOperations aerospikeTestOperations;

    @EnableAutoConfiguration
    @Configuration
    static class TestConfiguration {

        @Value("${embedded.aerospike.host}")
        String host;
        @Value("${embedded.aerospike.port}")
        int port;

        @Bean(destroyMethod = "close")
        public AerospikeClient aerospikeClient() {
            ClientPolicy clientPolicy = new ClientPolicy();
            clientPolicy.timeout = 10_000;//in millis
            return new AerospikeClient(clientPolicy, host, port);
        }

        @Bean
        public WritePolicy policy() {
            WritePolicy policy = new WritePolicy();
            policy.totalTimeout = 200;//in millis
            return policy;
        }
    }
}
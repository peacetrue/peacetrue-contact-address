package com.github.peacetrue.contactaddress;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiayx
 */
@Configuration(proxyBeanMethods = false)
@ImportAutoConfiguration({
        R2dbcAutoConfiguration.class,
        R2dbcDataAutoConfiguration.class,
        R2dbcTransactionManagerAutoConfiguration.class,
        ServiceContactAddressAutoConfiguration.class
})
@EnableAutoConfiguration
public class TestServiceContactAddressAutoConfiguration {

}

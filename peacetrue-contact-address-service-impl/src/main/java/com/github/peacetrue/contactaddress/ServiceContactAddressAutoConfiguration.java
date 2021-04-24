package com.github.peacetrue.contactaddress;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Objects;

/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ServiceContactAddressProperties.class)
@ComponentScan(basePackageClasses = ServiceContactAddressAutoConfiguration.class)
@PropertySource("classpath:/application-contact-address-service.yml")
public class ServiceContactAddressAutoConfiguration {

    private ServiceContactAddressProperties properties;

    public ServiceContactAddressAutoConfiguration(ServiceContactAddressProperties properties) {
        this.properties = Objects.requireNonNull(properties);
    }

}

package com.github.peacetrue.contactaddress;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.data.web.ReactiveSortHandlerMethodArgumentResolver;


/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ControllerContactAddressProperties.class)
@ComponentScan(basePackageClasses = ControllerContactAddressAutoConfiguration.class)
@PropertySource("classpath:/application-contact-address-controller.yml")
public class ControllerContactAddressAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ReactivePageableHandlerMethodArgumentResolver.class)
    public ReactivePageableHandlerMethodArgumentResolver reactivePageableHandlerMethodArgumentResolver() {
        return new ReactivePageableHandlerMethodArgumentResolver();
    }

    @Bean
    @ConditionalOnMissingBean(ReactivePageableHandlerMethodArgumentResolver.class)
    public ReactiveSortHandlerMethodArgumentResolver reactiveSortHandlerMethodArgumentResolver() {
        return new ReactiveSortHandlerMethodArgumentResolver();
    }
}

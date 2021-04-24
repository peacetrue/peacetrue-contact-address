package com.github.peacetrue.contactaddress;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiayx
 */
@Data
@ConfigurationProperties(prefix = "peacetrue.contact-address")
public class ServiceContactAddressProperties {

}

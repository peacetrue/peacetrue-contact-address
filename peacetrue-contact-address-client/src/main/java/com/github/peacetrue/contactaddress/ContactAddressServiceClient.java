package com.github.peacetrue.contactaddress;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;

/**
 * 联系地址客户端
 *
 * @author xiayx
 */
@ReactiveFeignClient(name = "peacetrue-contact-address", url = "${peacetrue.ContactAddress.url:${peacetrue.server.url:}}")
public interface ContactAddressServiceClient {

    @PostMapping(value = "/contact-addresses")
    Mono<ContactAddressVO> add(ContactAddressAdd params);

    @GetMapping(value = "/contact-addresses", params = "page")
    Mono<Page<ContactAddressVO>> query(@Nullable @SpringQueryMap ContactAddressQuery params, @Nullable Pageable pageable, @SpringQueryMap String... projection);

    @GetMapping(value = "/contact-addresses", params = "sort")
    Flux<ContactAddressVO> query(@SpringQueryMap ContactAddressQuery params, Sort sort, @SpringQueryMap String... projection);

    @GetMapping(value = "/contact-addresses")
    Flux<ContactAddressVO> query(@SpringQueryMap ContactAddressQuery params, @SpringQueryMap String... projection);

    @GetMapping(value = "/contact-addresses/get")
    Mono<ContactAddressVO> get(@SpringQueryMap ContactAddressGet params, @SpringQueryMap String... projection);

    @PutMapping(value = "/contact-addresses")
    Mono<Integer> modify(ContactAddressModify params);

    @DeleteMapping(value = "/contact-addresses/delete")
    Mono<Integer> delete(@SpringQueryMap ContactAddressDelete params);

}

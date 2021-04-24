package com.github.peacetrue.contactaddress;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 联系地址服务接口
 *
 * @author xiayx
 */
public interface ContactAddressService {

    /** 新增 */
    Mono<ContactAddressVO> add(ContactAddressAdd params);

    /** 分页查询 */
    Mono<Page<ContactAddressVO>> query(ContactAddressQuery params, Pageable pageable, String... projection);

    /** 全量查询 */
    Flux<ContactAddressVO> query(ContactAddressQuery params, Sort sort, String... projection);

    /** 全量查询 */
    Flux<ContactAddressVO> query(ContactAddressQuery params, String... projection);

    /** 获取 */
    Mono<ContactAddressVO> get(ContactAddressGet params, String... projection);

    /** 修改 */
    Mono<Integer> modify(ContactAddressModify params);

    /** 设置默认地址 */
    Mono<Integer> setDefaults(ContactAddressSetDefaults params);

    /** 删除 */
    Mono<Integer> delete(ContactAddressDelete params);

}

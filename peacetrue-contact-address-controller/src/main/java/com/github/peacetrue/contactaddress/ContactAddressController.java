package com.github.peacetrue.contactaddress;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 联系地址控制器
 *
 * @author xiayx
 */
@Slf4j
@RestController
@RequestMapping(value = "/contact-addresses")
public class ContactAddressController {

    @Autowired
    private ContactAddressService contactAddressService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<ContactAddressVO> addByForm(ContactAddressAdd params) {
        log.info("新增联系地址信息(请求方法+表单参数)[{}]", params);
        return contactAddressService.add(params);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ContactAddressVO> addByJson(@RequestBody ContactAddressAdd params) {
        log.info("新增联系地址信息(请求方法+JSON参数)[{}]", params);
        return contactAddressService.add(params);
    }

    @GetMapping(params = "page")
    public Mono<Page<ContactAddressVO>> query(ContactAddressQuery params, Pageable pageable, String... projection) {
        log.info("分页查询联系地址信息(请求方法+参数变量)[{}]", params);
        return contactAddressService.query(params, pageable, projection);
    }

    @GetMapping
    public Flux<ContactAddressVO> query(ContactAddressQuery params, Sort sort, String... projection) {
        log.info("全量查询联系地址信息(请求方法+参数变量)[{}]", params);
        return contactAddressService.query(params, sort, projection);
    }

    @GetMapping("/{id}")
    public Mono<ContactAddressVO> getByUrlPathVariable(@PathVariable Long id, String... projection) {
        log.info("获取联系地址信息(请求方法+路径变量)[{}]", id);
        return contactAddressService.get(new ContactAddressGet(id), projection);
    }

    @RequestMapping("/get")
    public Mono<ContactAddressVO> getByPath(ContactAddressGet params, String... projection) {
        log.info("获取联系地址信息(请求路径+参数变量)[{}]", params);
        return contactAddressService.get(params, projection);
    }

    @GetMapping(params = "filter=defaults")
    public Mono<ContactAddressVO> getDefaults(ContactAddressGet params, String... projection) {
        log.info("获取默认联系地址信息(请求路径+参数变量)[{}]", params);
        params.setDefaults(true);
        return contactAddressService.get(params, projection);
    }

    @PutMapping(value = {"", "/*"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<Integer> modifyByForm(ContactAddressModify params) {
        log.info("修改联系地址信息(请求方法+表单参数)[{}]", params);
        return contactAddressService.modify(params);
    }

    @PutMapping(value = {"", "/*"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Integer> modifyByJson(@RequestBody ContactAddressModify params) {
        log.info("修改联系地址信息(请求方法+JSON参数)[{}]", params);
        return contactAddressService.modify(params);
    }

    @DeleteMapping("/{id}")
    public Mono<Integer> deleteByUrlPathVariable(@PathVariable Long id) {
        log.info("删除联系地址信息(请求方法+URL路径变量)[{}]", id);
        return contactAddressService.delete(new ContactAddressDelete(id));
    }

    @DeleteMapping(params = "id")
    public Mono<Integer> deleteByUrlParamVariable(ContactAddressDelete params) {
        log.info("删除联系地址信息(请求方法+URL参数变量)[{}]", params);
        return contactAddressService.delete(params);
    }

    @RequestMapping(path = "/delete")
    public Mono<Integer> deleteByPath(ContactAddressDelete params) {
        log.info("删除联系地址信息(请求路径+URL参数变量)[{}]", params);
        return contactAddressService.delete(params);
    }


}

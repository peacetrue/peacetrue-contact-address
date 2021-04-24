package com.github.peacetrue.contactaddress;

import com.github.peacetrue.core.IdCapable;
import com.github.peacetrue.core.OperatorCapable;
import com.github.peacetrue.core.Operators;
import com.github.peacetrue.spring.data.domain.SortUtils;
import com.github.peacetrue.spring.data.relational.core.query.CriteriaUtils;
import com.github.peacetrue.spring.data.relational.core.query.QueryUtils;
import com.github.peacetrue.spring.data.relational.core.query.UpdateUtils;
import com.github.peacetrue.spring.util.BeanUtils;
import com.github.peacetrue.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * 联系地址服务实现
 *
 * @author xiayx
 */
@Slf4j
@Service
public class ContactAddressServiceImpl implements ContactAddressService {

    @Autowired
    private R2dbcEntityOperations entityOperations;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public static Criteria buildCriteria(ContactAddressQuery params) {
        if (params.getSourceId() == null) params.setSourceId(0L);
        return CriteriaUtils.and(
                CriteriaUtils.nullableCriteria(CriteriaUtils.smartIn("id"), params::getId),
                CriteriaUtils.nullableCriteria(Criteria.where("contactName")::like, value -> "%" + value + "%", params::getContactName),
                CriteriaUtils.nullableCriteria(Criteria.where("contactPhoneCode")::like, value -> "%" + value + "%", params::getContactPhoneCode),
                CriteriaUtils.nullableCriteria(Criteria.where("addressId")::is, params::getAddressId),
                CriteriaUtils.nullableCriteria(Criteria.where("addressDetail")::like, value -> "%" + value + "%", params::getAddressDetail),
                CriteriaUtils.nullableCriteria(Criteria.where("sourceId")::is, params::getSourceId),
                CriteriaUtils.nullableCriteria(Criteria.where("creatorId")::is, params::getCreatorId),
                CriteriaUtils.nullableCriteria(Criteria.where("createdTime")::greaterThanOrEquals, params.getCreatedTime()::getLowerBound),
                CriteriaUtils.nullableCriteria(Criteria.where("createdTime")::lessThan, DateUtils.DATE_CELL_EXCLUDE, params.getCreatedTime()::getUpperBound),
                CriteriaUtils.nullableCriteria(Criteria.where("modifierId")::is, params::getModifierId),
                CriteriaUtils.nullableCriteria(Criteria.where("modifiedTime")::greaterThanOrEquals, params.getModifiedTime()::getLowerBound),
                CriteriaUtils.nullableCriteria(Criteria.where("modifiedTime")::lessThan, DateUtils.DATE_CELL_EXCLUDE, params.getModifiedTime()::getUpperBound)
        );
    }

    @Override
    @Transactional
    public Mono<ContactAddressVO> add(ContactAddressAdd params) {
        log.info("新增联系地址信息[{}]", params);
        ContactAddress entity = BeanUtils.map(params, ContactAddress.class);
        Operators.setCreateModify(params, entity);
        entity.setSourceId(0L);
        return entityOperations.insert(entity)
                .map(item -> BeanUtils.map(item, ContactAddressVO.class))
                .doOnNext(item -> eventPublisher.publishEvent(new PayloadApplicationEvent<>(item, params)));
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Page<ContactAddressVO>> query(ContactAddressQuery params, Pageable pageable, String... projection) {
        log.info("分页查询联系地址信息[{}]", params);
        Criteria where = buildCriteria(params);
        return entityOperations.count(Query.query(where), ContactAddress.class)
                .<Page<ContactAddressVO>>flatMap(total -> {
                    if (total == 0L) return Mono.just(new PageImpl<>(Collections.emptyList(), pageable, 0L));
                    Query query = Query.query(where).with(pageable).sort(pageable.getSortOr(SortUtils.SORT_CREATED_TIME_DESC));
                    return entityOperations.select(query, ContactAddress.class)
                            .map(item -> BeanUtils.map(item, ContactAddressVO.class))
                            .collectList()
                            .doOnNext(item -> eventPublisher.publishEvent(new PayloadApplicationEvent<>(item, params)))
                            .map(item -> new PageImpl<>(item, pageable, total));
                })
                ;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ContactAddressVO> query(ContactAddressQuery params, Sort sort, String... projection) {
        log.info("全量查询联系地址信息[{}]", params);
        Criteria where = buildCriteria(params);
        Query query = Query.query(where).sort(sort).limit(100);
        return entityOperations.select(query, ContactAddress.class)
                .map(item -> BeanUtils.map(item, ContactAddressVO.class))
                .doOnNext(item -> eventPublisher.publishEvent(new PayloadApplicationEvent<>(item, params)))
                ;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ContactAddressVO> query(ContactAddressQuery params, String... projection) {
        return query(params, SortUtils.SORT_CREATED_TIME_DESC, projection);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ContactAddressVO> get(ContactAddressGet params, String... projection) {
        log.info("获取联系地址信息[{}]", params);
//        Criteria where = CriteriaUtils.and(
//                CriteriaUtils.nullableCriteria(Criteria.where("id")::is, params::getId),
//        );
        Criteria where = Criteria.where("id").is(params.getId());
        return entityOperations.selectOne(Query.query(where), ContactAddress.class)
                .map(item -> BeanUtils.map(item, ContactAddressVO.class))
                .doOnNext(item -> eventPublisher.publishEvent(new PayloadApplicationEvent<>(item, params)))
                ;
    }

    @Override
    @Transactional
    public Mono<Integer> modify(ContactAddressModify params) {
        log.info("修改联系地址信息[{}]", params);
        Query idQuery = QueryUtils.id(params::getId);
        return entityOperations.selectOne(idQuery, ContactAddress.class)
                .doOnNext(entity -> BeanUtils.copyProperties(params, entity, BeanUtils.EMPTY_PROPERTY_VALUE))
                .map(entity -> Operators.setOperator(params, BeanUtils.map(entity, ContactAddressAdd.class)))
                .flatMap(this::add)
                .doOnNext(vo -> log.debug("更新联系地址[{}]的上家为[{}]", params.getId(), vo.getId()))
                .flatMap(vo -> {
                    Update update = UpdateUtils.setModify(Update.update("sourceId", vo.getId()), params);
                    return entityOperations.update(idQuery, update, ContactAddress.class);
                })
                .doOnNext(vo -> eventPublisher.publishEvent(new PayloadApplicationEvent<>(vo, params)))
                .switchIfEmpty(Mono.just(0));
    }


    private <T extends IdCapable<Long> & OperatorCapable<Long>> Mono<Integer> modifyGeneric(T params) {
        Query idQuery = QueryUtils.id(params::getId);
        return entityOperations.selectOne(idQuery, ContactAddress.class)
                .zipWhen(entity -> {
                    ContactAddress modify = BeanUtils.map(params, ContactAddress.class);
                    modify.setModifierId(params.getOperatorId());
                    modify.setModifiedTime(LocalDateTime.now());
                    Update update = UpdateUtils.selectiveUpdateFromExample(modify);
                    return entityOperations.update(idQuery, update, ContactAddress.class);
                })
                .map(tuple2 -> {
                    ContactAddressVO vo = BeanUtils.map(tuple2.getT1(), ContactAddressVO.class);
                    BeanUtils.copyProperties(params, vo, BeanUtils.EMPTY_PROPERTY_VALUE);
                    eventPublisher.publishEvent(new PayloadApplicationEvent<>(vo, params));
                    return tuple2.getT2();
                })
                .switchIfEmpty(Mono.just(0));
    }

    @Override
    @Transactional
    public Mono<Integer> delete(ContactAddressDelete params) {
        log.info("删除联系地址信息[{}]", params);
        Query idQuery = QueryUtils.id(params::getId);
        return entityOperations.selectOne(idQuery, ContactAddress.class)
                .map(item -> BeanUtils.map(item, ContactAddressVO.class))
                .flatMap(vo -> {
                    Update update = UpdateUtils.setModify(Update.update("sourceId", vo.getId()), params);
                    return entityOperations.update(idQuery, update, ContactAddress.class);
                })
                .doOnNext(vo -> eventPublisher.publishEvent(new PayloadApplicationEvent<>(vo, params)))
                .switchIfEmpty(Mono.just(0));
    }

}

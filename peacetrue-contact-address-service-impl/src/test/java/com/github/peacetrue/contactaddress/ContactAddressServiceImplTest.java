package com.github.peacetrue.contactaddress;

import com.github.peacetrue.spring.util.BeanUtils;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import java.io.Serializable;

/**
 * @author : xiayx
 * @since : 2020-05-22 16:43
 **/
@SpringBootTest(classes = TestServiceContactAddressAutoConfiguration.class)
@ActiveProfiles("contact-address-service-test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactAddressServiceImplTest {

    public static final EasyRandomParameters parameters = new EasyRandomParameters().randomize(Serializable.class, () -> 1L);
    public static final EasyRandom EASY_RANDOM = new EasyRandom(parameters);
    public static final ContactAddressAdd ADD = EASY_RANDOM.nextObject(ContactAddressAdd.class);
    public static final ContactAddressModify MODIFY = EASY_RANDOM.nextObject(ContactAddressModify.class);
    public static ContactAddressVO vo;

    static {
        ADD.setOperatorId(EASY_RANDOM.nextObject(Long.class));
        MODIFY.setOperatorId(EASY_RANDOM.nextObject(Long.class));
    }

    @Autowired
    private ContactAddressServiceImpl service;

    @Test
    @Order(10)
    void add() {
        service.add(ADD)
                .as(StepVerifier::create)
                .assertNext(data -> {
                    Assertions.assertEquals(data.getCreatorId(), ADD.getOperatorId());
                    vo = data;
                })
                .verifyComplete();
    }

    @Test
    @Order(20)
    void queryForPage() {
        ContactAddressQuery params = BeanUtils.map(vo, ContactAddressQuery.class);
        service.query(params, PageRequest.of(0, 10))
                .as(StepVerifier::create)
                .assertNext(page -> Assertions.assertEquals(1, page.getTotalElements()))
                .verifyComplete();
    }

    @Test
    @Order(30)
    void queryForList() {
        ContactAddressQuery params = BeanUtils.map(vo, ContactAddressQuery.class);
        service.query(params)
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @Order(40)
    void get() {
        ContactAddressGet params = BeanUtils.map(vo, ContactAddressGet.class);
        service.get(params)
                .as(StepVerifier::create)
                .assertNext(item -> Assertions.assertEquals(vo.getId(), item.getId()))
                .verifyComplete();
    }

    @Test
    @Order(50)
    void modify() {
        ContactAddressModify params = MODIFY;
        params.setId(vo.getId());
        service.modify(params)
                .as(StepVerifier::create)
                .expectNext(1)
                .verifyComplete();
    }

    @Test
    @Order(60)
    void delete() {
        ContactAddressDelete params = new ContactAddressDelete(vo.getId());
        service.delete(params)
                .as(StepVerifier::create)
                .expectNext(1)
                .verifyComplete();
    }
}

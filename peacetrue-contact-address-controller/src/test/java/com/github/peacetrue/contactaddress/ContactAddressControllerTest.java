package com.github.peacetrue.contactaddress;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.function.Consumer;

/**
 * @author xiayx
 */
@SpringBootTest(classes = TestControllerContactAddressAutoConfiguration.class)
@ActiveProfiles({"contact-address-controller-test", "contact-address-service-test" })
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactAddressControllerTest {

    @Autowired
    private WebTestClient client;

    @Test
    @Order(10)
    void add() {
        this.client.post().uri("/contact-addresses")
                .bodyValue(ContactAddressServiceImplTest.ADD)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ContactAddressVO.class).value((Consumer<ContactAddressVO>) vo -> ContactAddressServiceImplTest.vo = vo);
    }

    @Test
    @Order(20)
    void queryForPage() {
        this.client.get()
                .uri("/contact-addresses?page=0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$.totalElements").isEqualTo(1);
    }

    @Test
    @Order(30)
    void queryForList() {
        this.client.get()
                .uri("/contact-addresses")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$.size()").isEqualTo(1);
    }

    @Test
    @Order(40)
    void get() {
        this.client.get()
                .uri("/contact-addresses/{0}", ContactAddressServiceImplTest.vo.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ContactAddressVO.class).isEqualTo(ContactAddressServiceImplTest.vo);
    }


    @Test
    @Order(50)
    void modify() {
        ContactAddressModify modify = ContactAddressServiceImplTest.MODIFY;
        modify.setId(ContactAddressServiceImplTest.vo.getId());
        this.client.put()
                .uri("/contact-addresses/{id}", ContactAddressServiceImplTest.vo.getId())
                .bodyValue(modify)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Integer.class).isEqualTo(1);
    }

    @Test
    @Order(60)
    void delete() {
        this.client.delete()
                .uri("/contact-addresses/{0}", ContactAddressServiceImplTest.vo.getId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Integer.class).isEqualTo(1);
    }

}

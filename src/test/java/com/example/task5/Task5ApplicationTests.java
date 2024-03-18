package com.example.task5;

import com.example.task5.models.entities.AccountState;
import com.example.task5.models.entities.TppProductRegister;
import com.example.task5.repositories.ProductRegisterRepo;
import com.example.task5.repositories.ProductRepo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestTask5Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Task5ApplicationTests  {


    @LocalServerPort
    private Integer port;

    @Autowired
    PostgreSQLContainer postgres;

    @Autowired
    ProductRegisterRepo productRegisterRepo;
    @Autowired
    ProductRepo productRepo;
    @BeforeAll
    void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        postgres.start();
    }

    @AfterAll
    void afterAll() {
        postgres.stop();
    }

    @Test
    void testConnection(){

        Assertions.assertTrue(postgres.isRunning());
    }



    @Test
    public void accountCreateCorrectRequestTest(){

        given()
                .contentType(ContentType.JSON)
                .port(port)
                .body(Constants.fromFile("correctRegister.json"))
                .when()
                .post("/corporate-settlement-account/create")
                .peek()
                .then()
                .statusCode(200)
                .body("data.accountId", equalTo("2"));

        var tppProductRegisterMy = new TppProductRegister();

        tppProductRegisterMy.setId(1);
        tppProductRegisterMy.setAccountNum("475335516415314841861");
        tppProductRegisterMy.setAccountNumId(1);
        tppProductRegisterMy.setCurrency("800");
        tppProductRegisterMy.setState(AccountState.OPEN);
        var tppProductRegisterDB = productRegisterRepo.findAll();
        tppProductRegisterDB.get(0).setProductId(null);
        tppProductRegisterDB.get(0).setRegisterType(null);
        Assertions.assertEquals(tppProductRegisterDB.get(0),tppProductRegisterMy);
    }


    @Test
    public void accountCreateProductTest(){

        given()
                .contentType(ContentType.JSON)
                .port(port)
                .body(Constants.fromFile("correctProduct.json"))
                .when()
                .post("/corporate-settlement-instance/create")
                .peek()
                .then()
                .statusCode(200)
                .body("data.instanceId", equalTo(2))
                .body("data.registerId", equalTo(List.of(1)));


        var tppProductDBOpt = productRepo.findById(2);
        var tppProductDb = tppProductDBOpt.get();

        Assertions.assertEquals(2,tppProductDb.getId());
        Assertions.assertEquals(1,tppProductDb.getClientId());
        Assertions.assertEquals("001/2023_NSB",tppProductDb.getNumber());
        Assertions.assertEquals(1,tppProductDb.getPriority());
        Assertions.assertEquals(Timestamp.valueOf(LocalDateTime.of(2023,12, 1,3,0,0)),tppProductDb.getDateOfConclusion());
    }
}

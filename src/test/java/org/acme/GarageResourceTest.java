package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.apache.http.HttpStatus;
import org.eclipse.jnosql.mapping.Database;
import org.eclipse.jnosql.mapping.DatabaseType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class GarageResourceTest {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    Garage garage;

    @BeforeEach
    @AfterEach
    public void cleanupDatabase(){
        garage.deleteAll(garage.findAll().toList());
    }


    @Test
    public void shouldReturnAnEmptyList(){
        given()
                .when().get("/garage")
                .then()
                .statusCode(200)
                .body("$",hasSize(0));
    }

    @Test
    public void shouldPersistAValidCar(){
        record NewCar (String owner){}
        record Car(String id, String owner){}

        var luisCar=new NewCar("Luis");

        Car persistedCar = given()
                .contentType(ContentType.JSON)
                .body(luisCar)
                .post("/garage")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(new TypeRef<Car>() {
                });

        assertThat(persistedCar.id(),notNullValue());
        assertThat(persistedCar.owner(),is(luisCar.owner()));

    }

}

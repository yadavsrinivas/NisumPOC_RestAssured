import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {

    int id;

    /*@Test
    public void GetUsers(){

                given()
                        .when()
                        .get("https://reqres.in/api/users?page=2")
                        .then()
                        .statusCode(200)
                        .body("page", equalTo(2))
                        .log().all();

            }*/
       @Test (priority = 1)
            void createUser()
       {
           HashMap data = new HashMap();
           data.put("name", "Srinivas");
           data.put("job", "QA");

     id =   given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
           .jsonPath().getInt("id");

       }

       @Test (priority=2, dependsOnMethods = {"createUser"})
    void UpdateUser(){
           HashMap data = new HashMap();
           data.put("name", "Sriniwaas");
           data.put("job", "QA");

            given()
                   .contentType("application/json")
                   .body(data)
                   .when()
                   .put("https://reqres.in/api/users/"+id)
                    .then()
                    .statusCode(200)
                    .log()
                    .all();
       }
       @Test (priority=3)
       void deleteUser(){
              given()
                      .when()
                      .delete("https://reqres.in/api/users/"+id)
                      .then()
                      .statusCode(204)
                      .log().all();

       }
}

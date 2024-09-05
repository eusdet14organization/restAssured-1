import dto.CreateUserRequest;
import dto.CreateUserResponse;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class CreateUserTest extends TestBase {

    RequestSpecification requestSpecification = given().contentType(ContentType.JSON);
    ResponseSpecification responseSpecification = expect().statusCode(200);

    @Test
    public void createUserTest(){
        String job = "IT";
        String name = "Andrei";

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName(name);
        createUserRequest.setJob(job);

        CreateUserResponse createUserResponse = given().spec(requestSpecification).
                body(createUserRequest).
                when().post("users").
                as(CreateUserResponse.class);

        Assertions.assertEquals(job, createUserResponse.getJob());
        Assertions.assertEquals(name, createUserResponse.getName());
    }



}

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    String url = "https://reqres.in";

    @Test
    public void checkStatusCode() {

        given().baseUri(url).log().all().
                when().get("/api/users").
                then().log().all().assertThat().statusCode(200);

    }

    @Test
    public void addParamTest() {

        given().baseUri(url).log().all().
                when().param("page", 2).
                get("/api/users").
                then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void headerTest() {

        given().baseUri(url).log().all().
                when().param("page", 2).
                get("/api/users").
                then().log().all().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);
    }

    @Test
    public void checkResponseTest() {

        Response response = given().baseUri(url).log().all().
                when().
                get("/api/users");

        System.out.println(response.prettyPrint());

        Assertions.assertTrue(response.body().asString().contains("charles.morris@reqres.in"));
    }
}

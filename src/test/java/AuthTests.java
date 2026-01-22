import Core.BaseTest;
import Utils.JsonReader;
import Utils.RetryAnalyzer;
import Utils.Routes;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import Enum.StatusCode;
import java.util.HashMap;
import java.util.Map;

public class AuthTests extends BaseTest
{
    @Test(description = "Testcase to test user login",retryAnalyzer = RetryAnalyzer.class)
    public void testLoginUser() {
        Map<String,String> loginBody = new HashMap<>();
        JsonNode root = JsonReader.getRoot();
        loginBody.put("email",root.get("email").asText());
        loginBody.put("password",root.get("password").asText());
        Response response =
                        requestSpecification
                        .when()
                        .body(loginBody)
                        .post(Routes.LOGIN)
                        .then()
                        .extract()
                        .response();
        softAssert.assertEquals(response.getStatusCode(),StatusCode.SUCCESS.code,"Expected status code was 200 but actual status code was "+response.getStatusCode());
        softAssert.assertNotNull(response.jsonPath().getString("token"));
        softAssert.assertAll();
        System.out.println("testLoginUser passed successfully");
    }

    @Test(description = "Testcase to test user registration",retryAnalyzer = RetryAnalyzer.class)
    public void testRegisterUser() {
        Map<String,String> registerBody = new HashMap<>();
        JsonNode root = JsonReader.getRoot();
        registerBody.put("email",root.get("email").asText());
        registerBody.put("password",root.get("password").asText());
        Response response =
                        requestSpecification
                        .when()
                        .body(registerBody)
                        .post(Routes.REGISTER)
                        .then()
                        .extract()
                        .response();
        softAssert.assertEquals(response.getStatusCode(),StatusCode.SUCCESS.code,"Expected status code was 200 but actual status code was "+response.getStatusCode());
        softAssert.assertNotNull(response.jsonPath().getString("id"));
        softAssert.assertNotNull(response.jsonPath().getString("token"));
        softAssert.assertAll();
        System.out.println("testRegisterUser passed successfully");
    }
}

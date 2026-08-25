package tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

class JsonPlaceholderTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    void shouldGetPostById() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1));
    }

    @Test
    void shouldCreatePost() {
        given()
                .contentType("application/json")
                .body("""
                        {
                            "title": "QA test",
                            "body": "Testing Rest Assured",
                            "userId": 1
                        }
                        """)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("QA test"))
                .body("body", equalTo("Testing Rest Assured"))
                .body("userId", equalTo(1));
    }

    @Test
    void shouldReturnPostsForUser() {
        given()
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("[0].userId", equalTo(1));
    }

    @Test
    void shouldReturn404ForMissingPost() {
        given()
                .when()
                .get("/posts/999999")
                .then()
                .statusCode(404);
    }

    @Test
    void shouldUseUserIdFromResponseToFindUsersPosts() {
        Response response = given()
                .when()
                .get("/posts/1");
        response.then()
                .statusCode(200);

        int userId = response.jsonPath().getInt("userId");

        given()
                .queryParam("userId", userId)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("userId", everyItem(equalTo(userId)));
    }
}
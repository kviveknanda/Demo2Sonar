package webapp.tier.resource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class RedisResourceTest {

	@Test
	void testSetError() {
		given()
				.when()
				.contentType("application/json")
				.post("/quarkus/redis/set")
				.then()
				.statusCode(500)
				.body(is("Put Error."));
	}

	@Test
	void testGetError() {
		given()
				.when()
				.get("/quarkus/redis/get")
				.then()
				.statusCode(500)
				.body(is("Get Error."));
	}

	@Test
	void testPublishError() {
		given()
				.when()
				.contentType("application/json")
				.post("/quarkus/redis/publish")
				.then()
				.statusCode(500)
				.body(is("Publish Error."));
	}

}


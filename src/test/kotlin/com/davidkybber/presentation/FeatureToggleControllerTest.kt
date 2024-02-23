package com.davidkybber.presentation

import com.davidkybber.presentation.dtos.FeatureToggleDto
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class FeatureToggleControllerTest {
    @Test
    fun `GET request returns initial toggle`() {
        given()
            .`when`().get("/api/featuretoggle")
            .then().statusCode(200)
            .body("[0].id", `is`("1"))
    }

    @Test
    fun `POST request to create toggle`() {
        val requestBody = FeatureToggleDto(id = "2", featureToggle = "test2")
        given()
            .contentType("application/json")
            .body(requestBody)
            .`when`().post("/api/featuretoggle")
            .then().statusCode(200)
            .body(`is`("test2 has been added to the feature toggles list."))
    }

    @Test
    fun `DELETE request deletes feature toggle with id`() {
        val requestBody = FeatureToggleDto(id = "1")
        given()
            .contentType("application/json")
            .body(requestBody)
            .`when`().delete("/api/featuretoggle")
            .then().statusCode(200)
            .body(`is`("Deleted toggle with id 1"))
    }
}
package com.davidkybber.presentation

import com.davidkybber.presentation.dtos.FeatureToggleRequest
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class FeatureToggleControllerTest {
    @Test
    fun `GET Request for toggle returns toggle`() {
        given()
            .`when`().get("/api/featuretoggle/1")
            .then().statusCode(200)
            .body("id", `is`("1"))
    }

    @Test
    fun `GET request returns initial toggle`() {
        given()
            .`when`().get("/api/featuretoggle")
            .then().statusCode(200)
            .body("[0].id", `is`("1"))
    }

    @Test
    fun `POST request to create toggle`() {
        val requestBody = FeatureToggleRequest(id = "2", name = "test2")
        given()
            .contentType("application/json")
            .body(requestBody)
            .`when`().post("/api/featuretoggle")
            .then().statusCode(201)
    }

    @Test
    fun `DELETE request deletes feature toggle with id`() {
        given()
            .contentType("application/json")
            .`when`().delete("/api/featuretoggle/333")
            .then().statusCode(204)
    }
}
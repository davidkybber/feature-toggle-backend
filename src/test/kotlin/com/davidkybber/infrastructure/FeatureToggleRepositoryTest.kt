package com.davidkybber.infrastructure

import arrow.core.right
import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

@QuarkusTest
class FeatureToggleRepositoryTest {
    @Inject
    lateinit var featureToggleRepository: DatabaseFeatureToggleRepository

    @Test
    fun `find toggle by id returns toggle`() {
        val featureToggle = featureToggleRepository.fetchFeatureToggle("1")
        featureToggle.fold(
            {
                fail("Couldn't find the user")
            },
            {
                assertTrue(it.name == "test toggle")
            }
        )
    }
}

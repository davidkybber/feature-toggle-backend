package com.davidkybber.infrastructure

import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@QuarkusTest
class FeatureToggleRepositoryTest {
    @Inject
    lateinit var featureToggleRepository: FeatureToggleRepository

    @Test
    fun `find toggle by name returns toggle`() {
        val featureToggle = featureToggleRepository.findByFeatureToggle("test toggle")
        assertTrue(featureToggle?.name == "test toggle")
    }

    @Test
    fun `find toggle by id returns toggle`() {
        val featureToggle = featureToggleRepository.findById("1")
        assertTrue(featureToggle?.id == "1")
    }
}

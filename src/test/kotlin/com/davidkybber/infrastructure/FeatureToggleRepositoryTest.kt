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
        val thing = featureToggleRepository.findByFeatureToggle("test toggle")
        assertTrue(thing?.id == "1")
    }
}
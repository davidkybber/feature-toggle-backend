package com.davidkybber.application

import com.davidkybber.core.models.FeatureToggle
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HandleFeatureTogglesUseCaseImpl(
    val featureToggleRepository: FeatureToggleRepository
): HandleFeatureTogglesUseCase {
    override fun getFeatureToggle(name: String): FeatureToggle {
        return featureToggleRepository.fetchFeatureToggle(name) ?: throw Exception("feature toggle doesn't exist")
    }

    override fun getAllFeatureToggles(): List<FeatureToggle> {
        return featureToggleRepository.fetchAllFeatureToggles()
    }

    override fun deleteFeatureToggle(featureToggleId: String) {
        featureToggleRepository.removeFeatureToggle(featureToggleId)
    }

    override fun addFeatureToggle(featureToggle: FeatureToggle) {
        featureToggleRepository.persistFeatureToggle(featureToggle)
    }
}
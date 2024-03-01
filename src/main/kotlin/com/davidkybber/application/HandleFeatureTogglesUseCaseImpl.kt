package com.davidkybber.application

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.davidkybber.core.FeatureToggleRepository
import com.davidkybber.core.exceptions.FeatureToggleAlreadyExists
import com.davidkybber.core.exceptions.FeatureToggleNotFoundException
import com.davidkybber.core.models.FeatureToggle
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class HandleFeatureTogglesUseCaseImpl(
    val featureToggleRepository: FeatureToggleRepository
): HandleFeatureTogglesUseCase {
    override fun getFeatureToggle(id: String): Either<FeatureToggleNotFoundException, FeatureToggle> {
        return featureToggleRepository.fetchFeatureToggle(id)
    }

    override fun getAllFeatureToggles(): List<FeatureToggle> {
        return featureToggleRepository.fetchAllFeatureToggles()
    }

    override fun deleteFeatureToggle(featureToggleId: String) {
        featureToggleRepository.removeFeatureToggle(featureToggleId)
    }

    override fun addFeatureToggle(featureToggleName: String): Either<FeatureToggleAlreadyExists, String> {
        val id = UUID.randomUUID().toString()
        val featureToggle = FeatureToggle(id = id, name = featureToggleName)
        val featureToggleExistsResult = featureToggleRepository.fetchFeatureToggleByName(featureToggleName)

        return either {
            ensure(featureToggleExistsResult.isLeft()) {
                FeatureToggleAlreadyExists()
            }

            featureToggleRepository.persistFeatureToggle(featureToggle)
            id
        }
    }
}
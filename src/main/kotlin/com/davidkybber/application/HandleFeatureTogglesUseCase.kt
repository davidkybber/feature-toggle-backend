package com.davidkybber.application

import arrow.core.Either
import com.davidkybber.core.exceptions.FeatureToggleNotFoundException
import com.davidkybber.core.models.FeatureToggle

interface HandleFeatureTogglesUseCase {
    fun getFeatureToggle(id: String): Either<FeatureToggleNotFoundException, FeatureToggle>

    fun getAllFeatureToggles(): List<FeatureToggle>

    fun deleteFeatureToggle(featureToggleId: String)

    fun addFeatureToggle(featureToggleName: String): String
}
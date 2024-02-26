package com.davidkybber.application

import com.davidkybber.core.models.FeatureToggle

interface HandleFeatureTogglesUseCase {
    fun getFeatureToggle(name: String): FeatureToggle

    fun getAllFeatureToggles(): List<FeatureToggle>

    fun deleteFeatureToggle(featureToggleId: String)

    fun addFeatureToggle(featureToggle: FeatureToggle)
}
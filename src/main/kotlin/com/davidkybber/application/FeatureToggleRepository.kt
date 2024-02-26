package com.davidkybber.application

import com.davidkybber.core.models.FeatureToggle

interface FeatureToggleRepository {
    fun fetchFeatureToggle(name: String): FeatureToggle?

    fun fetchAllFeatureToggles(): List<FeatureToggle>

    fun persistFeatureToggle(featureToggle: FeatureToggle)

    fun removeFeatureToggle(featureToggleId: String)
}
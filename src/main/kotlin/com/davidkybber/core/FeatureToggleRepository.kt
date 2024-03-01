package com.davidkybber.core

import arrow.core.Either
import com.davidkybber.core.exceptions.FeatureToggleNotFoundException
import com.davidkybber.core.models.FeatureToggle

interface FeatureToggleRepository {
    fun fetchFeatureToggle(id: String): Either<FeatureToggleNotFoundException, FeatureToggle>

    fun fetchFeatureToggleByName(name: String): Either<FeatureToggleNotFoundException, FeatureToggle>

    fun fetchAllFeatureToggles(): List<FeatureToggle>

    fun persistFeatureToggle(featureToggle: FeatureToggle)

    fun removeFeatureToggle(featureToggleId: String)
}
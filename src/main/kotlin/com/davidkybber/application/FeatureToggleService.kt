package com.davidkybber.application

import com.davidkybber.core.models.FeatureToggle
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FeatureToggleService {
    fun getFeatureToggle(name: String): FeatureToggle {
        return FeatureToggle(id = "2", name = "test")
    }
}
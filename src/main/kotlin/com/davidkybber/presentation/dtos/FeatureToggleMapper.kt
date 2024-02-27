package com.davidkybber.presentation.dtos

import com.davidkybber.core.models.FeatureToggle

fun FeatureToggleRequest.toDomainModel(): FeatureToggle {
    return FeatureToggle(id = this.id, name = this.name)
}

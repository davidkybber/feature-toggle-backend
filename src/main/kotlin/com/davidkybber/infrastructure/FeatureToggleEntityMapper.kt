package com.davidkybber.infrastructure

import com.davidkybber.core.models.FeatureToggle
import com.davidkybber.infrastructure.entities.FeatureToggleEntity

fun FeatureToggleEntity.toDomainModel(): FeatureToggle {
    return FeatureToggle(id = this.id ?: "error", name = this.name ?: "undefined")
}


fun FeatureToggle.toEntity(): FeatureToggleEntity {
    val featureToggleEntity = FeatureToggleEntity()
    featureToggleEntity.apply {
        this.id = this@toEntity.id
        this.name = this@toEntity.name
    }
    return featureToggleEntity
}
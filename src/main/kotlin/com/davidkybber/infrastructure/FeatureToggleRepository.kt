package com.davidkybber.infrastructure

import com.davidkybber.infrastructure.entities.FeatureToggleEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FeatureToggleRepository: PanacheRepository<FeatureToggleEntity> {
    fun findByFeatureToggle(toggleName: String): FeatureToggleEntity? {
        return find("name", toggleName).firstResult()
    }
}
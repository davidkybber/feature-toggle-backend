package com.davidkybber.infrastructure

import com.davidkybber.infrastructure.entities.FeatureToggleEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FeatureToggleRepository: PanacheRepositoryBase<FeatureToggleEntity, String> {
    fun findByFeatureToggle(toggleName: String): FeatureToggleEntity? {
        return find("name", toggleName).firstResult()
    }
}
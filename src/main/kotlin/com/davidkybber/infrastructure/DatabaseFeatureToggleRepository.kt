package com.davidkybber.infrastructure

import com.davidkybber.application.FeatureToggleRepository
import com.davidkybber.core.models.FeatureToggle
import com.davidkybber.infrastructure.entities.FeatureToggleEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional

@ApplicationScoped
class DatabaseFeatureToggleRepository: FeatureToggleRepository, PanacheRepositoryBase<FeatureToggleEntity, String> {
    override fun fetchFeatureToggle(name: String): FeatureToggle? {
        val featureToggleEntity =  find("name", name).firstResult()
        return featureToggleEntity?.toDomainModel()
    }

    override fun fetchAllFeatureToggles(): List<FeatureToggle> {
        return findAll().list().map {
            it.toDomainModel()
        }
    }

    @Transactional
    override fun persistFeatureToggle(featureToggle: FeatureToggle) {
        persist(featureToggle.toEntity())
    }

    @Transactional
    override fun removeFeatureToggle(featureToggleId: String) {
        deleteById(featureToggleId)
    }

}
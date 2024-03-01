package com.davidkybber.infrastructure

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.davidkybber.core.FeatureToggleRepository
import com.davidkybber.core.exceptions.FeatureToggleNotFoundException
import com.davidkybber.core.models.FeatureToggle
import com.davidkybber.infrastructure.entities.FeatureToggleEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional

@ApplicationScoped
class DatabaseFeatureToggleRepository: FeatureToggleRepository, PanacheRepositoryBase<FeatureToggleEntity, String> {
    override fun fetchFeatureToggle(id: String): Either<FeatureToggleNotFoundException, FeatureToggle> =
        either {
            val featureToggleEntity =  findById(id)
            ensure(
                featureToggleEntity != null
            ) {
                FeatureToggleNotFoundException()
            }
            featureToggleEntity.toDomainModel()
        }

    override fun fetchFeatureToggleByName(name: String): Either<FeatureToggleNotFoundException, FeatureToggle> =
        either {
            val featureToggleEntity = find("name", name).firstResult()
            ensure(
                featureToggleEntity != null
            ) {
                FeatureToggleNotFoundException()
            }

            featureToggleEntity.toDomainModel()
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
package com.davidkybber.infrastructure.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "feature_toggle")
class FeatureToggleEntity {
    @Id
    lateinit var id: String
    lateinit var name: String
}
package com.davidkybber.core.models

class FeatureToggle(
    val id: String,
    var name: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as FeatureToggle

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "FeatureToggle(id=$id, name=$name"
    }
}

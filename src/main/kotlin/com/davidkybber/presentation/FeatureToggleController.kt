package com.davidkybber.presentation

import com.davidkybber.presentation.dtos.FeatureToggleDto
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import kotlin.random.Random

@Path("/api/featuretoggle")
class FeatureToggleController {
    val featureToggles = mutableListOf(FeatureToggleDto("toggle1", "1"))

    @GET
    fun getFeatureToggles(): Response {
        return Response.ok(featureToggles).build()
    }

    @POST
    fun saveFeatureToggle(featureToggle: FeatureToggleDto): Response {
        featureToggle.id = Random.nextInt(1, 1000000).toString()
        featureToggles.add(featureToggle)
        return Response.ok("${featureToggle.featureToggle} has been added to the feature toggles list.").build()
    }

    @DELETE
    fun deleteFeatureToggle(featureToggle: FeatureToggleDto): Response {
        featureToggles.removeAll { it.id == featureToggle.id }
        return Response.ok("Deleted toggle with id ${featureToggle.id}").build()
    }
}

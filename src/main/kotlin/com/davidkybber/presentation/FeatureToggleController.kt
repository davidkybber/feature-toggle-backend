package com.davidkybber.presentation

import com.davidkybber.application.HandleFeatureTogglesUseCase
import com.davidkybber.presentation.dtos.FeatureToggleDto
import com.davidkybber.presentation.dtos.toDomainModel
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import kotlin.random.Random

@Path("/api/featuretoggle")
class FeatureToggleController(
    val handleFeatureTogglesUseCase: HandleFeatureTogglesUseCase
) {
    @GET
    fun getFeatureToggles(): Response {
        val featureToggles = handleFeatureTogglesUseCase.getAllFeatureToggles()
        return Response.ok(featureToggles).build()
    }

    @POST
    fun saveFeatureToggle(featureToggleDto: FeatureToggleDto): Response {
        featureToggleDto.id = Random.nextInt(1, 1000000).toString()
        handleFeatureTogglesUseCase.addFeatureToggle(featureToggleDto.toDomainModel())
        return Response.ok("${featureToggleDto.name} has been added to the feature toggles list.").build()
    }

    @DELETE
    @Path("/{id}")
    fun deleteFeatureToggle(@PathParam("id") id: String): Response {
        handleFeatureTogglesUseCase.deleteFeatureToggle(id)
        return Response.ok("Deleted toggle with id $id").build()
    }
}

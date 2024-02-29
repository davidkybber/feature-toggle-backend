package com.davidkybber.presentation

import com.davidkybber.application.HandleFeatureTogglesUseCase
import com.davidkybber.presentation.dtos.FeatureToggleRequest
import com.davidkybber.presentation.dtos.toDomainModel
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriBuilder

@Path("/api/featuretoggle")
class FeatureToggleController(
    val handleFeatureTogglesUseCase: HandleFeatureTogglesUseCase
) {
    // TODO: Proper error handling in the API
    // TODO: Redesign how the ID is generated, shouldn't come from the API
    @GET
    @Path("/{id}")
    fun getFeatureToggle(@PathParam("id") id: String): Response {
        val featureToggle = handleFeatureTogglesUseCase.getFeatureToggle(id)
        return featureToggle.fold(
            {
                Response.status(Response.Status.BAD_REQUEST).entity("No feature toggle found with that ID").build()
            },
            {
                Response.ok(featureToggle).build()
            }
        )
    }

    @GET
    fun getFeatureToggles(): Response {
        val featureToggles = handleFeatureTogglesUseCase.getAllFeatureToggles()
        return Response.ok(featureToggles).build()
    }

    @POST
    fun saveFeatureToggle(featureToggleRequest: FeatureToggleRequest): Response {
        handleFeatureTogglesUseCase.addFeatureToggle(featureToggleRequest.toDomainModel())

        val uri = UriBuilder.fromResource(FeatureToggleController::class.java)
            .path(featureToggleRequest.id)
            .build()

        return Response.created(uri).build()
    }

    @DELETE
    @Path("/{id}")
    fun deleteFeatureToggle(@PathParam("id") id: String): Response {
        handleFeatureTogglesUseCase.deleteFeatureToggle(id)
        return Response.noContent().build()
    }
}

package com.davidkybber.presentation

import com.davidkybber.application.HandleFeatureTogglesUseCase
import com.davidkybber.presentation.dtos.FeatureToggleRequest
import com.davidkybber.presentation.dtos.toDomainModel
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriBuilder
import java.net.URI

@Path("/api/featuretoggle")
class FeatureToggleController(
    val handleFeatureTogglesUseCase: HandleFeatureTogglesUseCase
) {
    // TODO: Proper error handling in the API
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

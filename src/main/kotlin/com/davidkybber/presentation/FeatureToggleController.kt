package com.davidkybber.presentation

import com.davidkybber.application.HandleFeatureTogglesUseCase
import com.davidkybber.presentation.dtos.FeatureToggleRequest
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriBuilder

@Path("/api/featuretoggle")
class FeatureToggleController(
    val handleFeatureTogglesUseCase: HandleFeatureTogglesUseCase
) {
    // TODO: Handle invalid data for the Post requests
    @GET
    @Path("/{id}")
    fun getFeatureToggle(@PathParam("id") id: String): Response {
        val featureToggleResult = handleFeatureTogglesUseCase.getFeatureToggle(id)
        return featureToggleResult.fold(
            {
                Response.status(Response.Status.BAD_REQUEST).entity("No feature toggle found with that ID").build()
            },
            {
                Response.ok(it).build()
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

        val newToggleId = handleFeatureTogglesUseCase.addFeatureToggle(featureToggleRequest.name)

        val uri = UriBuilder.fromResource(FeatureToggleController::class.java)
            .path(newToggleId)
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

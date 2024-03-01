package com.davidkybber.presentation

import com.davidkybber.application.HandleFeatureTogglesUseCase
import com.davidkybber.presentation.dtos.FeatureToggleRequest
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriBuilder

@Path("/api/featuretoggle")
class FeatureToggleController(
    val handleFeatureTogglesUseCase: HandleFeatureTogglesUseCase,
) {
    // TODO: Get backend into production
    @GET
    @Path("/{id}")
    fun getFeatureToggle(
        @PathParam("id") id: String,
    ): Response {
        val featureToggleResult = handleFeatureTogglesUseCase.getFeatureToggle(id)
        return featureToggleResult.fold(
            {
                Response.status(Response.Status.BAD_REQUEST).entity("No feature toggle found with that ID").build()
            },
            {
                Response.ok(it).build()
            },
        )
    }

    @GET
    fun getFeatureToggles(): Response {
        val featureToggles = handleFeatureTogglesUseCase.getAllFeatureToggles()
        return Response.ok(featureToggles).build()
    }

    @POST
    fun saveFeatureToggle(featureToggleRequest: FeatureToggleRequest): Response {
        val addToggleResult = handleFeatureTogglesUseCase.addFeatureToggle(featureToggleRequest.name)

        return addToggleResult.fold(
            {
                Response.status(Response.Status.BAD_REQUEST).entity("Feature toggle already exists.").build()
            },
            {
                val uri =
                    UriBuilder.fromResource(FeatureToggleController::class.java)
                        .path(it)
                        .build()

                Response.created(uri).build()
            },
        )
    }

    @DELETE
    @Path("/{id}")
    fun deleteFeatureToggle(
        @PathParam("id") id: String,
    ): Response {
        handleFeatureTogglesUseCase.deleteFeatureToggle(id)
        return Response.noContent().build()
    }
}

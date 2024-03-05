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
import org.jboss.logging.Logger

@Path("/api/featuretoggle")
class FeatureToggleController(
    val handleFeatureTogglesUseCase: HandleFeatureTogglesUseCase,
    var log: Logger,
) {
    // TODO: Get backend into production
    // - Add proper logging
    // - Send app to the hosting service, through created pipeline
    // - Add proper monitoring

    @GET
    @Path("/{id}")
    fun getFeatureToggle(
        @PathParam("id") id: String,
    ): Response {
        log.info("Received id: $id, to be retrieved from the system.")
        val featureToggleResult = handleFeatureTogglesUseCase.getFeatureToggle(id)
        return featureToggleResult.fold(
            {
                log.info("Failed to find feature toggle with the ID: $id. No such feature toggle in the system.")
                Response.status(Response.Status.BAD_REQUEST).entity("No feature toggle found with that ID").build()
            },
            {
                log.info("Feature toggle to be returned: $it")
                Response.ok(it).build()
            },
        )
    }

    @GET
    fun getFeatureToggles(): Response {
        log.info("Retrieving all feature toggles.")
        val featureToggles = handleFeatureTogglesUseCase.getAllFeatureToggles()
        log.info("Feature toggles in system: $featureToggles")
        return Response.ok(featureToggles).build()
    }

    @POST
    fun saveFeatureToggle(featureToggleRequest: FeatureToggleRequest): Response {
        log.info("Toggle request to save toggle: $featureToggleRequest")
        val addToggleResult = handleFeatureTogglesUseCase.addFeatureToggle(featureToggleRequest.name)

        return addToggleResult.fold(
            {
                log.info("Feature toggle already exists, won't be added.")
                Response.status(Response.Status.BAD_REQUEST).entity("Feature toggle already exists.").build()
            },
            {
                log.info("Toggle has been saved in database.")
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
        log.info("Deleting toggle with id: $id")
        handleFeatureTogglesUseCase.deleteFeatureToggle(id)
        return Response.noContent().build()
    }
}

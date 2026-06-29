package com.mlops.api.resources;

import com.mlops.api.models.MachineLearningModel;
import com.mlops.api.storage.DataStore;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/models")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModelResource {
    @Path("/{modelId}/metrics")
    public EvaluationMetricResource getMetricsResource(@PathParam("modelId") String modelId) {
        return new EvaluationMetricResource(modelId);
    }

    // GET all models + optional filter
    @GET
    public Collection<MachineLearningModel> getModels(@QueryParam("status") String status) {

        if (status == null) {
            return DataStore.models.values();
        }

        return DataStore.models.values()
                .stream()
                .filter(m -> m.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());

    }

    // CREATE model
    @POST
    public Response createModel(MachineLearningModel model) {

        // Validate workspace exists
        if (!DataStore.workspaces.containsKey(model.getWorkspaceId())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Workspace does not exist")
                    .build();
        }

        // Generate server-side ID (IMPORTANT for marks)
        String id = UUID.randomUUID().toString();
        model.setId(id);

        DataStore.models.put(id, model);

        return Response.status(Response.Status.CREATED)
                .entity(model)
                .build();
    }

    // GET single model
    @GET
    @Path("/{modelId}")
    public Response getModel(@PathParam("modelId") String modelId) {

        MachineLearningModel model = DataStore.models.get(modelId);

        if (model == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Model not found")
                    .build();
        }

        return Response.ok(model).build();
    }

    // DELETE model (basic version)
    @DELETE
    @Path("/{modelId}")
    public Response deleteModel(@PathParam("modelId") String modelId) {

        MachineLearningModel model = DataStore.models.get(modelId);

        if (model == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Model not found")
                    .build();
        }

        DataStore.models.remove(modelId);

        return Response.ok("Model deleted successfully").build();
    }
}
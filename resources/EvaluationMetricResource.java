package com.mlops.api.resources;

import com.mlops.api.models.EvaluationMetric;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EvaluationMetricResource {

    private String modelId;

    private static Map<String, List<EvaluationMetric>> store = new HashMap<>();

    public EvaluationMetricResource(String modelId) {
        this.modelId = modelId;
    }

    // GET metrics for a model
    @GET
    public List<EvaluationMetric> getMetrics() {
        return store.getOrDefault(modelId, new ArrayList<>());
    }

    // ADD metric
    @POST
    public Response addMetric(EvaluationMetric metric) {

        metric.setId(UUID.randomUUID().toString());

        store.putIfAbsent(modelId, new ArrayList<>());
        store.get(modelId).add(metric);

        return Response.status(Response.Status.CREATED)
                .entity(metric)
                .build();
    }
    @DELETE
    public Response deleteMetrics() {

        List<EvaluationMetric> removed = store.remove(modelId);

        if (removed == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No metrics found for this model")
                    .build();
        }

        return Response.ok("Metrics deleted successfully").build();
    }
}
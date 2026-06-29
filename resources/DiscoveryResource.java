package com.mlops.api.resources;

import com.mlops.api.models.ApiInfo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {

    @GET
    public ApiInfo getInfo() {

        return new ApiInfo(
                "v1",
                "admin@mlops.com",
                Map.of(
                        "workspaces", "/api/v1/workspaces",
                        "models", "/api/v1/models"
                )
        );
    }
}

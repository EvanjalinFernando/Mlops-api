package com.mlops.api.resources;

import com.mlops.api.models.Workspace;
import com.mlops.api.storage.DataStore;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;
import java.util.UUID;

@Path("/workspaces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkspaceResource {

    // GET ALL
    @GET
    public Collection<Workspace> getAllWorkspaces() {
        return DataStore.workspaces.values();
    }

    // GET BY ID
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {

        Workspace ws = DataStore.workspaces.get(id);

        if (ws == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Workspace not found")
                    .build();
        }

        return Response.ok(ws).build();
    }

    // CREATE
    @POST
    public Response createWorkspace(Workspace workspace) {

        String id = UUID.randomUUID().toString();
        workspace.setId(id);

        DataStore.workspaces.put(id, workspace);

        return Response.status(Response.Status.CREATED)
                .entity(workspace)
                .build();
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    public Response deleteWorkspace(@PathParam("id") String id) {

        Workspace removed = DataStore.workspaces.remove(id);

        if (removed == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Workspace not found")
                    .build();
        }

        return Response.ok("Workspace deleted successfully").build();
    }
}
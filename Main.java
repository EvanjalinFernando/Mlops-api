package com.mlops.api;

import com.mlops.api.models.Workspace;
import com.mlops.api.storage.DataStore;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {

    public static final String BASE_URI =
            "http://localhost:8081/api/v1/";

    public static void main(String[] args) throws Exception {


        Workspace ws = new Workspace();
        ws.setId("w1");
        ws.setName("Test Workspace");

        DataStore.workspaces.put("w1", ws);

        System.out.println("Workspace count: " + DataStore.workspaces.size());


        HttpServer server = startServer();

        System.out.println("Server running at " + BASE_URI);

        Thread.currentThread().join();
    }

    public static HttpServer startServer() {

        ResourceConfig config = new ResourceConfig()
                .packages("com.mlops.api.resources");

        return GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI),
                config
        );
    }
}
package com.mlops.api.models;

public class MachineLearningModel {

    private String id;
    private String name;
    private String workspaceId;
    private String status;
    private double latestAccuracy;

    public MachineLearningModel() {}

    public MachineLearningModel(String id, String name, String workspaceId, String status) {
        this.id = id;
        this.name = name;
        this.workspaceId = workspaceId;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(String workspaceId) { this.workspaceId = workspaceId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getLatestAccuracy() { return latestAccuracy; }
    public void setLatestAccuracy(double latestAccuracy) { this.latestAccuracy = latestAccuracy; }
}
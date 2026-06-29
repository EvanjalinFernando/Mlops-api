MLOps API – Workspace, Model & Metrics Management System

 Project Overview

This project is a RESTful MLOps API built using JAX-RS (Jakarta RESTful Web Services) and Grizzly HTTP server.  
It provides a lightweight backend system for managing:

- Workspaces
- Machine Learning Models
- Evaluation Metrics (Sub-resource design)

The system uses in-memory data storage (`HashMap`) instead of a database as required by the coursework specification.



Technology Stack

- Java 17
- JAX-RS (Jakarta WS RS API)
- Jersey (Reference implementation)
- Grizzly HTTP Server
- Maven
- In-memory storage (HashMap)



 How to Run the Project

1. Clone the Repository
```bash
git clone https://github.com/your-username/mlops-api.git
cd mlops-api

2. Build the Project
mvn clean install

3. Run the Server
Run the Main.java file from your IDE or use:
mvn exec:java

4. Server Base URL
http://localhost:8081/api/v1/

 API Design Overview

The API is structured into 3 main layers:

1. Workspace Resource

Manages ML workspaces.

Base Path:

/workspaces
2. Model Resource

Manages machine learning models inside a workspace.

Base Path:

/models
3. Evaluation Metrics (Sub-resource)

Nested under models.

Base Path:

/models/{modelId}/metrics
 Data Storage Design

Instead of a database, this project uses:

DataStore.workspaces -> HashMap<String, Workspace>
DataStore.models -> HashMap<String, MachineLearningModel>
DataStore.metrics -> HashMap<String, List<EvaluationMetric>>

This ensures fast in-memory operations as required by coursework constraints.

 API Endpoints
 Workspaces
Get All Workspaces
GET /workspaces
Get Workspace by ID
GET /workspaces/{id}
Create Workspace
POST /workspaces
Delete Workspace
DELETE /workspaces/{id}
 Models
Get All Models
GET /models
Filter Models by Status
GET /models?status=ACTIVE
Create Model
POST /models
Get Model by ID
GET /models/{modelId}
 Evaluation Metrics (Sub-resource)
Access Metrics for a Model
GET /models/{modelId}/metrics

This is implemented using sub-resource locator pattern:

@Path("/{modelId}/metrics")
public EvaluationMetricResource getMetricsResource(@PathParam("modelId") String modelId) {
    return new EvaluationMetricResource(modelId);
}
 Key Features Implemented
REST API using JAX-RS
Full CRUD for Workspaces
Model creation and filtering
Sub-resource design for metrics
In-memory data storage (HashMap)
UUID generation for entities
Proper HTTP response codes (200, 201, 404, 422)
 Error Handling

The API handles:

Workspace not found → 404 Not Found
Invalid workspace ID → 422 Unprocessable Entity
Null request body handling (fixed)
Proper JSON responses
 Sample cURL Commands
Create Workspace
curl -X POST http://localhost:8081/api/v1/workspaces \
-H "Content-Type: application/json" \
-d '{"name":"Test Workspace"}'
Get All Workspaces
curl http://localhost:8081/api/v1/workspaces
Create Model
curl -X POST http://localhost:8081/api/v1/models \
-H "Content-Type: application/json" \
-d '{"name":"Test Model","status":"ACTIVE","workspaceId":"w1"}'
Get Models
curl http://localhost:8081/api/v1/models
Get Model by ID
curl http://localhost:8081/api/v1/models/{modelId}
 Project Architecture
Main.java → Starts Grizzly Server
   ↓
ResourceConfig → loads resources
   ↓
WorkspaceResource
ModelResource
EvaluationMetricResource (Sub-resource)
   ↓
DataStore (HashMap storage)

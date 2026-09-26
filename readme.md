# This is a test project build to perform kubernetes practice.

### Global API Calls:
* GET `api/v1/info`
  * For getting the pod ip address and pod name.

### Level 1
Basic API calls:
* GET `api/v1/users`
* GET `api/v1/users/{id}`
* POST `api/v1/users`
* PUT `api/v1/users/{id}`
* DELETE `api/v1/users/{id}`


### Level 2
Creating a frontend for level 1 service. And then exposing both using a nginx ingress.


```mermaid
flowchart TD
    A[Internet] --> B[AWS Application Load Balancer]
    B --> C[Kubernetes EC2 Nodes]
    C --> D[NodePort]
    D --> E[Ingress Controller<br/>NGINX]
    E --> F["/"]
    E --> G["/api"]
    F --> H["Frontend Service"]
    H --> J[Frontend Pods]
    G --> I["Backend Service"]
    I --> K[Backend Pods]
    style A fill: #166534, stroke: #14532D, color: #FFFFFF
    style B fill: #1D4ED8, stroke: #1E3A8A, color: #FFFFFF
    style C fill: #B91C1C, stroke: #7F1D1D, color: #FFFFFF
    style D fill: #FACC15, stroke: #A16207, color: #000000
    style E fill: #0F766E, stroke: #134E4A, color: #FFFFFF
    classDef service fill: #2563EB, stroke: #1E3A8A, color: #FFFFFF
    classDef pods fill: #DB2777, stroke: #831843, color: #FFFFFF
    class F,H,I,G service;
    class J,K pods;

```
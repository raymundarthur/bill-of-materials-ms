# Bill Of Materials Service

A sample microservice with a simple REST Endpoint, Swagger UI, and HATEOAS 

### Problem Statement: 

For a given a product "P", nested dependecies from other product/components exists in order to build it. 
We need a way to generate a unique lists of all its dependencies.

Take the product dependency map given below:

```
├───────────────────────────────|
|  Product     |  Requirement   |
├───────────────────────────────|
|  P           |      A         |
├───────────────────────────────|
|  P           |      B         |
├───────────────────────────────|
|  P           |      C         |
├───────────────────────────────|
|  A           |      D         |
├───────────────────────────────|
|  A           |      E         |
├───────────────────────────────|
|  B           |      E         |
├───────────────────────────────|
|  B           |      F         |
├───────────────────────────────|
|  B           |      G         |
├───────────────────────────────|
|  C           |      G         |
├───────────────────────────────|
|  C           |      H         |
├───────────────────────────────|
|  D           |      I         |
├───────────────────────────────|

```

### Solution

To generate a dependency list (or bill of materials), we need to:

1. Convert the input map above and convert it to a tree-structure
2. Tree-walk the nodes and gather the component names via a unique list as you traverse it

This is shown on: [ComponentServiceImpl.class](https://github.com/raymundarthur/bill-of-materials-ms/blob/master/src/main/java/com/raymund/bom/service/ComponentServiceImpl.java)


# Prerequisites

+ Java JDK 1.8
+ Apache Maven 3.3+


# How to Build and Run

Build, Test, and Run using command:

```
mvn spring-boot:run

```


# Examples

Get the dependency list for product P:
```
curl -X GET "http://localhost:8080/api/v1/components/P/bom" -H "accept: application/json;charset=UTF-8"
```

Result is:
```
{
  "component": "P",
  "requirementSet": [
    "A",
    "B",
    "C",
    "D",
    "E",
    "F",
    "G",
    "H",
    "I"
  ],
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/v1/components/P/bom"
    }
  }
}
```

Provide a screenshot here





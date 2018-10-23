# Bill Of Materials Service

A sample microservice with a simple REST Endpoint and In-mem H2 database

### Requirement Description: 

Given a product "P", assume that we can build from a list or components.
However, each required component also needs to be built separately and also provides a list of sub-components as a dependency.

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

Then, if we want to generate the requirement list of product P, a service must be able to generate a unique list of required components to build it depending on the map shown above.


### Prerequisites

+ Java JDK 1.8
+ Apache Maven 3.3+


### How to Build and Run

Build, Test, and Run using command:

```
mvn spring-boot:run

```


### Examples

Provide a sample build/test output here;

Provide a screenshot here





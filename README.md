
### Operating requirements
* [Download Java SDK 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
* [Download Apache Maven 3.8.4](https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.zip)

#### Checking java installation

Define/Export: JAVA_HOME indicates the java installation path

java -version

Define/Export: no PATH "../apache-maven-3.8.4/bin"

mvn -version

### IDEs for working with java
* [Download Intellj Community ou Ultimate](https://www.jetbrains.com/pt-br/idea/download/other.html)
* [Download Eclipse IDE](https://www.eclipse.org/downloads/)


### Compiling and Running the Project

#### Compiling

mvn clean install package -U -DskipTests
___
Created file name:
desafiopubfuture-0.0.1-SNAPSHOT.jar

*file created in "../target/"
___
#### Execution

cd ../desafiopubfuture/target/

java -jar desafiopubfuture-0.0.1-SNAPSHOT.jar


#### API access
http://localhost:8000


### Versioning control

GIT e GITHub


### Functionalities
- [x] CRUD Contas
- [x] CRUD Despesas
- [x] CRUD Receitas
- [x] CRUD Pessoas
- [x] Transferência de saldos
- [x] Baixa de pagamentos
- [x] Baixa de receitas

### Access to API documentation and methods
http://localhost:8000/swagger-ui.html

### Dependencies

//Spring Boot

org.springframework.boot

//H2 database

com.h2database

//Lombok

org.projectlombok

//Swagger

springfox-swagger2

//Model Mapper

org.modelmapper


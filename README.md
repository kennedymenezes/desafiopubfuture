# Para compilar e executar o projeto

### Requisitos para funcionamento
* [Download Java SDK 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
* [Download Apache Maven 3.8.4](https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.zip)

#### Verificando instalação do java
Definir/Exportar: JAVA_HOME indica o path de instalação do java

java -version

Definir/Exportar: no PATH "../apache-maven-3.8.4/bin"

mvn -version

### IDEs para trabalhar com java
* [Download Intellj Community ou Ultimate](https://www.jetbrains.com/pt-br/idea/download/other.html)
* [Download Eclipse IDE](https://www.eclipse.org/downloads/)

 
### Compilando e Executando o projeto

#### Compilação 

mvn clean install package -U -DskipTests
___
Nome do arquivo criado:
desafiopubfuture-0.0.1-SNAPSHOT.jar

*arquivo criado em "../target/"
___
#### Execução

cd ../desafiopubfuture/target/

java -jar desafiopubfuture-0.0.1-SNAPSHOT.jar

 

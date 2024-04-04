# Starting a Ktor Server

This guide outlines how to start a Ktor server using the `pavicon.jar` file and provides the configuration details needed.

## Prerequisites

- Java Development Kit (JDK) installed on your machine
- `pavicon.jar` file available

## Starting the Ktor Server

To start the Ktor server, follow these steps:


1. **Configure the server**:
   
   Add the following configuration details to your application:

   ```kotlin
   object Config {
       val databaseUrl = "mongodb+srv://PascarlDevDB:Pasaka02105416001@pascarlsdevdb.sorce9p.mongodb.net/?retryWrites=true&w=majority&appName=PascarlsDevDB"
       val databaseName = "PaviconTech"
       val USERNAME = "admin@coinx.co.ke"
       val PASSWORD = "(-5VLeN3L5tbd7"
       val SMTP_SERVER_ADDRESS  = "sm1.cloudoon.com"
       val apiVersionBranch = "api/v1/"
   }

2. build the Jar file i.e ./gradlew buildFatJat
3. Run the fat jar ./gradlew runFatJar

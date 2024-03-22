# Introduction 

This is a template java project built on the principles of 
- Hexagonal architecture
  - clean separation between the core business logic & external interfaces.
- Command Query Responsibility Segregation and 
  - separates read & write operations, allowing for better scalability & optimization.
- Utilizes OpenAPI for API generation
  - used for API documentation & generation, providing a standardized way to describe & define RESTful APIs.

    

# Getting Started
By default, the app is configured to run with the `local` spring profile and to use in-memory H2 database. You can change this
in the `infrastructure-data/src/main/resources/application.properties` or by providing a spring profile's name at startup.


# Build and Test
1. Build the gradle project using  the command line `./gradlew clean assemble` or from the IDE 
2. Run the application by one of the following ways: 
   1. Run the main class com.launch.template.service.JavaTemplateApplication in the IDE
   2. Execute the `./gradlew bootRun` in the CLI. 
   3. Run the application in docker with postgres
      - docker build . -t {dockerusername}/launch-api:s1
        - make sure to update the same image name in docker-compose.yml file
      - docker-compose up: Create and start containers defined in a Docker Compose file.
      - docker-compose down: Stop and remove containers defined in a Docker Compose file.
      - use PgAdmin service to view the table & daa
        - https://medium.com/@marvinjungre/get-postgresql-and-pgadmin-4-up-and-running-with-docker-4a8d81048aea
3. If you are using in-memory H2 database, you can connect to the H2 console: http://localhost:8080/h2-console (no password needed, just click "Connect").
4. Connect to the actuator with the credentials "test/test": http://localhost:8080/actuator
5. Open the API Docs: http://localhost:8080/swagger-ui.html


# Create and push the docker image to ACR

- Login to Azure container registry from azure CLI
  - `az acr login --name launchhexappregistry`
  - username & password - login to azure, navigate to launchhexappregistry > access keys
- Create the image > `docker build . -t launchhexappregistry.azurecr.io/java-template-service`
- Push the image to acr > `docker push launchhexappregistry.azurecr.io/java-template-service`

# Create & push the image using Jib plugin
1. Run the command  `./gradlew jibDockerBuild`

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)
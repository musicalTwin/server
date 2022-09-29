# How to run

It connects to a database which path is specified in `application.properties`, and it creates its scheme automatically following declared models.
We manually set the values on the `genres` and `genders` tables.
Compile and run the jar for production and i suggest to use the Visual Studio Code's Java extension for debugging.
For running the server, in the root folder, run `.\mvnw.cmd spring-boot:run`

# Info

It runs on port `5000`, also specified in `application.properties`. <br />
Docs url: `http://localhost:5000/swagger-ui/index.html`

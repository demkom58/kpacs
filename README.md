# Test task
Test task solution for the position of Java Full-stack Developer at AurosKS.

## Launch

To start up project execute the following command:

```bash
docker-compose up
```

It will automatically build the project and run it in the Docker container
along with MySQL database. Service will be available at `http://localhost:8080`.

*Docker compose also automatically creates schema and inserts test data 
to MySQL database.*

## Notes

There is no Spock tests for the project, but they are configured and
`HelloWorldSpec` test is available in the `src/test/groovy` directory.
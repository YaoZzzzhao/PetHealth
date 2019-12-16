# CICD Process

This file will show the specific procedures of CICD Deployment. The outline is displayed as below:
- Push the source code to github by using git;
- Fullfill unit test stage in Docker container;
- Package **.war** file in Docker container;
- Build Docker image with **.war** file and Dockerfile;
- Launch containeried application successfully.

## Push code to github
Use git to push the most current version code to github and ensure that no credentials info included in your source code(you can set all sensetive info as environment variables).

## Make sure all the tests pass in the Docker container
Firstly, I retrieve maven:3.6.0-jdk-8 image
```bash
docker pull maven:3.6.0-jdk-8
```
Then run a container on this image and interact with it
```bash
docker run -it maven:3.6.0-jdk-8 /bin/bash
``` 
Then I use **git clone** command to retrieve the source code from GitHub.
By the way, I am supposed to have my postgresql database available. Therefore I use this command line in local to find the internal IP address of container
```bash
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' ${CONTAINER_ID}
```
When working inside this maven:jdk container, it will be pointless to try to connect database on localhost:5432. Localhost means the system itself, and it's obvious that no database runs on 5432 port in maven:jdk container. Instead, the DATABASE_URL should be the internal IP address we got before.
Then I can migrate the database schema to the database and run unit test and troubleshoot error in this container (The program should include some APIs go through the securiter filter and some APIs do not).

```bash
mvn clean compile flyway:migrate -Ddb_url=${db_url} -Ddb_port=${db_port} -Ddb_name=${db_name} -Ddb_user=${db_user} -Ddb_password=${db_password}

mvn test -Ddatabase.driver=${database.driver} -Ddatabase.url=${database.url} -Ddatabase.user=${database.user} -Ddatabase.password=${databse.password} -Ddatabase.dialect=${database.dialect} -Daws.accessKeyId=${}aws.accessKeyId -Daws.secretKey=${aws.secretKey}
```

## Package the **.war file** in the Docker container
In the same container I ran before, package the project as a .war file which will be stored in Target directory as default.
```bash
mvn compile package -DskipTests=true -q
```

## Move the .war file from container to local machine
After packaging the .war file, we need to deploy Tomcat servlet on local machine so we need to copy the .war file from container to local. I use this command on local
```bash
docker cp container_name:/path/in/container the/location/you/wanna/move/to
```
## Build Docker image with **.war** file, setenv.sh and Dockerfile in Local Machine
I try to build an image with dockerfile (including environment variables like DB_URL, DB_PASSWORD), setenv.sh (use environment variables as JVM options) and **.war** file.


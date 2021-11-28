# Backend-Java-EasyPatient
Java Backend for easypatient project

## Local postgres setup
These steps allow you to run a docker container with postgres database on your local machine. 
You have to reproduce these steps before running the application.

### Steps to reproduce for local postgres database setup
1) Create docker container with postgres:

`$ docker run --name easyPatient -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine`


2) Check if container has been properly set up:

`$ docker ps`

and copy the value of CONTAINER ID from this command output

3) Enter the container

`$ docker exec -it {CONTAINER ID} bin/bash` ->
`$ psql -U postgres`

4) Create database easypatientdb:

`$ CREATE DATABASE easypatientdb;`

After creating a new database you can connect to it with:

`$ \c easypatientdb`

5) Create extension (execute this command while being connected to easypatientdb):
   `$ CREATE EXTENSION "uuid-ossp";`

note: this command has to be executed while being connected to the previously created database. 
Now you can run the application. You can also try some of useful commands listed at the bottom 
of this page.

##Sign up and sign in
To start using the application you have to create a user. To do it simply open postman and send 
a request to `localhost:8080/api/v1/registration` with request body as raw JSON: 
```
{
"firstName": "jan",
"lastName": "kowalski",
"email": "jan.kowalski@email.com",
"password": "password",
"role": "USER"
}
```

## OpenAPI 
To use openAPI type in your browser:

`http://localhost:8080/swagger-ui.html`

note: if you change the port of application the url will change

### Useful commands 

- List of databases:
`$ \l`
- Create data base:
`$ CREATE DATABASE {name};`

  remember to use semicolons! (;)

- Connect to specific database:
`$ \c {name}`
- Show content:
`$ \d`
- Show only tables:
`$ \dt`
- Describe content of table:
`$ \d {table name}`
- Create extension for UUID generator:
`$ CREATE EXTENSION "uuid-ossp";`



`$ docker build -f Dockerfile -t docker-easy-patient .`
`$ docker run -p 8085:8085 docker-easy-patient`
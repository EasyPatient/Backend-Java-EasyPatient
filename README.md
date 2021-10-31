# Backend-Java-EasyPatient
Java Backend for easypatient project

##Local postgres setup
These steps allow you to run a docker container with postgres database on your local machine.
###Steps to reproduce for local postgres database setup
1) Create docker container with postgres:

`$ docker run --name {name} -e POSTGRES_PASSWORD={password} -d -p 5432:5432 postgres:alpine`

note: this command will not work until you replace brackets with specified values

2) Check if container has been properly set up:

`$ docker ps`

and copy the value of CONTAINER ID from this command output 

3) Enter the container

`$ docker exec -it {CONTAINER ID} bin/bash` ->
`$ psql -U postgres`

###Useful commands 

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




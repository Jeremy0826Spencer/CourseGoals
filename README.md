# CourseGoals
Spring Boot API to add course goals and some notes. This uses Jwt and I am adding more Junit tests. I want to add logging.
There is a front end and a backend in this project.
The front end uses React and Typescript. I want to add Jest with Enzyme tests for the front end and I want to add Redux.
To run, make sure you have a postgresql database named postgres running on port 5432 with username postgres and password of password, or change application.yml accordingly. You also need to create a schema in the postgres db named trying.
JPA and Hybernate will create the correct tables from the entity classes automatically when the application runs. You can change the word update to create in the application.yml to recreate the db but if you want to keep the same database then keep it as update.
 
For a user to be registered correctly when using register method in auth controller you need to run these commands on the database to create the roles needed for authentication.
insert into roles (id, name) values (1, 'ROLE_ADMIN')
insert into roles (id, name) values (2, 'ROLE_USER')

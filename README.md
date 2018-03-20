[![Build Status](https://travis-ci.org/jesusgsdev/bookshop.svg?branch=master)](https://travis-ci.org/jesusgsdev/bookshop)
[![codecov](https://codecov.io/gh/jesusgsdev/bookshop/branch/master/graph/badge.svg)](https://codecov.io/gh/jesusgsdev/bookshop)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesusgsdev/bookshop?branch=master)](https://bettercodehub.com/)

# Bookshop project using Spring Boot 2 and JUnit 5
In this demo project I will be using Spring Boot 2.0 and jUnit 5 in their latest releases.

## Accessing to the H2 database
You can access to the database typing `http://localhost:8080/console` and using as JDBC URL this one: `jdbc:h2:mem:bookshop`
and the user name is `sa` and the password is `password`.

## Postman Collection
I have provided a Postman collection with all the existing endpoints and examples of calls for each one of them.

## Docker
### Running the Bookshop app as a single instance within a container on port 8080
If you want to build an image for docker, just execute the command 
`docker build -t dockerbookshop .` to build the image and then use
`docker run -p 8080:8080 docker-bookshop` to run it within a docker container.

### Running 10 instances behind a load balancer on port 80
First of all, you need to run the command `docker swarm init` to create a Swarm within docker
and then run `docker stack deploy --compose-file=docker-compose.yml bookshop-stack`. 
You will see how the 10 instances start to spin up and eventually all of them will be working.
Each one will have it own H2 Database so the database is not distributed.
To leave the Swarm and stop and delete all created containers run the command 
`docker swarm leave` or `docker swarm leave --force` in case the first doesn't work.

### Current stack
- SpringBoot 2.0.0 RELEASE
- JUnit 5.1.0
- H2 Database 1.4.196

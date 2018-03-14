[![Build Status](https://travis-ci.org/jesusgsdev/bookshop.svg?branch=master)](https://travis-ci.org/jesusgsdev/bookshop)
[![codecov](https://codecov.io/gh/jesusgsdev/bookshop/branch/master/graph/badge.svg)](https://codecov.io/gh/jesusgsdev/bookshop)

# Bookshop project using Spring Boot 2 and jUnit 5
In this demo project I will be using Spring Boot 2.0 and jUnit 5 in their latest releases.

## Current stack

- SpringBoot 2.0.0 RELEASE
- JUnit 5.1.0
- H2 Database 1.4.196

# Accessing to the H2 database
You can access to the database typing `http://localhost:8080/console` and using as JDBC URL this one: `jdbc:h2:mem:bookshop`
and the user name is `sa` and the password is `password`.

# Postman Collection
I have provided a Postman collection with all the existing endpoints and examples of calls for each one of them.

# Docker
If you want to build an image for docker, just execute the command 
`docker build -t dockerbookshop .` to build the image and then use
`docker run -p 8080:8080 docker-bookshop` to run it within a docker container.

In case you want to try to use docker-compose to see how you can boot three instances on differents
ports but all of them working at the same time, run the command `docker-componse up`
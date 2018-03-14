[![Build Status](https://travis-ci.org/jesusgsdev/bookshop.svg?branch=master)](https://travis-ci.org/jesusgsdev/bookshop)
[![codecov](https://codecov.io/gh/jesusgsdev/bookshop/branch/master/graph/badge.svg)](https://codecov.io/gh/jesusgsdev/bookshop)

# Bookshop project using Spring Boot 2 and jUnit 5
In this demo project I will be using Spring Boot 2.0 and jUnit 5 in their latest releases.

## Current stack

- SpringBoot 2.0.0 M7
- jUnit 5.0.2
- H2 Database 1.4.196

# Accessing to the H2 database
You can access to the database typing `http://localhost:8080/console` and using as JDBC URL this one: `jdbc:h2:mem:bookshop`
and the user name is `sa` and the password is `password`.

# Postman Collection
I have provided a Postman collection with all the existing endpoints and examples of calls for each one of them.
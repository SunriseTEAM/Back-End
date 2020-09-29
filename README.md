
# Spring boot | JWT Authentication|REST API | Spring Security |  MySQL Example
# Back-End
*by M.Jackson(Anh Tuan)*

## RESTful API Server ##

&nbsp;
**1. API Description for Project**

METHOD | PATH | DESCRIPTION 
------------|-----|------------
GET | /api/project/{code} | get Project-Problem-SubProblem with code
POST | /api/project | save Project (code will generate by constructor) 
DELETE | /api/project/{code} | delete Project with code
PUT | /api/project/{code} | update Project with code

&nbsp;
## Running the project with MySQL ##

append this at the end of application.properties
&nbsp;

```
## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url= jdbc:mysql://localhost:3306/sunriseshop?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username= root
spring.datasource.password= 12345678
server.port=8090
## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto = none
app.jwtSecret= JWTSuperSecretKey
app.jwtExpirationInMs = 604800000
## Hibernate Logging
logging.level.org.hibernate.SQL= DEBUG
logging.level.org.springframework = info
```

&nbsp;



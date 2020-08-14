
<h3 align="center">Soccer Team API</h3>


---

<p align="center"> An API for storing and listing Soccer Team and its Player
    <br> 
</p>

## 📝 Table of Contents
- [About](#about)
- [Getting Started](#getting_started)
- [Running The Test](#tests)
- [Usage](#usage)

## 🧐 About <a name = "about"></a>
Wanna create a league but don't have the tools to do it?

Maybe this API can help you. 

Using Java, Spring Boot, MySQL, Docker, this API has 5 endpoints to help you facilitate team and player creation.

These are some limitation for this API at the moment 
- There is no endpoint to remove team nor player because it's not a requirement (it can be added quite easily).
- You cannot create Team without Player. Logically, a team without player is useless, right?



## 🏁 Getting Started <a name = "getting_started"></a>
You must have these tools installed to run the application
```
Maven
Docker
Java 8
```

This API is using MySQL as DB.

By default, it will run at port 5000

if you want to change it, you can modify the configuration in **app_config** folder (maven profiling with -P).

If you modify the docker config, you need to modify the docker compose also in the expose part.

if you modify the sql password, then you need to align it with the docker (and docker compose).

### Option 1 - Docker (Recommended but not for Windows user)
- Go to root project folder
- Run docker compose command (it will take some time so grab a coffee and food while you wait)
```
docker-compose build
docker-compose up -d
```
- [Check list of endpoints in Spring Doc (OpenAPI 3)](http://localhost:5000/api/swagger-ui.html)



### Option 2 - Local Application with docker DB (if you are using Windows)
- Go to root project folder
- Run MySQL docker (windows)
```
docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD="pass" -e MYSQL_DATABASE="soccer_api" -e MYSQL_USER="root" -v %cd%/db/sql/init.sql:/docker-entrypoint-initdb.d/init.sql mysql:8.0.21
```
- or Mac OSX
```
docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD="pass" -e MYSQL_DATABASE="soccer_api" -e MYSQL_USER="root" -v ./db/sql/init.sql:/docker-entrypoint-initdb.d/init.sql mysql:8.0.21
```

- Then run maven command (it will take some time so grab a coffee and food while you wait)

```
mvn clean install
mvn spring-boot:run
```

### Removing container

if you are using docker-compose up to start, then just docker-compose down
```
docker-compose down -v
```

if you are using docker mysql only
```
docker stop mysql
docker rm mysql
```


## 🔧 Running the tests <a name = "tests"></a>
There are unit test and integration test in this project.

I am using JUnit and TestContainer.
By default, it will be called if you run :
```
mvn clean install
```

## 🎈 Usage <a name="usage"></a>
There are 5 endpoints (if you go to <a href="http://localhost:5000/api/swagger-ui.html">  Spring Doc (OpenAPI 3)</a>)
Below are the details, example request and example response
Currently, only endpoint "/api/soccer/players/{teamName}" has caching feature

| HTTP Methods / Response Code | Endpoint                       | Description                         | Example Request                                                                                                                                                                                                                                                                                               | Example Response                                                                                                                                                                                                                                          |
|------------------------------|--------------------------------|-------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET / 200                    | /api/soccer/teams              | Get all teams and players           | curl -X GET "http://localhost:5000/api/soccer/teams" -H "accept: application/json"                                                                                                                                                                                                                            | [{"id":0,"teamName":"Liverpool FC","players":[{"id":2,"playerNo":"8","playerName":"Steven Gerrard"}]},{"id":1,"teamName":"ChelseaFC","players":[{"id":3,"playerNo":"8","playerName":"Frank Lampard"},{"id":4,"playerNo":"4","playerName":"John Terry"}]}] |
| POST / 201                   | /api/soccer/teams              | Create team(s) and player(s)        | curl -X POST "http://localhost:5000/api/soccer/teams" -H "accept: application/json" -H "Content-Type: application/json" -d "[{\"id\":1,\"teamName\":\"ChelseaFC\",\"players\":[{\"id\":0,\"playerNo\":\"8\",\"playerName\":\"Frank Lampard\"},{\"id\":1,\"playerNo\":\"4\",\"playerName\":\"John Terry\"}]}]" |                                                                                                                                                                                                                                                           |
| GET / 200                    | /api/soccer/players/{teamName} | Get all players in a team           | curl -X GET "http://localhost:5000/api/soccer/players/Liverpool%20FC" -H "accept: application/json"                                                                                                                                                                                                           | [{"id":2,"playerNo":"8","playerName":"Steven Gerrard"}]                                                                                                                                                                                                   |
| POST / 201                   | /api/soccer/team               | Create team and player(s)           | curl -X POST "http://localhost:5000/api/soccer/team" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"id\":0,\"teamName\":\"Liverpool FC\",\"players\":[{\"playerNo\":\"9\",\"playerName\":\"Fernando Torres\"}]}"                                                                    |                                                                                                                                                                                                                                                           |
| GET /200                     | /api/soccer/players            | Get all players (without team name) | curl -X GET "http://localhost:5000/api/soccer/players" -H "accept: application/json"                                                                                                                                                                                                                          | [{"id":2,"playerNo":"8","playerName":"Steven Gerrard"},{"id":3,"playerNo":"8","playerName":"Frank Lampard"},{"id":4,"playerNo":"4","playerName":"John Terry"}]                                                                                            |

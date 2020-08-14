
<h3 align="center">Soccer API</h3>


---

<p align="center"> An API that serves for storing and listing Soccer Team and its Player
    <br> 
</p>

## 📝 Table of Contents
- [About](#about)
- [Getting Started](#getting_started)
- [Deployment](#deployment)
- [Usage](#usage)
- [Built Using](#built_using)
- [TODO](../TODO.md)
- [Contributing](../CONTRIBUTING.md)
- [Authors](#authors)
- [Acknowledgments](#acknowledgement)

## 🧐 About <a name = "about"></a>
API that I created for technical test in kitabisa.com using Java, Spring Boot, MySQL, Docker.

There are 5 endpoints in this API.

There is no endpoint to remove team nor player because it's not a requirement (it can be added quite easily though).


## 🏁 Getting Started <a name = "getting_started"></a>
You must have these tools installed to run the application
```
Maven
Docker
Java 8
```

This API is using MySQL as DB.
It's easier to do everything via docker

### Docker
- Go to root project folder
- Run docker compose command
```
docker-compose build
docker-compose up -d
```
- Check swagger-ui (open browser : localhost:5000/api/swagger-ui)



### Local Application with docker DB (another option)
- Go to root project folder
- Run MySQL docker (windows)
```
docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD="pass" -e MYSQL_DATABASE="soccer_api" -e MYSQL_USER="root" -v %cd%/db/sql/init.sql:/docker-entrypoint-initdb.d/init.sql --link mysql:mysqldb mysql:8.0.21
```
- or Mac OSX
```
docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD="pass" -e MYSQL_DATABASE="soccer_api" -e MYSQL_USER="root" -v ./db/sql/init.sql:/docker-entrypoint-initdb.d/init.sql --link mysql:mysqldb mysql:8.0.21
```

- Then run maven command

```
mvn clean install
mvn spring-boot:run
```

## 🔧 Running the tests <a name = "tests"></a>
There are unit test and integration test in this project.

I am using JUnit and TestContainer.
By default, it will be called if you run :
```
mvn clean install
```

## 🎈 Usage <a name="usage"></a>
Add notes about how to use the system.
